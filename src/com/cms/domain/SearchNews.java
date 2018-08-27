package com.cms.domain;

public class SearchNews {
    private int searchID;
    private String searchTitle;
    private int searchTypeid;
    private int searchFlag;
    private String searchStartcreattime;
    private String searchEndcreattime;

    public SearchNews() {
    }

    public SearchNews(int searchID, String searchTitle, int searchTypeid, int searchFlag, String searchStartcreattime, String searchEndcreattime) {
        this.searchID = searchID;
        this.searchTitle = searchTitle;
        this.searchTypeid = searchTypeid;
        this.searchFlag = searchFlag;
        this.searchStartcreattime = searchStartcreattime;
        this.searchEndcreattime = searchEndcreattime;
    }

    public int getSearchID() {
        return searchID;
    }

    public void setSearchID(int searchID) {
        this.searchID = searchID;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public int getSearchTypeid() {
        return searchTypeid;
    }

    public void setSearchTypeid(int searchTypeid) {
        this.searchTypeid = searchTypeid;
    }

    public int getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(int searchFlag) {
        this.searchFlag = searchFlag;
    }

    public String getSearchStartcreattime() {
        return searchStartcreattime;
    }

    public void setSearchStartcreattime(String searchStartcreattime) {
        this.searchStartcreattime = searchStartcreattime;
    }

    public String getSearchEndcreattime() {
        return searchEndcreattime;
    }

    public void setSearchEndcreattime(String searchEndcreattime) {
        this.searchEndcreattime = searchEndcreattime;
    }
}
