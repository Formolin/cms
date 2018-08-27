package com.cms.domain;

public class User {
    private Integer id;
    private Integer depID;
    private String userName;
    private String userPwd;
    private String userCode;
    private String userSex;
    private Integer userAge;
    private Integer userPower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepID() {
        return depID;
    }

    public void setDepID(Integer depID) {
        this.depID = depID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserPower() {
        return userPower;
    }

    public void setUserPower(Integer userPower) {
        this.userPower = userPower;
    }

    public User() {
    }

    public User(Integer id, Integer depID, String userName, String userPwd, String userCode, String userSex, Integer userAge, Integer userPower) {
        this.id = id;
        this.depID = depID;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userCode = userCode;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userPower = userPower;
    }
}
