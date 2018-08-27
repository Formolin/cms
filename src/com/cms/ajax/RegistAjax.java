package com.cms.ajax;

import com.cms.dao.UserDao;
import com.cms.domain.NewsType;
import com.cms.domain.User;
import com.cms.service.NewsTypeService;
import com.cms.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserService();
        NewsTypeService newsTypeService = new NewsTypeService();
        if ("checkUser".equals(request.getParameter("flag"))) {
            String username = request.getParameter("username");
            User user = new User();
            user.setUserName(username);
            if (userService.findUserName(user)){
                //用户名已存在，不能注册
                out.print("no");
            }else {
                //用户名可以注册
                out.print("yes");
            }
        }
        // password-ajax
        if ("password".equals(request.getParameter("flag"))) {
            String updateUserPwd = request.getParameter("password");
            if (updateUserPwd == null || updateUserPwd.trim() == "" || updateUserPwd.length() < 5) {
                out.write("no");
            }
        }
        // updateUserCode-ajax
        if ("updateUserCode".equals(request.getParameter("flag"))) {
            String updateUserCode = request.getParameter("updateUserCode");
            String regex = "^[0-9]*$";

            if (!(updateUserCode.matches(regex))||updateUserCode == null || updateUserCode.trim() == "" || updateUserCode.length() < 4) {
                out.write("no");
            }
        }
        // updateUserAge-ajax
        if ("updateUserAge".equals(request.getParameter("flag"))) {
            String updateUserAge = request.getParameter("updateUserAge");
            String regex = "^[0-9]*$";
            if (!(updateUserAge.matches(regex)) || updateUserAge == null || updateUserAge.trim() == ""
                    || (Integer.parseInt(updateUserAge) < 0 || Integer.parseInt(updateUserAge) > 120)) {
                out.write("no");
            }
        }


        if ("sort".equals(request.getParameter("flag"))) {
            int sort = Integer.parseInt(request.getParameter("sort"));
            NewsType newsType = new NewsType();
            newsType.setSort(sort);
            if (newsTypeService.findNewsSort(newsType)){
                //用户名已存在，不能注册
                out.print("no");
            }else {
                //用户名可以注册
                out.print("yes");
            }
        }

    }
}
