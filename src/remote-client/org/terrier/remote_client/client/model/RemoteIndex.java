package org.terrier.remote_client.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * RemoteIndex
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-02-03T11:07:29.227Z")
public class RemoteIndex {
  @SerializedName("path")
  private String path = null;

  @SerializedName("prefix")
  private String prefix = null;

  @SerializedName("indexName")
  private String indexName = null;

  @SerializedName("props")
  private List<KeyValue> props = new ArrayList<KeyValue>();

  public RemoteIndex path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
   **/
  @ApiModelProperty(example = "C:/path/to/index", required = true, value = "")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public RemoteIndex prefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  /**
   * Get prefix
   * @return prefix
   **/
  @ApiModelProperty(example = "data", required = true, value = "")
  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public RemoteIndex indexName(String indexName) {
    this.indexName = indexName;
    return this;
  }

  /**
   * the name under which the index will be stored and accessed
   * @return indexName
   **/
  @ApiModelProperty(example = "firstIndex", required = true, value = "the name under which the index will be stored and accessed")
  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public RemoteIndex props(List<KeyValue> props) {
    this.props = props;
    return this;
  }

  public RemoteIndex addPropsItem(KeyValue propsItem) {
    this.props.add(propsItem);
    return this;
  }

  /**
   * Get props
   * @return props
   **/
  @ApiModelProperty(required = true, value = "")
  public List<KeyValue> getProps() {
    return props;
  }

  public void setProps(List<KeyValue> props) {
    this.props = props;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RemoteIndex remoteIndex = (RemoteIndex) o;
    return Objects.equals(this.path, remoteIndex.path) &&
            Objects.equals(this.prefix, remoteIndex.prefix) &&
            Objects.equals(this.indexName, remoteIndex.indexName) &&
            Objects.equals(this.props, remoteIndex.props);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, prefix, indexName, props);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RemoteIndex {\n");

    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
    sb.append("    indexName: ").append(toIndentedString(indexName)).append("\n");
    sb.append("    props: ").append(toIndentedString(props)).append("\n");
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

