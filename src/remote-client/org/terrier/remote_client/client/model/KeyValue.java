package org.terrier.remote_client.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

/**
 * KeyValue
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-01-12T08:12:17.889Z")
public class KeyValue {
  @SerializedName("propName")
  private String propName = null;

  @SerializedName("propValue")
  private String propValue = null;

  public KeyValue propName(String propName) {
    this.propName = propName;
    return this;
  }

   /**
   * Get propName
   * @return propName
  **/
  @ApiModelProperty(value = "")
  public String getPropName() {
    return propName;
  }

  public void setPropName(String propName) {
    this.propName = propName;
  }

  public KeyValue propValue(String propValue) {
    this.propValue = propValue;
    return this;
  }

   /**
   * Get propValue
   * @return propValue
  **/
  @ApiModelProperty(value = "")
  public String getPropValue() {
    return propValue;
  }

  public void setPropValue(String propValue) {
    this.propValue = propValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyValue keyValue = (KeyValue) o;
    return Objects.equals(this.propName, keyValue.propName) &&
        Objects.equals(this.propValue, keyValue.propValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(propName, propValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyValue {\n");
    
    sb.append("    propName: ").append(toIndentedString(propName)).append("\n");
    sb.append("    propValue: ").append(toIndentedString(propValue)).append("\n");
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

