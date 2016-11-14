package com.fnst.paper.util;

import java.io.Serializable;

public class JsonData<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data;
	private String message;
	private HttpStatusCode statusCode;
	private boolean SUCCESS = true;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatusCode getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatusCode statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isSUCCESS() {
		return SUCCESS;
	}
	public void setSUCCESS(boolean sUCCESS) {
		SUCCESS = sUCCESS;
	}
}
