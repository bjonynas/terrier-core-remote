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
import java.util.ArrayList;
import java.util.List;

/**
 * Metadata
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-03-02T08:26:14.940Z")
public class Metadata {
  @SerializedName("metaKeys")
  private List<String> metaKeys = new ArrayList<String>();

  @SerializedName("metaItems")
  private List<String> metaItems = new ArrayList<String>();

  public Metadata metaKeys(List<String> metaKeys) {
    this.metaKeys = metaKeys;
    return this;
  }

  public Metadata addMetaKeysItem(String metaKeysItem) {
    this.metaKeys.add(metaKeysItem);
    return this;
  }

   /**
   * Get metaKeys
   * @return metaKeys
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getMetaKeys() {
    return metaKeys;
  }

  public void setMetaKeys(List<String> metaKeys) {
    this.metaKeys = metaKeys;
  }

  public Metadata metaItems(List<String> metaItems) {
    this.metaItems = metaItems;
    return this;
  }

  public Metadata addMetaItemsItem(String metaItemsItem) {
    this.metaItems.add(metaItemsItem);
    return this;
  }

   /**
   * Get metaItems
   * @return metaItems
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getMetaItems() {
    return metaItems;
  }

  public void setMetaItems(List<String> metaItems) {
    this.metaItems = metaItems;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(this.metaKeys, metadata.metaKeys) &&
        Objects.equals(this.metaItems, metadata.metaItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metaKeys, metaItems);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    metaKeys: ").append(toIndentedString(metaKeys)).append("\n");
    sb.append("    metaItems: ").append(toIndentedString(metaItems)).append("\n");
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

