
package org.terrier.remote_client;

import java.io.File;

import org.terrier.applications.batchquerying.TRECQuerying;
import org.terrier.matching.ResultSet;
import org.terrier.querying.ManagerInterface;
import org.terrier.querying.Request;
import org.terrier.querying.SearchRequest;
import org.terrier.remote_client.client.ApiException;
import org.terrier.structures.*;
import org.terrier.utility.ApplicationSetup;

public class RemoteTrecQuerying extends TRECQuerying {

    protected String indexPath;
    protected String indexPrefix;
    protected String indexName;

    public RemoteTrecQuerying(String path, String prefix, String name, boolean _queryexpansion) {

        this.indexPath = path;

        if (prefix == null || prefix.equals("")) {
            this.indexPrefix = "data";
        } else {
            this.indexPrefix = prefix;
        }

        if (name == null || name.equals("")) {
            this.indexName = "index";
        } else {
            this.indexName = name;
        }

        createManager();
        loadIndex();
        this.querySource = this.getQueryParser();
        this.printer = getOutputFormat();
        this.resultsCache = getResultsCache();
        this.queryexpansion = _queryexpansion;
    }

    protected void createManager() {
        this.queryingManager = new ApiRemoteManager();
    }

    /**
     * Imports an on-disk index
     */
    protected void loadIndex() {
        long startLoading = System.currentTimeMillis();
        indexName = "index";
        try {
            ((ApiRemoteManager) queryingManager).importIndex(this.indexPath, this.indexPrefix, this.indexName);
        } catch (Exception e) {
            logger.error("Failed to load index. " + Index.getLastIndexLoadError());
            throw new IllegalArgumentException("Failed to load index: " + Index.getLastIndexLoadError());
        }
        long endLoading = System.currentTimeMillis();
        if (logger.isInfoEnabled())
            logger.info("time to intialise index : "
                    + ((endLoading - startLoading) / 1000.0D));
    }

    public void setIndex(Index index) {
        try{
            ((ApiRemoteManager) queryingManager).importIndex( ((IndexOnDisk) index).getPath(), ((IndexOnDisk) index).getPrefix(), "index");
        } catch (ApiException e){
            e.printStackTrace();
        }
    }

    public ManagerInterface getManager() {
        return queryingManager;
    }

    public void close() {
        try {
            ((ApiRemoteManager) queryingManager).removeIndex(indexName);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SearchRequest processQuery(String queryId, String query, double cParameter, boolean c_set) {

        if (removeQueryPeriods && query.indexOf(".") > -1) {
            logger.warn("Removed . from query");
            query = query.replaceAll("\\.", " ");
        }

        if (logger.isInfoEnabled())
            logger.info(queryId + " : " + query);

        SearchRequest srq = queryingManager.newSearchRequest(queryId, query);
        initSearchRequestModification(queryId, srq);
        String c = null;
        if (c_set) {
            srq.setControl("c", Double.toString(cParameter));
        } else if ((c = ApplicationSetup.getProperty("trec.c", null)) != null) {
            srq.setControl("c", c);
        }
        c = null;
        if ((c = srq.getControl("c")).length() > 0) {
            c_set = true;
        }
        srq.setControl("c_set", "" + c_set);

        srq.addMatchingModel(mModel, wModel);

        if (queryexpansion) {
            srq.setControl("qemodel", defaultQEModel);
            srq.setControl("qe", "on");
        }

        preQueryingSearchRequestModification(queryId, srq);
        ResultSet rs = resultsCache.checkCache(srq);
        if (rs != null)
            ((Request)rs).setResultSet(rs);

        if (logger.isInfoEnabled())
            logger.info("Processing query: " + queryId + ": '" + query + "'");
        matchingCount++;
        ((ApiRemoteManager) queryingManager).runSearchRequest(indexName, srq);
        resultsCache.add(srq);
        return srq;
    }
}
