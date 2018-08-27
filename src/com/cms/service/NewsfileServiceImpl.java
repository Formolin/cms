package com.cms.service;

import com.cms.dao.INewsfileDao;
import com.cms.dao.NewsfileDaoImpl;
import com.cms.domain.News;
import com.cms.domain.Newsfile;

public class NewsfileServiceImpl implements INewsfileService {
    INewsfileDao newsfileDao = new NewsfileDaoImpl();

    @Override
    public int getAddNewsLastId(News news) {
        System.out.println("调用了service的getAddNewsLastId");
        newsfileDao.addNews(news);
//        int i = 10/0;
        int newId = newsfileDao.getNewId();
        return newId;
    }

    @Override
    public void addNewsfile(Newsfile newsfile) {
        System.out.println("调用了service的addNewsfile");
        newsfileDao.addNewsfile(newsfile);
    }

    @Override
    public String getFileName(int newsid) {
        return newsfileDao.getFileName(newsid);
    }
}
