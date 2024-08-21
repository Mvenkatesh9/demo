package com.clinivapps.model;

import java.util.ArrayList;

public class VisitReportModel {
	private Integer openCount;
	private Integer completedCount;
	private Integer ongoingCount;
	private Integer maxCount;
	private String[] visitNames;
	private String[] openCountValues;
	private String[] ongoingCountValues;
	private String[] completedCountValues;
	
	public Integer getOpenCount() {
		return openCount;
	}
	public void setOpenCount(Integer openCount) {
		this.openCount = openCount;
	}
	public Integer getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Integer completedCount) {
		this.completedCount = completedCount;
	}
	public Integer getOngoingCount() {
		return ongoingCount;
	}
	public void setOngoingCount(Integer ongoingCount) {
		this.ongoingCount = ongoingCount;
	}
	public Integer getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public String[] getVisitNames() {
		return visitNames;
	}
	public void setVisitNames(String[] visitNames) {
		this.visitNames = visitNames;
	}
	public String[] getOpenCountValues() {
		return openCountValues;
	}
	public void setOpenCountValues(String[] openCountValues) {
		this.openCountValues = openCountValues;
	}
	public String[] getOngoingCountValues() {
		return ongoingCountValues;
	}
	public void setOngoingCountValues(String[] ongoingCountValues) {
		this.ongoingCountValues = ongoingCountValues;
	}
	public String[] getCompletedCountValues() {
		return completedCountValues;
	}
	public void setCompletedCountValues(String[] completedCountValues) {
		this.completedCountValues = completedCountValues;
	}
	
	
	
}
