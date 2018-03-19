package org.terrier.remote_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terrier.querying.Manager;
import org.terrier.querying.ManagerInterface;
import org.terrier.querying.parser.QueryParser;
import org.terrier.querying.parser.QueryParserException;
import org.terrier.remote_client.client.ApiClient;
import org.terrier.remote_client.client.ApiException;
import org.terrier.remote_client.client.api.DefaultApi;
import org.terrier.remote_client.client.model.RemoteIndex;
import org.terrier.remote_client.client.model.RemoteResultSet;
import org.terrier.matching.QueryResultSet;
import org.terrier.matching.ResultSet;
import org.terrier.querying.Request;
import org.terrier.querying.SearchRequest;
import org.terrier.remote_client.client.model.ResultDocument;
import org.terrier.structures.Index;
import org.terrier.utility.ApplicationSetup;

import java.util.*;

public class ApiRemoteManager implements ManagerInterface {
    protected static final Logger logger = LoggerFactory.getLogger(ApiRemoteManager.class);

    DefaultApi api;
    String basepath = "http://localhost:8080/remote-terrier";
    Index index;

    public ApiRemoteManager(){
        ApiClient client = new ApiClient();
        client.setBasePath(basepath);
        api = new DefaultApi(client);
    }

    public void  importIndex(String path, String prefix, String indexName) throws ApiException{
        RemoteIndex rIndex = new RemoteIndex();
        rIndex.setPath(path);
        rIndex.setPrefix(prefix);
        rIndex.setIndexName(indexName);
        api.importIndex(rIndex);
        this.index = Index.createIndex(path, prefix);
    }

    public void removeIndex(String indexName) throws ApiException{
            api.deleteIndex(indexName);
    }

    @Override
    public void setProperty(String s, String s1) {
        ApplicationSetup.setProperty(s, s1);
    }

    @Override
    public void setProperties(Properties properties) {
        String propKey;
        Iterator itr = properties.keySet().iterator();
        while(itr.hasNext()){
            propKey = (String) itr.next();
            ApplicationSetup.setProperty(propKey, properties.getProperty(propKey));
        }
    }

    public SearchRequest newSearchRequest(String QueryID)
    {
        Request q = new Request();
        q.setQueryID(QueryID);
        q.setIndex(this.index);
        return (SearchRequest)q;
    }

    public SearchRequest newSearchRequest(String QueryID, String query)
    {
        Request q = new Request();
        q.setQueryID(QueryID);
        q.setIndex(this.index);
        try{
            QueryParser.parseQuery(query, q);
        } catch (QueryParserException qpe) {
            logger.error("Error while parsing the query.",qpe);
        }
        q.setOriginalQuery(query);
        return q;
    }

    @Override
    public void runSearchRequest(SearchRequest searchRequest){}

    @Override
    public Index getIndex() {
        return null;
    }

    public void runSearchRequest(String indexName, SearchRequest searchRequest) {
        Request rq = (Request) searchRequest;
        List<String> queryControlNames = new LinkedList<String>();
        List<String> queryControlValues = new LinkedList<String>();

        Map<String, String> cont = rq.getControlHashtable();
        for(Map.Entry<String, String> entry : cont.entrySet()){
            queryControlNames.add(entry.getKey());
            queryControlValues.add(entry.getValue());
        }

        try{
            RemoteResultSet remoteResultSet = api.retrieve(indexName, searchRequest.getOriginalQuery(), rq.getQueryID(), ((Request) searchRequest).getMatchingModel(), ((Request) searchRequest).getWeightingModel(), queryControlNames, queryControlValues);

            List<ResultDocument> docs = remoteResultSet.getDocuments();
            int[] docIdArray = new int[docs.size()];
            double[] docScoreArray = new double[docs.size()];

            String[] metaKeys = new String[0];
            String[][] metaValues = new String[0][0];

            if(docs.size() > 0){
                metaKeys = new String[docs.get(0).getMetadata().getMetaKeys().size()];
                metaValues = new String[metaKeys.length][docs.size()];

                for(int k=0; k<metaKeys.length; k++){
                    metaKeys[k] = docs.get(0).getMetadata().getMetaKeys().get(k);
                }

                for(int x=0; x<docs.size(); x++){
                    docIdArray[x] = docs.get(x).getDocId();
                    docScoreArray[x] = docs.get(x).getScore();

                    for(int k=0; k<metaKeys.length; k++){
                        metaValues[k][x] = docs.get(x).getMetadata().getMetaItems().get(k);
                    }
                }
            }

            ResultSet resultSet = new QueryResultSet(docIdArray, docScoreArray, null);
            for(int k=0; k<metaKeys.length; k++){
                resultSet.addMetaItems(metaKeys[k], metaValues[k]);
            }

            ((Request) searchRequest).setResultSet(resultSet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String getInfo(SearchRequest srq) {
        return "";
    }

}
