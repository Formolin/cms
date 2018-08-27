package com.cms.service;

import com.cms.dao.UserDao;
import com.cms.domain.SearchUser;
import com.cms.domain.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    //判断登陆
    public boolean checkUser(User user) {
        return userDao.checkUser(user);
    }

    //获取id
    public int getId(User user) {
        return userDao.getId(user);
    }
    //判断权限
    public String getPower(User user){
        return userDao.getPower(user);
    }
    // 验证用户名是否存在
    public boolean findUserName(User user) {
        return userDao.findUserName(user);

    }

    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public int getRowCount(User user) {
        return userDao.getRowCount(user);
    }

    public int getPageCount(User user,int pageSize) {
        int pageCount = 0;
        int rowCount = userDao.getRowCount(user);
        if (rowCount % pageSize == 0) {
            pageCount = rowCount / pageSize;
        } else {
            pageCount = rowCount / pageSize + 1;
        }
        return pageCount;
    }

    public List<User> findAllUser(int pageNow, int pageSize) {
        return userDao.findAllUser(pageNow,pageSize);
    }

    //复合查询用户
    public List<User> findSearchUser(SearchUser searchUser, int pageNow, int pageSize){
        return userDao.findSearchUser(searchUser,pageNow,pageSize);
    }

    public int getSearchRowCount(SearchUser searchUser) {
        return userDao.getSearchRowCount(searchUser);
    }
    public int getSearchPageCount(SearchUser searchUser,int pageSize) {
        int pageCount = 0;
        int rowCount = getSearchRowCount(searchUser);
        if (rowCount % pageSize == 0) {
            pageCount = rowCount / pageSize;
        } else {
            pageCount = rowCount / pageSize + 1;
        }
        return pageCount;
    }



    // 删除用户
    public boolean deleteUser(int id) {
        return  userDao.deleteUser(id);
    }


    //根据id获取用户信息
    public User getUserInfo(int id){
        return  userDao.getUserInfo(id);
    }
}
