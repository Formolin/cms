package com.cms.dao;

import com.cms.domain.SearchUser;
import com.cms.domain.User;
import com.cms.util.ConnDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    //判断登陆
    public boolean checkUser(User user) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            preparedStatement = connection.prepareStatement("select * from tb_users where userName = ? and userPwd = ?;");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }


    }

    // 验证用户名是否存在
    public boolean findUserName(User user) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_users where userName = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                // 登陆成功
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }

    }

    //获取id
    public int getId(User user) {
        int id = 0;
        try {
            connection = ConnDB.getConnection();
            String sql = "select id from tb_users where userName=? and userPwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    //获取power
    public String getPower(User user) {
        // TODO Auto-generated method stub
        int userPower = 0;
        String power = null;
        try {
            connection = ConnDB.getConnection();
            String sql = "select userPower from tb_users where userName = ? and userPwd = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPwd());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userPower = rs.getInt("userPower");
            }
            if (userPower == 0) {
                power = "普通管理员";
            } else {
                power = "超级管理员";
            }
            return power;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }

    }


    // 添加用户
    public boolean addUser(User user) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "insert into tb_users values(default,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getDepID());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPwd());
            preparedStatement.setString(4, user.getUserCode());
            preparedStatement.setString(5, user.getUserSex());
            preparedStatement.setInt(6, user.getUserAge());
            preparedStatement.setInt(7, user.getUserPower());
            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                // 添加成功
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }
    }

    // 修改登陆用户
    public boolean updateUser(User user) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "update tb_users set depID = ?,userName = ?,userPwd = ?,userCode = ?,userSex = ?,userAge = ?,userPower = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getDepID());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPwd());
            preparedStatement.setString(4, user.getUserCode());
            preparedStatement.setString(5, user.getUserSex());
            preparedStatement.setInt(6, user.getUserAge());
            preparedStatement.setInt(7, user.getUserPower());
            preparedStatement.setInt(8, user.getId());
            if (preparedStatement.executeUpdate() == 1) {
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }
    }

    public int getRowCount(User user) {
        int rowCount = 0;
        int depID = user.getDepID();
        String userName = user.getUserName();
        String userCode = user.getUserCode();
        String userSex = user.getUserSex();
        int userAge = user.getUserAge();
        int userPower = user.getUserPower();
        //where and
        String where = "";
        boolean isAnd = false;
        //depID
        String byDepID = "";
        String isAndDepID = "";

        if (depID != -1) {
            byDepID = " depID=" + depID;
            where = " where";
            if (isAnd) {
                isAndDepID = " and";
            }
            isAnd = true;
        }
        //userPower
        String byUserPower = "";
        String isAndUserPower = "";
        if (userPower != -1) {
            byUserPower = " userPower=" + userPower;
            where = " where";
            if (isAnd) {
                isAndUserPower = " and";
            }
            isAnd = true;
        }
        //userName
        String byUserName = "";
        String isAndUserName = "";
        if (!"".equals(userName)) {
            byUserName = " userName='" + userName + "'";
            where = " where";
            if (isAnd) {
                isAndUserName = " and";
            }
            isAnd = true;
        }
        //userCode
        String byUserCode = "";
        String isAndUserCode = "";
        if (!"".equals(userCode)) {
            byUserCode = " userCode='" + userCode + "'";
            where = " where";
            if (isAnd) {
                isAndUserCode = " and";
            }
            isAnd = true;
        }
        //userSex
        String byUserSex = "";
        String isAndUserSex = "";
        if (!"".equals(userSex)) {
            byUserSex = " userSex='" + userSex + "'";
            where = " where";
            if (isAnd) {
                isAndUserSex = " and";
            }
            isAnd = true;
        }


        //Age
        String byUserAge = "";
        String isAndUserAge = "";
        if (userAge != -1) {
            byUserAge = " userAge" + userAge;
            where = " where";
            if (isAnd) {
                isAndUserAge = " and";
            }
            isAnd = true;
        }

//        System.out.println("select count(*) as rowCount from tb_users" + where + isAndDepID + byDepID + isAndUserPower + byUserPower + isAndUserName + byUserName + isAndUserCode + byUserCode + isAndUserSex + byUserSex + isAndUserAge + byUserAge);

        try {
            connection = ConnDB.getConnection();
            String sql = "select count(*) as rowCount from tb_users" + where + isAndDepID + byDepID + isAndUserPower + byUserPower + isAndUserName + byUserName + isAndUserCode + byUserCode + isAndUserSex + byUserSex + isAndUserAge + byUserAge;
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt("rowCount");
            }

            return rowCount;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }
    }


    // 分页查询所有用户信息
    public List<User> findAllUser(int pageNow, int pageSize) {
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_users order by id desc limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
            preparedStatement = connection.prepareStatement(sql);
            List<User> list = new ArrayList<User>();
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setDepID(rs.getInt("depID"));
                user.setUserName(rs.getString("userName"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserSex(rs.getString("userSex"));
                user.setUserAge(rs.getInt("userAge"));
                user.setUserPower(rs.getInt("userPower"));
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }
    }

    //复合查询所有用户新消息
    public List<User> findSearchUser(SearchUser searchUser, int pageNow, int pageSize) {
        int depID = searchUser.getSearchDepID();
        String userName = searchUser.getSearchUserName();
        String userCode = searchUser.getSearchUserCode();
        String userSex = searchUser.getSearchUserSex();
        int userAge = searchUser.getSearchUserAge();
        int userPower = searchUser.getSearchUserPower();
        StringBuilder stringBuilder = new StringBuilder("select * from tb_users where 1=1");
        if (depID != -1){
            stringBuilder.append(" and depId='"+depID+"'");
        }
        if (!"".equals(userName)){
            stringBuilder.append(" and userName like '"+userName+"%'");
        }
        if (!"".equals(userCode)){
            stringBuilder.append(" and userCode='"+userCode+"'");
        }
        if (!"".equals(userSex)){
            stringBuilder.append(" and userSex='"+userSex+"'");
        }
        if (userAge != -1){
            stringBuilder.append(" and userAge='"+userAge+"'");
        }
        if (userPower != -1){
            stringBuilder.append(" and userPower='"+userPower+"'");
        }
        stringBuilder.append(" limit "+(pageNow * pageSize - pageSize) + "," + pageSize);
//        System.out.println("findearchUser="+stringBuilder.toString());

        try {
            connection = ConnDB.getConnection();
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            rs = preparedStatement.executeQuery();
            List<User> list = new ArrayList<User>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setDepID(rs.getInt("depID"));
                user.setUserName(rs.getString("userName"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserSex(rs.getString("userSex"));
                user.setUserAge(rs.getInt("userAge"));
                user.setUserPower(rs.getInt("userPower"));
                user.setUserPwd(rs.getString("userPwd"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException();

        }finally {
            ConnDB.closeDB(connection,preparedStatement,rs);
        }
    }
    //复合查询
    public int getSearchRowCount(SearchUser searchUser) {
        int rowCount = 0;
        int depID = searchUser.getSearchDepID();
        String userName = searchUser.getSearchUserName();
        String userCode = searchUser.getSearchUserCode();
        String userSex = searchUser.getSearchUserSex();
        int userAge = searchUser.getSearchUserAge();
        int userPower = searchUser.getSearchUserPower();
        //where and
        String where = "";
        boolean isAnd = false;
        //depID
        String byDepID = "";
        String isAndDepID = "";

        if (depID != -1) {
            byDepID = " depID=" + depID;
            where = " where";
            if (isAnd) {
                isAndDepID = " and";
            }
            isAnd = true;
        }
        //userPower
        String byUserPower = "";
        String isAndUserPower = "";
        if (userPower != -1) {
            byUserPower = " userPower=" + userPower;
            where = " where";
            if (isAnd) {
                isAndUserPower = " and";
            }
            isAnd = true;
        }
        //userName
        String byUserName = "";
        String isAndUserName = "";
        if (!"".equals(userName)) {
            byUserName = " userName='" + userName + "'";
            where = " where";
            if (isAnd) {
                isAndUserName = " and";
            }
            isAnd = true;
        }
        //userCode
        String byUserCode = "";
        String isAndUserCode = "";
        if (!"".equals(userCode)) {
            byUserCode = " userCode='" + userCode + "'";
            where = " where";
            if (isAnd) {
                isAndUserCode = " and";
            }
            isAnd = true;
        }
        //userSex
        String byUserSex = "";
        String isAndUserSex = "";
        if (!"".equals(userSex)) {
            byUserSex = " userSex='" + userSex + "'";
            where = " where";
            if (isAnd) {
                isAndUserSex = " and";
            }
            isAnd = true;
        }


        //Age
        String byUserAge = "";
        String isAndUserAge = "";
        if (userAge != -1) {
            byUserAge = " userAge=" + userAge;
            where = " where";
            if (isAnd) {
                isAndUserAge = " and";
            }
            isAnd = true;
        }

//        System.out.println("复合查询----select count(*) as rowCount from tb_users" + where + isAndDepID + byDepID + isAndUserPower + byUserPower + isAndUserName + byUserName + isAndUserCode + byUserCode + isAndUserSex + byUserSex + isAndUserAge + byUserAge);

        try {
            connection = ConnDB.getConnection();
            String sql = "select count(*) as rowCount from tb_users" + where + isAndDepID + byDepID + isAndUserPower + byUserPower + isAndUserName + byUserName + isAndUserCode + byUserCode + isAndUserSex + byUserSex + isAndUserAge + byUserAge;
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rowCount = rs.getInt("rowCount");
            }

            return rowCount;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }
    }



    // 删除用户
    public boolean deleteUser(int id) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "delete from tb_users where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int num = preparedStatement.executeUpdate();
            if (num == 1) {
                // 删除成功
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            ConnDB.closeDB(connection, preparedStatement, rs);
        }

    }

    //根据id获取用户信息
    public User getUserInfo(int id){
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_users where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getInt("id"),rs.getInt("depID"),rs.getString("userName"),rs.getString("userPwd"),rs.getString("userCode"),rs.getString("userSex"),rs.getInt("userAge"),rs.getInt("userPower"));

            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            ConnDB.closeDB(connection,preparedStatement,rs);
        }
    }


}

