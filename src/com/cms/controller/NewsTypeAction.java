package com.cms.controller;

import com.cms.domain.NewsType;
import com.cms.service.NewsTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class NewsTypeAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        NewsTypeService newsTypeService = new NewsTypeService();
        if ("superSearchTypeid".equals(request.getParameter("flag"))){
//            System.out.println("superSearchTypeid");
            //获取所有栏目信息
            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让news都为空，查询所有

            int rowCount = newsTypeService.getRowCount();
            int pageCount = newsTypeService.getPageCount(pageSize);
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

            List<NewsType> findAllNewsType = newsTypeService.findAllNewsType(pageNow, pageSize);
            request.setAttribute("findAllNewsType", findAllNewsType);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/superPage/superSearchTypeid.jsp").forward(request,response);
        }
        //添加栏目
        if ("addNewsType".equals(request.getParameter("flag"))){
            int showRowCount = 5;
//            if (request.getParameter("showRowCount") != null) {
//                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
//
//            }

//            int start = 1;
//            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让news都为空，查询所有

//            int rowCount = newsTypeService.getRowCount();
//            int pageCount = newsTypeService.getPageCount(pageSize);
//            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
//                pageNow = Integer.parseInt(request.getParameter("pageNow"));
//            }
            // 判断跳转页面输入的是否是数字,page为空
//            String page = request.getParameter("page");
//            String regex = "^[0-9]*$";
//            if (page != null && page.matches(regex) && page.trim() != "") {
//                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
//                    pageNow = Integer.parseInt(page);
//                }
//
//            }

            List<NewsType> findAllNewsType = newsTypeService.findAllNewsType(pageNow, pageSize);
            request.setAttribute("findAllNewsType", findAllNewsType);
//            request.setAttribute("pageNow", pageNow);
//            request.setAttribute("pageSize", pageSize);
//            request.setAttribute("pageCount", pageCount);
//            request.setAttribute("rowCount", rowCount);
//            request.setAttribute("showRowCount", pageSize);
//            request.setAttribute("start", start);
//            request.setAttribute("end", end);
            request.getRequestDispatcher("/superPage/addNewsType.jsp").forward(request,response);
        }














    }
}
