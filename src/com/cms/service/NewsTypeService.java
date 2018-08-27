package com.cms.service;

import com.cms.dao.NewsTypeDao;
import com.cms.domain.NewsType;

import java.util.List;

public class NewsTypeService {
    NewsTypeDao newsTypeDao = new NewsTypeDao();
    // 分页查询所有栏目信息
    public List<NewsType> findAllNewsType(int pageNow, int pageSize) {
        return newsTypeDao.findAllNewsType(pageNow,pageSize);
    }

    public int getRowCount() {
        return newsTypeDao.getRowCount();
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

    // 验证栏目是否存在
    public boolean findNewsSort(NewsType newsType) {
        return newsTypeDao.findNewsSort(newsType);
    }
}
