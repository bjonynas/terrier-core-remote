package org.terrier.remote_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terrier.applications.AbstractInteractiveQuerying;
import org.terrier.matching.ResultSet;
import org.terrier.querying.Manager;
import org.terrier.querying.Request;
import org.terrier.querying.SearchRequest;
import org.terrier.querying.parser.Query;
import org.terrier.querying.parser.QueryParser;
import org.terrier.remote_client.client.ApiException;
import org.terrier.structures.Index;
import org.terrier.structures.IndexOnDisk;
import org.terrier.structures.MetaIndex;
import org.terrier.utility.ApplicationSetup;

import java.io.*;

public class RemoteInteractiveQuerying extends AbstractInteractiveQuerying{

        //The name of the index being queried
        protected String indexName;

        /** A default constructor initialises the index, and the Manager. */
        public RemoteInteractiveQuerying() {
            createManager();
            loadIndex();
        }

        /**
         * Loads index(s) from disk.
         *
         */
        protected void loadIndex() {
            long startLoading = System.currentTimeMillis();
            try {
                indexName = "index";
                ((ApiRemoteManager) queryingManager).importIndex(ApplicationSetup.TERRIER_INDEX_PATH, ApplicationSetup.TERRIER_INDEX_PREFIX, indexName);
            } catch (ApiException e) {
                logger.error("Failed to load index. Perhaps index files are missing");
            }
            long endLoading = System.currentTimeMillis();
            if (logger.isInfoEnabled())
                logger.info("time to intialise index : " + ((endLoading-startLoading)/1000.0D));
        }

        protected void createManager(){
            queryingManager = new ApiRemoteManager();
        }

        /**
         * Closes the used structures.
         */
        public void close() {
            try{
                ((ApiRemoteManager) queryingManager).removeIndex("index");
            } catch (ApiException e) {
                logger.warn("Problem closing index", e);
            }

        }

        public void processQuery(String queryId, String query, double cParameter) {
            Query q = null;
            try{
                q = QueryParser.parseQuery(query);
            }catch (Exception e){
                e.printStackTrace();
            }

            SearchRequest srq = queryingManager.newSearchRequest(queryId, query);
            srq.setControl("c", Double.toString(cParameter));
            srq.addMatchingModel(mModel, wModel);
            srq.setControl("c", Double.toString(cParameter));
            matchingCount++;
            ((ApiRemoteManager) queryingManager).runSearchRequest(indexName, srq);
            try{
                printResults(resultFile, srq);
            } catch (IOException ioe) {
                logger.error("Problem displaying results", ioe);
            }
        }

        public void processQueries(double cParameter) {
            try {
                //prepare console input
                InputStreamReader consoleReader = new InputStreamReader(System.in);
                BufferedReader consoleInput = new BufferedReader(consoleReader);
                String query; int qid=1;
                if (verbose)
                    System.out.print("Please enter your query: ");
                while ((query = consoleInput.readLine()) != null) {
                    if (query.length() == 0 ||
                            query.toLowerCase().equals("quit") ||
                            query.toLowerCase().equals("exit")
                            )
                    {
                        return;
                    }
                    processQuery(""+(qid++), lowercase ? query.toLowerCase() : query, cParameter);


                    if (verbose)
                        System.out.print("Please enter your query: ");
                }
            } catch(IOException ioe) {
                logger.error("Input/Output exception while performing the matching. Stack trace follows.",ioe);
            }
        }

        public void printResults(PrintWriter pw, SearchRequest q) throws IOException {
            ResultSet set = q.getResultSet();
            int[] docids = set.getDocids();
            double[] scores = set.getScores();
            int minimum = RESULTS_LENGTH;
            //if the minimum number of documents is more than the
            //number of documents in the results, aw.length, then
            //set minimum = aw.length
            if (minimum > set.getResultSize())
                minimum = set.getResultSize();
            if (verbose)
                if(set.getResultSize()>0)
                    pw.write("\n\tDisplaying 1-"+set.getResultSize()+ " results\n");
                else
                    pw.write("\n\tNo results\n");
            if (set.getResultSize() == 0)
                return;

            int metaKeyId = 0; final int metaKeyCount = metaKeys.length;
            String[][] docNames = new String[metaKeyCount][];
            for(String metaIndexDocumentKey : metaKeys)
            {
                if (set.hasMetaItems(metaIndexDocumentKey))
                {
                    docNames[metaKeyId] = set.getMetaItems(metaIndexDocumentKey);
                }
                else
                {
                    final MetaIndex metaIndex = queryingManager.getIndex().getMetaIndex();
                    docNames[metaKeyId] = metaIndex.getItems(metaIndexDocumentKey, docids);
                }
                metaKeyId++;
            }


            StringBuilder sbuffer = new StringBuilder();
            int start = 0;
            int end = minimum;
            for (int i = start; i < end; i++) {
                if (scores[i] <= 0d)
                    continue;
                sbuffer.append(i);
                sbuffer.append(" ");

                for(metaKeyId = 0; metaKeyId < metaKeyCount; metaKeyId++)
                {
                    sbuffer.append(docNames[metaKeyId][i]);
                    sbuffer.append(" ");
                }
                if (printDocid)
                {
                    sbuffer.append(docids[i]);
                    sbuffer.append(" ");
                }
                sbuffer.append(scores[i]);
                sbuffer.append('\n');
            }
            //System.out.println(sbuffer.toString());
            pw.write(sbuffer.toString());
            pw.flush();
            //pw.write("finished outputting\n");
        }
        /**
         * Starts the interactive query application.
         * @param args the command line arguments.
         */
        public static void main(String[] args) {
            RemoteInteractiveQuerying rq = new RemoteInteractiveQuerying();
            if (args.length == 0)
            {
                rq.processQueries(1.0);
            }
            else if (args.length == 1 && args[0].equals("--noverbose"))
            {
                rq.verbose = false;
                rq.processQueries(1.0);
            }
            else
            {
                rq.verbose = false;
                StringBuilder s = new StringBuilder();
                for(int i=0; i<args.length;i++)
                {
                    s.append(args[i]);
                    s.append(" ");
                }
                rq.processQuery("CMDLINE", s.toString(), 1.0);
            }
        }
}