package com.cms.dao;

import com.cms.domain.News;
import com.cms.domain.SearchNews;
import com.cms.domain.SearchUser;
import com.cms.domain.User;
import com.cms.util.ConnDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public int getRowCount() {
        int rowCount = 0;

        try {
            connection = ConnDB.getConnection();
            String sql = "select count(*) as rowCount from tb_news";
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
    public List<News> findAllNews(int pageNow, int pageSize) {
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_news order by creattime desc limit " + (pageNow * pageSize - pageSize) + "," + pageSize;
            preparedStatement = connection.prepareStatement(sql);
            List<News> list = new ArrayList<News>();
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                News news = new News(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getInt("typeid"),rs.getInt("flag"),rs.getString("creattime"));
                list.add(news);
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

    //复合查询所有新闻消息
//    private int searchID;
//    private String searchTitle;
//    private int searchTypeid;
//    private int searchFlag;
//    private String searchStartcreattime;
//    private String searchEndcreattime;
    public List<News> findSearchNews(SearchNews searchNews, int pageNow, int pageSize) {
        String searchTitle = searchNews.getSearchTitle();
        int searchTypeid = searchNews.getSearchTypeid();
        int searchFlag = searchNews.getSearchFlag();
        String searchStartcreattime = searchNews.getSearchStartcreattime();
        String searchEndcreattime = searchNews.getSearchEndcreattime();
        StringBuilder stringBuilder = new StringBuilder("select * from tb_news where 1=1");

        if (!"".equals(searchTitle)){
            stringBuilder.append(" and title like '"+searchTitle+"%'");
        }
        if (searchTypeid != -1){
            stringBuilder.append(" and typeid='"+searchTypeid+"'");
        }
        if (searchFlag != -1){
            stringBuilder.append(" and flag='"+searchFlag+"'");
        }
        if (!"".equals(searchStartcreattime)){
            stringBuilder.append(" and creattime between '"+searchStartcreattime+"'");
        }
        if (!"".equals(searchEndcreattime)){
            stringBuilder.append(" and '"+searchEndcreattime+"'");
        }
        //select * from tb_news where creattime BETWEEN '2017-03-15' and '2018-06-19' order by creattime DESC limit 0,5
        stringBuilder.append(" order by creattime DESC limit "+(pageNow * pageSize - pageSize) + "," + pageSize);
//        System.out.println("findearchUser="+stringBuilder.toString());

        try {
            connection = ConnDB.getConnection();
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            rs = preparedStatement.executeQuery();
            List<News> list = new ArrayList<News>();
            while (rs.next()){
                News news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setTypeid(rs.getInt("typeid"));
                news.setFlag(rs.getInt("flag"));
                news.setCreattime(rs.getString("creattime"));
                list.add(news);
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
    public int getNewsRowCount(SearchNews searchNews) {
        int rowCount = 0;
        String searchTitle = searchNews.getSearchTitle();
        int searchTypeid = searchNews.getSearchTypeid();
        int searchFlag = searchNews.getSearchFlag();
        String searchStartcreattime = searchNews.getSearchStartcreattime();
        String searchEndcreattime = searchNews.getSearchEndcreattime();
        StringBuilder stringBuilder = new StringBuilder("select count(*) as rowCount from tb_news where 1=1");
//select count(*) from tb_news where creattime BETWEEN '2017-03-15' and '2018-06-19'
//        select count(*) from tb_news where flag = '1'
        if (!"".equals(searchTitle)){
            stringBuilder.append(" and title='"+searchTitle+"'");
        }
        if (searchTypeid != -1){
            stringBuilder.append(" and typeid='"+searchTypeid+"'");
        }
        if (searchFlag != -1){
            stringBuilder.append(" and flag='"+searchFlag+"'");
        }
        if (!"".equals(searchStartcreattime)){
            stringBuilder.append(" and creattime between '"+searchStartcreattime+"'");
        }
        if (!"".equals(searchEndcreattime)){
            stringBuilder.append(" and '"+searchEndcreattime+"'");
        }


        try {
            connection = ConnDB.getConnection();
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
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

    public boolean addNews(News news) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "insert into tb_news values(default,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getTypeid());
            preparedStatement.setInt(4, news.getFlag());
            preparedStatement.setString(5, news.getCreattime());

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

    //删除新闻
    public boolean deleteNews(int id){
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "delete from tb_news where id = ?";
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

    //根据id获取news信息
    public News getNewsInfo(int id){
        try {
            connection = ConnDB.getConnection();
            String sql = "select * from tb_news where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            News news = null;
            if (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setTypeid(rs.getInt("typeid"));
                news.setFlag(rs.getInt("flag"));
                news.setCreattime(rs.getString("creattime"));
            }
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            ConnDB.closeDB(connection,preparedStatement,rs);
        }
    }
//    修改信息
    public boolean updateNews(News news) {
        boolean flag = false;
        try {
            connection = ConnDB.getConnection();
            String sql = "update tb_news set title = ?,content = ?,typeid = ?,flag = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getTypeid());
            preparedStatement.setInt(4, news.getFlag());
            preparedStatement.setInt(5, news.getId());
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


}

