package org.terrier.remote_client.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ServerStatus
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-02-23T03:14:21.703Z")
public class ServerStatus {
  @SerializedName("usedMemory")
  private String usedMemory = null;

  @SerializedName("availableMemory")
  private String availableMemory = null;

  @SerializedName("numberOfIndexes")
  private String numberOfIndexes = null;

  public ServerStatus usedMemory(String usedMemory) {
    this.usedMemory = usedMemory;
    return this;
  }

   /**
   * Get usedMemory
   * @return usedMemory
  **/
  @ApiModelProperty(value = "")
  public String getUsedMemory() {
    return usedMemory;
  }

  public void setUsedMemory(String usedMemory) {
    this.usedMemory = usedMemory;
  }

  public ServerStatus availableMemory(String availableMemory) {
    this.availableMemory = availableMemory;
    return this;
  }

   /**
   * Get availableMemory
   * @return availableMemory
  **/
  @ApiModelProperty(value = "")
  public String getAvailableMemory() {
    return availableMemory;
  }

  public void setAvailableMemory(String availableMemory) {
    this.availableMemory = availableMemory;
  }

  public ServerStatus numberOfIndexes(String numberOfIndexes) {
    this.numberOfIndexes = numberOfIndexes;
    return this;
  }

   /**
   * nmber of imported indexes
   * @return numberOfIndexes
  **/
  @ApiModelProperty(value = "nmber of imported indexes")
  public String getNumberOfIndexes() {
    return numberOfIndexes;
  }

  public void setNumberOfIndexes(String numberOfIndexes) {
    this.numberOfIndexes = numberOfIndexes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerStatus serverStatus = (ServerStatus) o;
    return Objects.equals(this.usedMemory, serverStatus.usedMemory) &&
        Objects.equals(this.availableMemory, serverStatus.availableMemory) &&
        Objects.equals(this.numberOfIndexes, serverStatus.numberOfIndexes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(usedMemory, availableMemory, numberOfIndexes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerStatus {\n");
    
    sb.append("    usedMemory: ").append(toIndentedString(usedMemory)).append("\n");
    sb.append("    availableMemory: ").append(toIndentedString(availableMemory)).append("\n");
    sb.append("    numberOfIndexes: ").append(toIndentedString(numberOfIndexes)).append("\n");
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

