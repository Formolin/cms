package com.cms.dao;

import com.cms.domain.News;
import com.cms.domain.NewsType;
import com.cms.domain.User;
import com.cms.util.ConnDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsTypeDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    // 分页查询所有栏目信息
    public List<NewsType> findAllNewsType(int pageNow, int pageSize) {
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_newstype limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
            preparedStatement = connection.prepareStatement(sql);
            List<NewsType> list = new ArrayList<NewsType>();
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NewsType newsType = new NewsType();
                newsType.setId(rs.getInt("id"));
                newsType.setTypeName(rs.getString("typeName"));
                newsType.setSort(rs.getInt("sort"));
                list.add(newsType);
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

    public int getRowCount() {
        int rowCount = 0;

        try {
            connection = ConnDB.getConnection();
            String sql = "select count(*) as rowCount from tb_newstype";
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


    // 验证栏目sort是否存在
    public boolean findNewsSort(NewsType newsType) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_newstype where sort = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newsType.getSort());
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
}
