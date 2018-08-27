package com.cms.ajax;

import com.cms.domain.User;
import com.cms.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserService();
        // 判断登陆用户名是否存在
        if ("checkUser".equals(request.getParameter("flag"))) {
            String username = request.getParameter("username");
            User user = new User();
            user.setUserName(username);
            if ("".equals(username.trim())) {
                // 登陆名不可用
                out.print("no");
            } else {
                // 登陆名可用
                out.write("yes");
            }
        }
    }
}
