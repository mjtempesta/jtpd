package org.jtpd.domain.model;

import java.util.ArrayList;
import java.util.List;

// TODO Burasi kullanýlýyor mu? 
public class ListObject {

	
	private int id ;
	 
	private String startDate;
	
	private String endDate ;
	
	private String owner ;
	
	private String trainingName ;
	
	private List subList = new ArrayList();

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public List getSubList() {
		return subList;
	}

	public void setSubList(List subList) {
		this.subList = subList;
	}
	
	public void addSubList(SubList sl) {
		this.subList.add(sl);
	}
	
	
}
