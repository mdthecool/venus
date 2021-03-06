package com.venus.controller.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import javax.ws.rs.core.Response;

@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonWriteNullProperties(value=false)
public class BaseResponse {
  private Boolean error;
  private Integer errorCode;
  private Integer httpErrorCode;
  private String errorDescription;
  private Integer startIndex;
  private Integer itemsPerPage;
  private Integer totalResults;

  public BaseResponse() {
    this.error = false;
    this.errorCode = 0;
    this.httpErrorCode = Response.Status.OK.getStatusCode();
    this.startIndex = 0;
    this.itemsPerPage = 0;
    this.totalResults = 0;
  }

  public Boolean getError() {
    return error;
  }

  public void setError(Boolean error) {
    this.error = error;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public Integer getHttpErrorCode() {
    return httpErrorCode;
  }

  public void setHttpErrorCode(Integer httpErrorCode) {
    this.httpErrorCode = httpErrorCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public Integer getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }

  public Integer getItemsPerPage() {
    return itemsPerPage;
  }

  public void setItemsPerPage(Integer itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

}