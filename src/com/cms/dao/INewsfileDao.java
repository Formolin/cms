package com.cms.dao;

import com.cms.domain.News;
import com.cms.domain.Newsfile;

public interface INewsfileDao {
    /**
     * 添加新闻信息
     */
    public boolean addNews(News news);

    /**
     * 添加新闻附件
     */
    public boolean addNewsfile(Newsfile newsfile);

    /**
     * 获取最后一次添加新闻信息的id
     */
    public int getNewId();
    //根据newsid查询新闻下载附件
    public String getFileName(int newsid);
}
