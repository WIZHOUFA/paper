package com.fnst.paper.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChartData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private List<String> legend = new ArrayList<String>();
	
	private List<String> xAxis = new ArrayList<String>();
	
	private List<String> yAxis = new ArrayList<String>();
	
	private ChartSeries chartSeries;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getLegend() {
		return legend;
	}

	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<String> getyAxis() {
		return yAxis;
	}

	public void setyAxis(List<String> yAxis) {
		this.yAxis = yAxis;
	}

	public ChartSeries getChartSeries() {
		return chartSeries;
	}

	public void setChartSeries(ChartSeries chartSeries) {
		this.chartSeries = chartSeries;
	}

	
	
	
}
