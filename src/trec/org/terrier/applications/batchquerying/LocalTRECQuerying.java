package org.terrier.applications.batchquerying;

import org.terrier.matching.ResultSet;
import org.terrier.querying.Manager;
import org.terrier.querying.Request;
import org.terrier.querying.SearchRequest;
import org.terrier.structures.Index;
import org.terrier.utility.ApplicationSetup;

import java.io.IOException;

//Class to perform TREC querying on a local index
public class LocalTRECQuerying extends TRECQuerying{


    /**
     * TRECQuerying default constructor initialises the inverted index, the
     * lexicon and the document index structures.
     */
    public LocalTRECQuerying() {
        this.loadIndex();
        this.createManager();
        this.querySource = this.getQueryParser();
        this.printer = getOutputFormat();
        this.resultsCache = getResultsCache();
    }

    /**
     * TRECQuerying constructor initialises the inverted index, the
     * lexicon and the document index structures.
     */
    public LocalTRECQuerying(boolean _queryexpansion) {
        this.loadIndex();
        this.createManager();
        this.querySource = this.getQueryParser();
        this.printer = getOutputFormat();
        this.resultsCache = getResultsCache();
        this.queryexpansion = _queryexpansion;
    }

    /**
     * TRECQuerying constructor initialises the specified inverted index, the
     * lexicon and the document index structures.
     *
     * @param i The specified index.
     */
    public LocalTRECQuerying(Index i) {
        this.setIndex(i);
        this.createManager();
        this.querySource = this.getQueryParser();
        this.printer = getOutputFormat();
        this.resultsCache = getResultsCache();
    }

    /**
     * Create a querying manager. This method should be overriden if another
     * matching model is required.
     */
    protected void createManager() {
        try {
            if (managerName.indexOf('.') == -1)
                managerName = "org.terrier.querying." + managerName;
            else if (managerName.startsWith("uk.ac.gla.terrier"))
                managerName = managerName.replaceAll("uk.ac.gla.terrier", "org.terrier");
            queryingManager = (Manager) (Class.forName(managerName)
                    .getConstructor(new Class[] { Index.class })
                    .newInstance(new Object[] { index }));
        } catch (Exception e) {
            logger.error("Problem loading Manager (" + managerName + "): ", e);

        }
    }


    /**
     * Loads index(s) from disk.
     *
     */
    protected void loadIndex() {
        long startLoading = System.currentTimeMillis();
        index = Index.createIndex();
        if (index == null) {
            logger.error("Failed to load index. " + Index.getLastIndexLoadError());
            throw new IllegalArgumentException("Failed to load index: " + Index.getLastIndexLoadError());
        }
        long endLoading = System.currentTimeMillis();
        if (logger.isInfoEnabled())
            logger.info("time to intialise index : "
                    + ((endLoading - startLoading) / 1000.0D));
    }
    /**
     * Set the index pointer.
     *
     * @param i
     *            The index pointer.
     */
    public void setIndex(Index i) {
        this.index = i;
        if (index == null) {
            throw new IllegalArgumentException("Index specified was null. Perhaps index files are missing");
        }
    }

    /**
     * Get the querying manager.
     *
     * @return The querying manager.
     */
    public Manager getManager() {
        return (Manager) queryingManager;
    }

    /**
     * Closes the used structures.
     */
    public void close() {
        if (index != null)
            try {
                index.close();
            } catch (IOException e) {
            }
    }


    /**
     * According to the given parameters, it sets up the correct matching class
     * and performs retrieval for the given query.
     *
     * @param queryId
     *            the identifier of the query to process.
     * @param query
     *            the query to process.
     * @param cParameter
     *            double the value of the parameter to use.
     * @param c_set
     *            boolean specifies whether the parameter c is set.
     */
    public SearchRequest processQuery(String queryId, String query,
                                      double cParameter, boolean c_set) {

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
            //if (srq.getControl("qemodel").length() == 0)
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
        ((Manager) queryingManager).runPreProcessing(srq);
        ((Manager) queryingManager).runMatching(srq);
        ((Manager) queryingManager).runPostProcessing(srq);
        ((Manager) queryingManager).runPostFilters(srq);
        resultsCache.add(srq);
        return srq;
    }
}
