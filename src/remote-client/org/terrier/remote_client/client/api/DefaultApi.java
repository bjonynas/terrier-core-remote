

package org.terrier.remote_client.client.api;

import org.terrier.remote_client.client.ApiCallback;
import org.terrier.remote_client.client.ApiClient;
import org.terrier.remote_client.client.ApiException;
import org.terrier.remote_client.client.ApiResponse;
import org.terrier.remote_client.client.Configuration;
import org.terrier.remote_client.client.Pair;
import org.terrier.remote_client.client.ProgressRequestBody;
import org.terrier.remote_client.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.terrier.remote_client.client.model.IndexStats;
import org.terrier.remote_client.client.model.RemoteDocument;
import org.terrier.remote_client.client.model.RemoteIndex;
import org.terrier.remote_client.client.model.RemoteResultSet;
import org.terrier.remote_client.client.model.ServerStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultApi {
    private ApiClient apiClient;

    public DefaultApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DefaultApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for addDocuments
     * @param collection path of the collection for which documents are to be shown (required)
     * @param path path to the documents to be added (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call addDocumentsCall(String collection, String path, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/documents/{collection}"
                .replaceAll("\\{" + "collection" + "\\}", apiClient.escapeString(collection.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (path != null)
            localVarFormParams.put("path", path);

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
                "application/x-www-form-urlencoded"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call addDocumentsValidateBeforeCall(String collection, String path, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'collection' is set
        if (collection == null) {
            throw new ApiException("Missing the required parameter 'collection' when calling addDocuments(Async)");
        }

        // verify the required parameter 'path' is set
        if (path == null) {
            throw new ApiException("Missing the required parameter 'path' when calling addDocuments(Async)");
        }


        com.squareup.okhttp.Call call = addDocumentsCall(collection, path, progressListener, progressRequestListener);
        return call;

    }

    /**
     * add documents to a collection
     * Add documents to the specified collection
     * @param collection path of the collection for which documents are to be shown (required)
     * @param path path to the documents to be added (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void addDocuments(String collection, String path) throws ApiException {
        addDocumentsWithHttpInfo(collection, path);
    }

    /**
     * add documents to a collection
     * Add documents to the specified collection
     * @param collection path of the collection for which documents are to be shown (required)
     * @param path path to the documents to be added (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> addDocumentsWithHttpInfo(String collection, String path) throws ApiException {
        com.squareup.okhttp.Call call = addDocumentsValidateBeforeCall(collection, path, null, null);
        return apiClient.execute(call);
    }

    /**
     * add documents to a collection (asynchronously)
     * Add documents to the specified collection
     * @param collection path of the collection for which documents are to be shown (required)
     * @param path path to the documents to be added (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call addDocumentsAsync(String collection, String path, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = addDocumentsValidateBeforeCall(collection, path, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for deleteDocuments
     * @param collection path of the index from the collection of which documents are to be deleted (required)
     * @param documents document to be deleted (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call deleteDocumentsCall(String collection, String documents, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/documents/{collection}"
                .replaceAll("\\{" + "collection" + "\\}", apiClient.escapeString(collection.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (documents != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("documents", documents));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call deleteDocumentsValidateBeforeCall(String collection, String documents, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'collection' is set
        if (collection == null) {
            throw new ApiException("Missing the required parameter 'collection' when calling deleteDocuments(Async)");
        }

        // verify the required parameter 'documents' is set
        if (documents == null) {
            throw new ApiException("Missing the required parameter 'documents' when calling deleteDocuments(Async)");
        }


        com.squareup.okhttp.Call call = deleteDocumentsCall(collection, documents, progressListener, progressRequestListener);
        return call;

    }

    /**
     * delete document from specified collection
     * Delete document(s) from the specified collection
     * @param collection path of the index from the collection of which documents are to be deleted (required)
     * @param documents document to be deleted (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void deleteDocuments(String collection, String documents) throws ApiException {
        deleteDocumentsWithHttpInfo(collection, documents);
    }

    /**
     * delete document from specified collection
     * Delete document(s) from the specified collection
     * @param collection path of the index from the collection of which documents are to be deleted (required)
     * @param documents document to be deleted (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> deleteDocumentsWithHttpInfo(String collection, String documents) throws ApiException {
        com.squareup.okhttp.Call call = deleteDocumentsValidateBeforeCall(collection, documents, null, null);
        return apiClient.execute(call);
    }

    /**
     * delete document from specified collection (asynchronously)
     * Delete document(s) from the specified collection
     * @param collection path of the index from the collection of which documents are to be deleted (required)
     * @param documents document to be deleted (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteDocumentsAsync(String collection, String documents, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = deleteDocumentsValidateBeforeCall(collection, documents, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /**
     * Build call for deleteIndex
     * @param indexName  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call deleteIndexCall(String indexName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/index/{indexName}"
                .replaceAll("\\{" + "indexName" + "\\}", apiClient.escapeString(indexName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call deleteIndexValidateBeforeCall(String indexName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'indexName' is set
        if (indexName == null) {
            throw new ApiException("Missing the required parameter 'indexName' when calling deleteIndex(Async)");
        }


        com.squareup.okhttp.Call call = deleteIndexCall(indexName, progressListener, progressRequestListener);
        return call;

    }

    /**
     * remove specified index from the list of imported indexes
     * Remove the specified index from the list of imported indexes
     * @param indexName  (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String deleteIndex(String indexName) throws ApiException {
        ApiResponse<String> resp = deleteIndexWithHttpInfo(indexName);
        return resp.getData();
    }

    /**
     * remove specified index from the list of imported indexes
     * Remove the specified index from the list of imported indexes
     * @param indexName  (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> deleteIndexWithHttpInfo(String indexName) throws ApiException {
        com.squareup.okhttp.Call call = deleteIndexValidateBeforeCall(indexName, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * remove specified index from the list of imported indexes (asynchronously)
     * Remove the specified index from the list of imported indexes
     * @param indexName  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call deleteIndexAsync(String indexName, final ApiCallback<String> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = deleteIndexValidateBeforeCall(indexName, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getDocuments
     * @param collection path of the collection from which documents are to be shown (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getDocumentsCall(String collection, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/documents/{collection}"
                .replaceAll("\\{" + "collection" + "\\}", apiClient.escapeString(collection.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getDocumentsValidateBeforeCall(String collection, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'collection' is set
        if (collection == null) {
            throw new ApiException("Missing the required parameter 'collection' when calling getDocuments(Async)");
        }


        com.squareup.okhttp.Call call = getDocumentsCall(collection, progressListener, progressRequestListener);
        return call;

    }

    /**
     * get list of documents in the specified collection
     * Get a list of all documents in specified collection\&quot;
     * @param collection path of the collection from which documents are to be shown (required)
     * @return List&lt;RemoteDocument&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<RemoteDocument> getDocuments(String collection) throws ApiException {
        ApiResponse<List<RemoteDocument>> resp = getDocumentsWithHttpInfo(collection);
        return resp.getData();
    }

    /**
     * get list of documents in the specified collection
     * Get a list of all documents in specified collection\&quot;
     * @param collection path of the collection from which documents are to be shown (required)
     * @return ApiResponse&lt;List&lt;RemoteDocument&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<RemoteDocument>> getDocumentsWithHttpInfo(String collection) throws ApiException {
        com.squareup.okhttp.Call call = getDocumentsValidateBeforeCall(collection, null, null);
        Type localVarReturnType = new TypeToken<List<RemoteDocument>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * get list of documents in the specified collection (asynchronously)
     * Get a list of all documents in specified collection\&quot;
     * @param collection path of the collection from which documents are to be shown (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getDocumentsAsync(String collection, final ApiCallback<List<RemoteDocument>> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getDocumentsValidateBeforeCall(collection, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<RemoteDocument>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getIndexes
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getIndexesCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/index";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getIndexesValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {


        com.squareup.okhttp.Call call = getIndexesCall(progressListener, progressRequestListener);
        return call;

    }

    /**
     * get all available indexes
     * Get all available indexes
     * @return List&lt;RemoteIndex&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<RemoteIndex> getIndexes() throws ApiException {
        ApiResponse<List<RemoteIndex>> resp = getIndexesWithHttpInfo();
        return resp.getData();
    }

    /**
     * get all available indexes
     * Get all available indexes
     * @return ApiResponse&lt;List&lt;RemoteIndex&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<RemoteIndex>> getIndexesWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = getIndexesValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<List<RemoteIndex>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * get all available indexes (asynchronously)
     * Get all available indexes
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getIndexesAsync(final ApiCallback<List<RemoteIndex>> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getIndexesValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<RemoteIndex>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for importIndex
     * @param index Index object to be imported. Returns the name of the imported index (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call importIndexCall(RemoteIndex index, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = index;

        // create path and map variables
        String localVarPath = "/index/import";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call importIndexValidateBeforeCall(RemoteIndex index, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'index' is set
        if (index == null) {
            throw new ApiException("Missing the required parameter 'index' when calling importIndex(Async)");
        }


        com.squareup.okhttp.Call call = importIndexCall(index, progressListener, progressRequestListener);
        return call;

    }

    /**
     * import existing index and return its id
     *
     * @param index Index object to be imported. Returns the name of the imported index (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String importIndex(RemoteIndex index) throws ApiException {
        ApiResponse<String> resp = importIndexWithHttpInfo(index);
        return resp.getData();
    }

    /**
     * import existing index and return its id
     *
     * @param index Index object to be imported. Returns the name of the imported index (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> importIndexWithHttpInfo(RemoteIndex index) throws ApiException {
        com.squareup.okhttp.Call call = importIndexValidateBeforeCall(index, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * import existing index and return its id (asynchronously)
     *
     * @param index Index object to be imported. Returns the name of the imported index (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call importIndexAsync(RemoteIndex index, final ApiCallback<String> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = importIndexValidateBeforeCall(index, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for retrieve
     * @param indexName Id of the index to be queried (required)
     * @param queryString  (required)
     * @param queryId  (optional)
     * @param matchingModel  (optional)
     * @param weightingModel  (optional)
     * @param queryControlNames  (optional)
     * @param queryControlValues  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call retrieveCall(String indexName, String queryString, String queryId, String matchingModel, String weightingModel, List<String> queryControlNames, List<String> queryControlValues, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/index/{indexName}/retrieve"
                .replaceAll("\\{" + "indexName" + "\\}", apiClient.escapeString(indexName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (queryId != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("queryId", queryId));
        if (queryString != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("queryString", queryString));
        if (matchingModel != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("matchingModel", matchingModel));
        if (weightingModel != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("weightingModel", weightingModel));
        if (queryControlNames != null)
            localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "queryControlNames", queryControlNames));
        if (queryControlValues != null)
            localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "queryControlValues", queryControlValues));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call retrieveValidateBeforeCall(String indexName, String queryString, String queryId, String matchingModel, String weightingModel, List<String> queryControlNames, List<String> queryControlValues, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'indexName' is set
        if (indexName == null) {
            throw new ApiException("Missing the required parameter 'indexName' when calling retrieve(Async)");
        }

        // verify the required parameter 'queryString' is set
        if (queryString == null) {
            throw new ApiException("Missing the required parameter 'queryString' when calling retrieve(Async)");
        }


        com.squareup.okhttp.Call call = retrieveCall(indexName, queryString, queryId, matchingModel, weightingModel, queryControlNames, queryControlValues, progressListener, progressRequestListener);
        return call;

    }

    /**
     * run a query
     * Runs a query
     * @param indexName Id of the index to be queried (required)
     * @param queryString  (required)
     * @param queryId  (optional)
     * @param matchingModel  (optional)
     * @param weightingModel  (optional)
     * @param queryControlNames  (optional)
     * @param queryControlValues  (optional)
     * @return RemoteResultSet
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RemoteResultSet retrieve(String indexName, String queryString, String queryId, String matchingModel, String weightingModel, List<String> queryControlNames, List<String> queryControlValues) throws ApiException {
        ApiResponse<RemoteResultSet> resp = retrieveWithHttpInfo(indexName, queryString, queryId, matchingModel, weightingModel, queryControlNames, queryControlValues);
        return resp.getData();
    }

    /**
     * run a query
     * Runs a query
     * @param indexName Id of the index to be queried (required)
     * @param queryString  (required)
     * @param queryId  (optional)
     * @param matchingModel  (optional)
     * @param weightingModel  (optional)
     * @param queryControlNames  (optional)
     * @param queryControlValues  (optional)
     * @return ApiResponse&lt;RemoteResultSet&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<RemoteResultSet> retrieveWithHttpInfo(String indexName, String queryString, String queryId, String matchingModel, String weightingModel, List<String> queryControlNames, List<String> queryControlValues) throws ApiException {
        com.squareup.okhttp.Call call = retrieveValidateBeforeCall(indexName, queryString, queryId, matchingModel, weightingModel, queryControlNames, queryControlValues, null, null);
        Type localVarReturnType = new TypeToken<RemoteResultSet>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * run a query (asynchronously)
     * Runs a query
     * @param indexName Id of the index to be queried (required)
     * @param queryString  (required)
     * @param queryId  (optional)
     * @param matchingModel  (optional)
     * @param weightingModel  (optional)
     * @param queryControlNames  (optional)
     * @param queryControlValues  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call retrieveAsync(String indexName, String queryString, String queryId, String matchingModel, String weightingModel, List<String> queryControlNames, List<String> queryControlValues, final ApiCallback<RemoteResultSet> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = retrieveValidateBeforeCall(indexName, queryString, queryId, matchingModel, weightingModel, queryControlNames, queryControlValues, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<RemoteResultSet>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for stats
     * @param indexName  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call statsCall(String indexName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/index/{indexName}"
                .replaceAll("\\{" + "indexName" + "\\}", apiClient.escapeString(indexName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call statsValidateBeforeCall(String indexName, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {

        // verify the required parameter 'indexName' is set
        if (indexName == null) {
            throw new ApiException("Missing the required parameter 'indexName' when calling stats(Async)");
        }


        com.squareup.okhttp.Call call = statsCall(indexName, progressListener, progressRequestListener);
        return call;

    }

    /**
     * view stats about the specified index
     * View stats about the index
     * @param indexName  (required)
     * @return IndexStats
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public IndexStats stats(String indexName) throws ApiException {
        ApiResponse<IndexStats> resp = statsWithHttpInfo(indexName);
        return resp.getData();
    }

    /**
     * view stats about the specified index
     * View stats about the index
     * @param indexName  (required)
     * @return ApiResponse&lt;IndexStats&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<IndexStats> statsWithHttpInfo(String indexName) throws ApiException {
        com.squareup.okhttp.Call call = statsValidateBeforeCall(indexName, null, null);
        Type localVarReturnType = new TypeToken<IndexStats>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * view stats about the specified index (asynchronously)
     * View stats about the index
     * @param indexName  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call statsAsync(String indexName, final ApiCallback<IndexStats> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = statsValidateBeforeCall(indexName, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<IndexStats>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for status
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call statusCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/status";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call statusValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {


        com.squareup.okhttp.Call call = statusCall(progressListener, progressRequestListener);
        return call;

    }

    /**
     * get current server status
     * Get the current server status, e.g. memory usage\&quot;
     * @return ServerStatus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ServerStatus status() throws ApiException {
        ApiResponse<ServerStatus> resp = statusWithHttpInfo();
        return resp.getData();
    }

    /**
     * get current server status
     * Get the current server status, e.g. memory usage\&quot;
     * @return ApiResponse&lt;ServerStatus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ServerStatus> statusWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = statusValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<ServerStatus>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * get current server status (asynchronously)
     * Get the current server status, e.g. memory usage\&quot;
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call statusAsync(final ApiCallback<ServerStatus> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = statusValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ServerStatus>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
