package com.cms.service;

import com.cms.dao.NewsDao;
import com.cms.domain.News;
import com.cms.domain.SearchNews;

import java.util.List;

public class NewsService {
   NewsDao newsDao = new NewsDao();
    public int getRowCount() {
        return newsDao.getRowCount();
    }
    public int getPageCount(int pageSize){
        int pageCount = 0;
        int rowCount = getRowCount();
        if (rowCount % pageSize == 0) {
            pageCount = rowCount / pageSize;
        } else {
            pageCount = rowCount / pageSize + 1;
        }
        return pageCount;
    }

    // 分页查询所有用户信息
    public List<News> findAllNews(int pageNow, int pageSize) {
        return newsDao.findAllNews(pageNow,pageSize);
    }
    //复合查询 public int getNewsRowCount(SearchNews searchNews) {
    public int getNewsRowCount(SearchNews searchNews) {
        return  newsDao.getNewsRowCount(searchNews);
    }
    public int getNewsPageCount(SearchNews searchNews,int pageSize){
        int pageCount = 0;
        int rowCount = getNewsRowCount(searchNews);
        if (rowCount % pageSize == 0) {
            pageCount = rowCount / pageSize;
        } else {
            pageCount = rowCount / pageSize + 1;
        }
        return pageCount;
    }
    public List<News> findSearchNews(SearchNews searchNews, int pageNow, int pageSize) {
        return newsDao.findSearchNews(searchNews,pageNow,pageSize);
    }


    public boolean addNews(News news) {
        return newsDao.addNews(news);
    }


    //删除新闻
    public boolean deleteNews(int id){
        return newsDao.deleteNews(id);
    }

    //根据id获取news信息
    public News getNewsInfo(int id){
        return newsDao.getNewsInfo(id);
    }

    public boolean updateNews(News news) {
        return newsDao.updateNews(news);
    }























}
