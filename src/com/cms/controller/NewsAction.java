package com.cms.controller;


import com.cms.domain.News;
import com.cms.domain.SearchNews;
import com.cms.service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        NewsService newsService = new NewsService();
        //新闻查询--分页
        if ("searchNews".equals(request.getParameter("flag"))) {
            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让news都为空，查询所有

            int rowCount = newsService.getRowCount();
            int pageCount = newsService.getPageCount(pageSize);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            // 判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<News> findAllNews = newsService.findAllNews(pageNow, pageSize);
            request.setAttribute("findAllNews", findAllNews);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/normalPage/searchNews.jsp").forward(request, response);
        }


        //新闻复合查询
        if ("compoundSearch".equals(request.getParameter("flag"))) {
//            System.out.println("compoundSearch");
            String searchTitle = request.getParameter("searchTitle");
//            System.out.println("searchTitle="+searchTitle);
            String searchTypeid = request.getParameter("searchTypeid");
            if (searchTypeid == null) {
                searchTypeid = -1 + "";
            }
//            System.out.println("searchTypeid="+searchTypeid);
            String searchFlag = request.getParameter("searchFlag");
            if (searchFlag == null) {
                searchFlag = -1 + "";
            }
//            System.out.println("searchFlag="+searchFlag);
            String searchStartcreattime = request.getParameter("searchStartcreattime");
//            System.out.println("searchStartcreattime="+searchStartcreattime);
            String searchEndcreattime = request.getParameter("searchEndcreattime");
//            System.out.println("searchEndcreattime="+searchEndcreattime);
            SearchNews searchNews = new SearchNews();
            searchNews.setSearchTitle(searchTitle);
            searchNews.setSearchTypeid(Integer.parseInt(searchTypeid));
            searchNews.setSearchFlag(Integer.parseInt(searchFlag));
            searchNews.setSearchStartcreattime(searchStartcreattime);
            searchNews.setSearchEndcreattime(searchEndcreattime);
            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            int rowCount = newsService.getNewsRowCount(searchNews);
//            System.out.println("rowCount="+rowCount);
            int pageCount = newsService.getNewsPageCount(searchNews, pageSize);
//            System.out.println("pageCount="+pageCount);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            // 判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<News> findAllNews = newsService.findSearchNews(searchNews, pageNow, pageSize);
            request.setAttribute("findAllNews", findAllNews);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("searchTitle", searchTitle);
            request.setAttribute("searchTypeid", searchTypeid);
            request.setAttribute("searchFlag", searchFlag);
            request.setAttribute("searchStartcreattime", searchStartcreattime);
            request.setAttribute("searchEndcreattime", searchEndcreattime);
            //传个标记，用于控制分页
            request.setAttribute("issearch", "ok");
            request.getRequestDispatcher("/normalPage/searchNews.jsp").forward(request, response);
        }

        // 发布新闻
        if ("release".equals(request.getParameter("flag"))) {
            System.out.println("发布新闻");
            String title = request.getParameter("title");
            System.out.println("title=" + title);
            String content = request.getParameter("content");
            System.out.println("content=" + content);
            int typeid = Integer.parseInt(request.getParameter("typeid"));
            System.out.println("typeid=" + typeid);
            int sflag = Integer.parseInt(request.getParameter("sflag"));
            System.out.println("sflag=" + sflag);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(date);
            System.out.println(currentTime);
            News news = new News();
            news.setTitle(title);
            news.setContent(content);
            news.setTypeid(typeid);
            news.setFlag(sflag);
            news.setCreattime(currentTime);
            // 存储到数据库
            if (newsService.addNews(news)) {
                System.out.println("发布新闻成功");
                // 传到普通用户的待审核页面
                request.getRequestDispatcher("/newsAction?flag=searchNews").forward(request, response);
            }
        }


        //超级管理员--新闻分页查询
        if ("superSearchNews".equals(request.getParameter("flag"))) {
            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让news都为空，查询所有

            int rowCount = newsService.getRowCount();
            int pageCount = newsService.getPageCount(pageSize);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            // 判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<News> findAllNews = newsService.findAllNews(pageNow, pageSize);
            request.setAttribute("findAllNews", findAllNews);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/superPage/superSearchNews.jsp").forward(request, response);
        }

        if ("superCompoundSearch".equals(request.getParameter("flag"))) {
            //            System.out.println("compoundSearch");
            String searchTitle = request.getParameter("searchTitle");
//            System.out.println("searchTitle="+searchTitle);
            String searchTypeid = request.getParameter("searchTypeid");
            if (searchTypeid == null) {
                searchTypeid = -1 + "";
            }
//            System.out.println("searchTypeid="+searchTypeid);
            String searchFlag = request.getParameter("searchFlag");
            if (searchFlag == null) {
                searchFlag = -1 + "";
            }
//            System.out.println("searchFlag="+searchFlag);
            String searchStartcreattime = request.getParameter("searchStartcreattime");
//            System.out.println("searchStartcreattime="+searchStartcreattime);
            String searchEndcreattime = request.getParameter("searchEndcreattime");
//            System.out.println("searchEndcreattime="+searchEndcreattime);
            SearchNews searchNews = new SearchNews();
            searchNews.setSearchTitle(searchTitle);
            searchNews.setSearchTypeid(Integer.parseInt(searchTypeid));
            searchNews.setSearchFlag(Integer.parseInt(searchFlag));
            searchNews.setSearchStartcreattime(searchStartcreattime);
            searchNews.setSearchEndcreattime(searchEndcreattime);
            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            int rowCount = newsService.getNewsRowCount(searchNews);
//            System.out.println("rowCount="+rowCount);
            int pageCount = newsService.getNewsPageCount(searchNews, pageSize);
//            System.out.println("pageCount="+pageCount);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            // 判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<News> findAllNews = newsService.findSearchNews(searchNews, pageNow, pageSize);
            request.setAttribute("findAllNews", findAllNews);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("searchTitle", searchTitle);
            request.setAttribute("searchTypeid", searchTypeid);
            request.setAttribute("searchFlag", searchFlag);
            request.setAttribute("searchStartcreattime", searchStartcreattime);
            request.setAttribute("searchEndcreattime", searchEndcreattime);
            //传个标记，用于控制分页
            request.setAttribute("issearch", "ok");
            request.getRequestDispatcher("/superPage/superSearchNews.jsp").forward(request, response);
        }
        //删除新闻
        if ("delete".equals(request.getParameter("flag"))) {
            //复合查询删除--传参数进入
            if ("superCompoundSearch".equals(request.getParameter("superCompoundSearch"))) {
                System.out.println("123");
                if (request.getParameter("id") == null) {
                    //传个标记，用于控制分页和复合查询分页
                    request.setAttribute("issearch", "ok");
                    request.getRequestDispatcher("/newsAction?flag=superCompoundSearch").forward(request, response);
                }
                int id = Integer.parseInt(request.getParameter("id"));
                if (newsService.deleteNews(id)) {
                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    request.getRequestDispatcher("/newsAction?flag=superCompoundSearch").forward(request, response);
                }
            } else {
                //普通删除
                if (request.getParameter("id") == null) {

                    request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request, response);
                }
                int id = Integer.parseInt(request.getParameter("id"));
                if (newsService.deleteNews(id)) {

                    request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request, response);
                }
            }
        }
        // 超级用户批量删除news
        if ("batch".equals(request.getParameter("flag"))) {
            if ("superCompoundSearch".equals(request.getParameter("superCompoundSearch"))) {
                if (request.getParameter("ids") == null) {
                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                    //传个标记，用于控制分页和复合查询分页
                    request.setAttribute("issearch", "ok");
                    request.getRequestDispatcher("/newsAction?flag=superCompoundSearch&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);
                }
                String[] ids = request.getParameterValues("ids");
                for (int i = 0; i < ids.length; i++) {
                    newsService.deleteNews(Integer.parseInt(ids[i]));
                }
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                //传个标记，用于控制分页和复合查询分页
                request.setAttribute("issearch", "ok");
                //&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}
                request.getRequestDispatcher("/newsAction?flag=superCompoundSearch&pageNow=" + pageNow).forward(request, response);
            } else {
                if (request.getParameter("ids") == null) {
                    System.out.println("12312313");
                    request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request, response);
                }
                String[] ids = request.getParameterValues("ids");
                for (int i = 0; i < ids.length; i++) {
                    newsService.deleteNews(Integer.parseInt(ids[i]));
                }
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));

                request.getRequestDispatcher("/newsAction?flag=superSearchNews&pageNow=" + pageNow).forward(request, response);
            }

        }
        //进入修改新闻页面
        if ("revise".equals(request.getParameter("flag"))) {
            //1\复合查询后，进入修改，修改后返回当前位置
            //2、正常修改
//            System.out.println("正常修改");
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            News newsInfo = newsService.getNewsInfo(newsId);
            String creattime = newsInfo.getCreattime();
            String title = newsInfo.getTitle();
            int typeid = newsInfo.getTypeid();
            int flag = newsInfo.getFlag();
            String content = newsInfo.getContent();
            int pageNow = Integer.parseInt(request.getParameter("pageNow"));
            int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
            request.setAttribute("title", title);
            request.setAttribute("typeid", typeid);
            request.setAttribute("flag", flag);
            request.setAttribute("content", content);
            request.setAttribute("pageNow",pageNow);
            request.setAttribute("showRowCount",showRowCount);
            request.setAttribute("newsId",newsId);
            request.setAttribute("creattime",creattime);

            request.getRequestDispatcher("/superPage/updateNews.jsp").forward(request, response);
        }
        //进入修改新闻页面
        if ("reviseok".equals(request.getParameter("flag"))) {
            //1\复合查询后，进入修改，修改后返回当前位置
            //2、正常修改
//            System.out.println("复合查询后修改");
            int newsId = Integer.parseInt(request.getParameter("newsId"));
            News newsInfo = newsService.getNewsInfo(newsId);
            String creattime = newsInfo.getCreattime();
            String title = newsInfo.getTitle();
            int typeid = newsInfo.getTypeid();
            int flag = newsInfo.getFlag();
            String content = newsInfo.getContent();
            int pageNow = Integer.parseInt(request.getParameter("pageNow"));
            int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
            request.setAttribute("title", title);
            request.setAttribute("typeid", typeid);
            request.setAttribute("flag", flag);
            request.setAttribute("content", content);
            request.setAttribute("pageNow",pageNow);
            request.setAttribute("showRowCount",showRowCount);
            request.setAttribute("newsId",newsId);
            request.setAttribute("creattime",creattime);
            //传个标记，用于控制分页和复合查询分页
            request.setAttribute("issearch", "ok");
            request.getRequestDispatcher("/superPage/updateNews.jsp").forward(request, response);
        }

        //判断进入显示页
        if ("index".equals(request.getParameter("flag"))) {
//            System.out.println("index");
            int newsFlag = Integer.parseInt(request.getParameter("newsFlag"));
            int nid = Integer.parseInt(request.getParameter("nid"));
            String title = request.getParameter("title");
            String typeid = request.getParameter("typeid");
            String content = request.getParameter("content");
            String creattime = request.getParameter("creattime");
//            System.out.println(newsFlag);
            //如果flag=0，返回查询页面或复合查询页面
            if (newsFlag == 0) {
//                System.out.println("未审核");
                if ("ok".equals(request.getParameter("issearch"))) {
//                    System.out.println("复合查询页面");
                    //连接数据库保存修改信息

                    News news = new News();
                    news.setId(nid);
                    news.setTitle(title);
                    news.setTypeid(Integer.parseInt(typeid));
                    news.setFlag(newsFlag);
                    news.setContent(content);

                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                    if (newsService.updateNews(news)){
                        System.out.println("修改成功");
                        //保留到当前修改的页面，但是按时间排序，所以不穿参数
                        //request.getRequestDispatcher("/newsAction?flag=superSearchNews&pageNow="+pageNow+"&showRowCount="+showRowCount+"").forward(request,response);
                        request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request,response);
                    }else {

                        request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request,response);

                    }
//                    newsAction?flag=superCompoundSearch&pageNow=1&showRowCount=5
                } else {
                    System.out.println("普通查询页面");
                    //连接数据库保存修改信息

                    News news = new News();
                    news.setId(nid);
                    news.setTitle(title);
                    news.setTypeid(Integer.parseInt(typeid));
                    news.setFlag(newsFlag);
                    news.setContent(content);

                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    System.out.println("普通查询页面pageNow="+pageNow);
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                    if (newsService.updateNews(news)){
                        System.out.println("修改成功");
                        //保留到当前修改的页面，但是按时间排序，所以不穿参数
                        //request.getRequestDispatcher("/newsAction?flag=superSearchNews&pageNow="+pageNow+"&showRowCount="+showRowCount+"").forward(request,response);
                        request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request,response);
                    }else {

                        request.getRequestDispatcher("/newsAction?flag=superSearchNews").forward(request,response);

                    }
                }
            } else {
                //如果flag=1，跳转到index。jsp
                request.setAttribute("newsFlag",newsFlag);
                request.setAttribute("nid",nid);
                request.setAttribute("title",title);
                request.setAttribute("typeid",typeid);
                request.setAttribute("content",content);
                request.setAttribute("creattime",creattime);
                request.getRequestDispatcher("/amz_11_bcj/news.jsp").forward(request,response);
            }


        }


    }


}
