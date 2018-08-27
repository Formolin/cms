package com.cms.service;

import com.cms.domain.News;
import com.cms.domain.Newsfile;

public interface INewsfileService {
    //添加新闻后获取id
    public int getAddNewsLastId(News news);
    //添加附件信息
    public void addNewsfile(Newsfile newsfile);
    public String getFileName(int newsid);
}
