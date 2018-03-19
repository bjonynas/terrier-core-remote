package org.terrier.remote_client.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.terrier.remote_client.client.model.ResultDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * RemoteResultSet
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-03-02T08:10:51.350Z")
public class RemoteResultSet {
  @SerializedName("documents")
  private List<ResultDocument> documents = null;

  @SerializedName("resultSize")
  private Integer resultSize = null;

  public RemoteResultSet documents(List<ResultDocument> documents) {
    this.documents = documents;
    return this;
  }

  public RemoteResultSet addDocumentsItem(ResultDocument documentsItem) {
    if (this.documents == null) {
      this.documents = new ArrayList<ResultDocument>();
    }
    this.documents.add(documentsItem);
    return this;
  }

   /**
   * Get documents
   * @return documents
  **/
  @ApiModelProperty(value = "")
  public List<ResultDocument> getDocuments() {
    return documents;
  }

  public void setDocuments(List<ResultDocument> documents) {
    this.documents = documents;
  }

  public RemoteResultSet resultSize(Integer resultSize) {
    this.resultSize = resultSize;
    return this;
  }

   /**
   * Get resultSize
   * @return resultSize
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getResultSize() {
    return resultSize;
  }

  public void setResultSize(Integer resultSize) {
    this.resultSize = resultSize;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RemoteResultSet remoteResultSet = (RemoteResultSet) o;
    return Objects.equals(this.documents, remoteResultSet.documents) &&
        Objects.equals(this.resultSize, remoteResultSet.resultSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documents, resultSize);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RemoteResultSet {\n");
    
    sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
    sb.append("    resultSize: ").append(toIndentedString(resultSize)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

