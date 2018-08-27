package com.cms.dao;

import com.cms.domain.News;
import com.cms.domain.Newsfile;
import com.cms.util.C3P0Util;
import com.cms.util.ManagerThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsfileDaoImpl implements INewsfileDao {


    @Override
    public boolean addNews(News news) {
        System.out.println("调用了addnews");
        boolean flag = false;
        PreparedStatement ps = null;
        try {

            String sql = "insert into tb_news values(default,?,?,?,?,?)";
             ps = ManagerThreadLocal.getConnection().prepareStatement(sql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setInt(3, news.getTypeid());
            ps.setInt(4, news.getFlag());
            ps.setString(5, news.getCreattime());

            int num = ps.executeUpdate();
            if (num == 1) {
                // 添加成功
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            C3P0Util.release(null,ps,null);
        }


    }

    @Override
    public boolean addNewsfile(Newsfile newsfile) {
        boolean flag = false;
        PreparedStatement ps = null;
        String sql = "insert into tb_newsfile values(default,?,?)";
        try {

            ps = ManagerThreadLocal.getConnection().prepareStatement(sql);
            ps.setInt(1,newsfile.getNewsid());
            System.out.println("newsfile.getNewsid()=="+newsfile.getNewsid());
            ps.setString(2,newsfile.getFilename());
            int i = ps.executeUpdate();
            if (i == 1){
                flag = true;
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            C3P0Util.release(null,ps,null);
        }
    }

    @Override
    public int getNewId() {
        System.out.println("调用了getnewid");
        int newid = 0;
        //select LAST_INSERT_ID();
        String sql = "select LAST_INSERT_ID() as newid";
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        try {

            ps = ManagerThreadLocal.getConnection().prepareStatement(sql);
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                newid = resultSet.getInt("newid");
            }
            return newid;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询最后添加信息的id失败");
        }finally {
            C3P0Util.release(null,ps,resultSet);
        }
    }

    @Override
    public String getFileName(int newsid) {
        String filename ="";
        String sql = "select * from tb_newsfile where newsid = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        try {
            connection = C3P0Util.getConnection();
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,newsid);
             resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                filename = resultSet.getString("filename");
            }
            return filename;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            C3P0Util.release(connection,preparedStatement,resultSet);
        }


    }


}
