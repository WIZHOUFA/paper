package com.fnst.paper.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonListData<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> data = new ArrayList<T>();
	private long count;
	private String message;
	private String statusCode;
	private boolean SUCCESS = true;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isSUCCESS() {
		return SUCCESS;
	}
	public void setSUCCESS(boolean sUCCESS) {
		SUCCESS = sUCCESS;
	}
	
	
}
