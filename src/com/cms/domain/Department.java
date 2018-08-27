package com.cms.domain;


public class Department {

	private int id;
	private String depName;
	private String depCreateTime;
	private int sort;

	public Department() {
	}

	public Department(int id, String depName, String depCreateTime, int sort) {
		this.id = id;
		this.depName = depName;
		this.depCreateTime = depCreateTime;
		this.sort = sort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepCreateTime() {
		return depCreateTime;
	}

	public void setDepCreateTime(String depCreateTime) {
		this.depCreateTime = depCreateTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
