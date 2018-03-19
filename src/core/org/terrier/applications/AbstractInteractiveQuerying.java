package org.terrier.applications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terrier.matching.ResultSet;
import org.terrier.querying.ManagerInterface;
import org.terrier.querying.SearchRequest;
import org.terrier.structures.Index;
import org.terrier.structures.MetaIndex;
import org.terrier.utility.ApplicationSetup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public abstract class AbstractInteractiveQuerying {

    /** The logger used */
    protected static final Logger logger = LoggerFactory.getLogger(InteractiveQuerying.class);

    /** Change to lowercase? */
    protected final static boolean lowercase = Boolean.parseBoolean(ApplicationSetup.getProperty("lowercase", "true"));
    /** display user prompts */
    protected boolean verbose = true;
    /** the number of processed queries. */
    protected int matchingCount = 0;
    /** The file to store the output to.*/
    protected PrintWriter resultFile = new PrintWriter(System.out);
    /** The name of the manager object that handles the queries. Set by property <tt>trec.manager</tt>, defaults to Manager. */
    protected String managerName = ApplicationSetup.getProperty("interactive.manager", "Manager");
    /** The query manager.*/
    protected ManagerInterface queryingManager;
    /** The weighting model used. */
    protected String wModel = ApplicationSetup.getProperty("interactive.model", "PL2");
    /** The matching model used.*/
    protected String mModel = ApplicationSetup.getProperty("interactive.matching", "Matching");
    /** The data structures used.*/
    protected Index index;
    /** The maximum number of presented results. */
    protected static int RESULTS_LENGTH =
            Integer.parseInt(ApplicationSetup.getProperty("interactive.output.format.length", "1000"));

    protected String[] metaKeys = ApplicationSetup.getProperty("interactive.output.meta.keys", "docno").split("\\s*,\\s*");

    protected boolean printDocid = Boolean.parseBoolean(ApplicationSetup.getProperty("interactive.output.docids", "false"));

    protected abstract void createManager();
    protected abstract void loadIndex();
    public abstract void close();
    public abstract void processQuery(String queryId, String query, double cParameter);

    /**
     * Performs the matching using the specified weighting model
     * from the setup and possibly a combination of evidence mechanism.
     * It parses the file with the queries (the name of the file is defined
     * in the address_query file), creates the file of results, and for each
     * query, gets the relevant documents, scores them, and outputs the results
     * to the result file.
     * @param cParameter the value of c
     */
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
    /**
     * Prints the results
     * @param pw PrintWriter the file to write the results to.
     * @param q SearchRequest the search request to get results from.
     */
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
                final MetaIndex metaIndex = index.getMetaIndex();
                docNames[metaKeyId] = metaIndex.getItems(metaIndexDocumentKey, docids);
            }
            metaKeyId++;
        }


        StringBuilder sbuffer = new StringBuilder();
        //the results are ordered in asceding order
        //with respect to the score. For example, the
        //document with the highest score has score
        //score[scores.length-1] and its docid is
        //docid[docids.length-1].
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
}
