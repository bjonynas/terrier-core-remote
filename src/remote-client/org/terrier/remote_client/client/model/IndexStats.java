package org.terrier.remote_client.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

/**
 * IndexStats
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-12T08:12:17.889Z")
public class IndexStats {
  @SerializedName("numberOfDocuments")
  private Integer numberOfDocuments = null;

  @SerializedName("numberOfTerms")
  private Integer numberOfTerms = null;

  @SerializedName("numberOfTokens")
  private Long numberOfTokens = null;

  @SerializedName("numberOfPointers")
  private Long numberOfPointers = null;

  public IndexStats numberOfDocuments(Integer numberOfDocuments) {
    this.numberOfDocuments = numberOfDocuments;
    return this;
  }

   /**
   * Get numberOfDocuments
   * @return numberOfDocuments
  **/
  @ApiModelProperty(value = "")
  public Integer getNumberOfDocuments() {
    return numberOfDocuments;
  }

  public void setNumberOfDocuments(Integer numberOfDocuments) {
    this.numberOfDocuments = numberOfDocuments;
  }

  public IndexStats numberOfTerms(Integer numberOfTerms) {
    this.numberOfTerms = numberOfTerms;
    return this;
  }

   /**
   * Get numberOfTerms
   * @return numberOfTerms
  **/
  @ApiModelProperty(value = "")
  public Integer getNumberOfTerms() {
    return numberOfTerms;
  }

  public void setNumberOfTerms(Integer numberOfTerms) {
    this.numberOfTerms = numberOfTerms;
  }

  public IndexStats numberOfTokens(Long numberOfTokens) {
    this.numberOfTokens = numberOfTokens;
    return this;
  }

   /**
   * Get numberOfTokens
   * @return numberOfTokens
  **/
  @ApiModelProperty(value = "")
  public Long getNumberOfTokens() {
    return numberOfTokens;
  }

  public void setNumberOfTokens(Long numberOfTokens) {
    this.numberOfTokens = numberOfTokens;
  }

  public IndexStats numberOfPointers(Long numberOfPointers) {
    this.numberOfPointers = numberOfPointers;
    return this;
  }

   /**
   * Get numberOfPointers
   * @return numberOfPointers
  **/
  @ApiModelProperty(value = "")
  public Long getNumberOfPointers() {
    return numberOfPointers;
  }

  public void setNumberOfPointers(Long numberOfPointers) {
    this.numberOfPointers = numberOfPointers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndexStats indexStats = (IndexStats) o;
    return Objects.equals(this.numberOfDocuments, indexStats.numberOfDocuments) &&
        Objects.equals(this.numberOfTerms, indexStats.numberOfTerms) &&
        Objects.equals(this.numberOfTokens, indexStats.numberOfTokens) &&
        Objects.equals(this.numberOfPointers, indexStats.numberOfPointers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfDocuments, numberOfTerms, numberOfTokens, numberOfPointers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndexStats {\n");
    
    sb.append("    numberOfDocuments: ").append(toIndentedString(numberOfDocuments)).append("\n");
    sb.append("    numberOfTerms: ").append(toIndentedString(numberOfTerms)).append("\n");
    sb.append("    numberOfTokens: ").append(toIndentedString(numberOfTokens)).append("\n");
    sb.append("    numberOfPointers: ").append(toIndentedString(numberOfPointers)).append("\n");
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

