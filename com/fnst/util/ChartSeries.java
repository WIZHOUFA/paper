package com.fnst.paper.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChartSeries implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String type;
	
	private List<Integer> data = new ArrayList<Integer>();
    
	public ChartSeries() {
		super();
	}

	public ChartSeries(String name, String type, List<Integer> data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	
}
