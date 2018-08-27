package com.cms.domain;


public class News {

	private int id;
	private String title;
	private String content;
	private int typeid;
	private int flag;
	private String creattime;

	public News() {
	}

	public News(int id, String title, String content, int typeid, int flag, String creattime) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.typeid = typeid;
		this.flag = flag;
		this.creattime = creattime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getCreattime() {
		return creattime;
	}

	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
}
