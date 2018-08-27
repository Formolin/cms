package com.cms.domain;

public class SearchUser {
    private String searchUserName;
    private Integer searchDepID;
    private String searchUserCode;
    private String searchUserSex;
    private Integer searchUserAge;
    private Integer searchUserPower;

    public SearchUser() {
    }

    public SearchUser(String searchUserName, Integer searchDepID, String searchUserCode, String searchUserSex, Integer searchUserAge, Integer searchUserPower) {
        this.searchUserName = searchUserName;
        this.searchDepID = searchDepID;
        this.searchUserCode = searchUserCode;
        this.searchUserSex = searchUserSex;
        this.searchUserAge = searchUserAge;
        this.searchUserPower = searchUserPower;
    }

    public String getSearchUserName() {
        return searchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public Integer getSearchDepID() {
        return searchDepID;
    }

    public void setSearchDepID(Integer searchDepID) {
        this.searchDepID = searchDepID;
    }

    public String getSearchUserCode() {
        return searchUserCode;
    }

    public void setSearchUserCode(String searchUserCode) {
        this.searchUserCode = searchUserCode;
    }

    public String getSearchUserSex() {
        return searchUserSex;
    }

    public void setSearchUserSex(String searchUserSex) {
        this.searchUserSex = searchUserSex;
    }

    public Integer getSearchUserAge() {
        return searchUserAge;
    }

    public void setSearchUserAge(Integer searchUserAge) {
        this.searchUserAge = searchUserAge;
    }

    public Integer getSearchUserPower() {
        return searchUserPower;
    }

    public void setSearchUserPower(Integer searchUserPower) {
        this.searchUserPower = searchUserPower;
    }
}
