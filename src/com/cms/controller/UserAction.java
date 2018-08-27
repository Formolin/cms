package com.cms.controller;

import com.cms.domain.SearchUser;
import com.cms.domain.User;
import com.cms.service.UserService;
import com.cms.util.MyCookieTime;
import com.cms.util.Tools;
import net.sf.json.JSONArray;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends HttpServlet {
    public void destroy() {
        /* 写入数据库 */
        Integer visits = (Integer) getServletContext().getAttribute("visits");
        Tools.writeDB(visits);
    }

    public void init() throws ServletException {
        // 从数据库读取访问次数
        int readDB = Tools.readDB();
        getServletContext().setAttribute("visits", readDB);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        UserService userService = new UserService();
        //判断登陆
        if ("loginOk".equals(request.getParameter("flag"))) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User();
            user.setUserName(username);
            user.setUserPwd(password);
            if (userService.checkUser(user)) {
                System.out.println("登陆成功");
                // 登陆成功
                // 创建session,并解决非法登陆，如果没有session进入判断cookie，两者都没有视为非法登陆
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("id", userService.getId(user));
                session.setAttribute("power", userService.getPower(user));
                //获取cookid，用于判断上次登陆时间
                //如果cookie为空，说明第一次登陆，获取当前时间返回
                //如果！=null 之前已登陆，获取之前的时间并更新，返回客户端


                Cookie[] cookies = request.getCookies();
                Cookie cookie = MyCookieTime.getCookieByName(cookies, "last");
                if (cookie == null) {
                    //第一次登陆，记录当前时间 写到浏览器
                    System.out.println("第一次登陆");
                } else {
                    //第n次登陆，记录当前时间，
                    String value = cookie.getValue();
                    System.out.println("欢迎再次登陆" + value);
                }
                //获取当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String currentTime = simpleDateFormat.format(new Date());
                Cookie last = new Cookie("last", currentTime);
                response.addCookie(last);
                // 创建cookie
                if (request.getParameter("cook") != null) {
                    // 创建cookie
                    Cookie cookie_username = new Cookie("username", username);
                    Cookie cookie_password = new Cookie("password", password);
                    // 设置时效
                    cookie_username.setMaxAge(1000 * 60 * 60 * 24);
                    cookie_password.setMaxAge(1000 * 60 * 60 * 24);
                    // 回写cookie给客户端
                    response.addCookie(cookie_username);
                    response.addCookie(cookie_password);
                }
                // 登陆成功，访问次数加1
                ServletContext application = getServletContext();
                Integer visits = (Integer) application.getAttribute("visits");
                application.setAttribute("visits", ++visits);
                // 判断管理员进入相应的界面
                // 获取管理员的状态码
                if ("普通管理员".equals(userService.getPower(user))) {
                    response.sendRedirect(request.getContextPath() + "/normalPage/normalUser.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/newsAction?flag=superSearchNews");
                }

            } else {
                // 登陆失败,回显密码错误
                String msg = "密码错误";
                msg = URLEncoder.encode(msg, "utf-8");
                response.sendRedirect(request.getContextPath() + "/login.jsp?err=" + msg);
            }
        }
        // 注册用户
        if ("regUser".equals(request.getParameter("flag"))) {
            String updateDepID = request.getParameter("updateUserDept");
            String updateUserName = request.getParameter("username");
            String updateUserPwd = request.getParameter("password");
            String updateUserCode = request.getParameter("updateUserCode");
            String updateUserSex = request.getParameter("updateUserSex");
            String updateUserAge = request.getParameter("updateUserAge");
            String updateUserPower = request.getParameter("updateUserPower");
            User user = new User();
            user.setDepID(Integer.parseInt(updateDepID));
            user.setUserName(updateUserName);
            user.setUserPwd(updateUserPwd);
            user.setUserCode(updateUserCode);
            user.setUserSex(updateUserSex);
            user.setUserAge(Integer.parseInt(updateUserAge));
            user.setUserPower(Integer.parseInt(updateUserPower));
            if (userService.addUser(user)) {

                request.getRequestDispatcher("/userAction?flag=loginOk").forward(request, response);
            } else {
                // 失败
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }

        }
        // 修改登陆用户
        if ("update".equals(request.getParameter("flag"))) {
            // 创建map集合，验证修改数据是否有误
            Map<String, String> map = new HashMap<String, String>();
            // //根据条件添加map中 updateUserName updateUserPwd updateUserCode
            // updateUserSex updateUserAge updateUserPower
            String regex = "^[0-9]*$";
            if (request.getParameter("username") == null || request.getParameter("username").trim() == "") {
                map.put("username", "用户名不能为空");
            }
            if (request.getParameter("password") == null || request.getParameter("password").trim() == ""
                    || request.getParameter("password").length() < 6) {
                map.put("password", "密码不能为空，长度必须是6位以上");
            }
            if (request.getParameter("updateUserCode") == null || request.getParameter("updateUserCode").trim() == ""
                    || request.getParameter("updateUserCode").length() < 4) {
                map.put("updateUserCode", "编号不能为空并且大于4位");
            }
            if (!(request.getParameter("updateUserAge").matches(regex)) || request.getParameter("updateUserAge") == null
                    || request.getParameter("updateUserAge").trim() == ""
                    || (Integer.parseInt(request.getParameter("updateUserAge")) < 0
                    && Integer.parseInt(request.getParameter("updateUserAge")) > 120)) {
                map.put("updateUserAge", "年龄不符合");
            }
            // //如果map的长度！=0，证明有误，给出提示，并重返修改页面
            if (map.size() > 0) {
                request.getRequestDispatcher("/normalPage/updateUser.jsp").forward(request, response);
                // 如果无误添加用户，封装用户数据，调用service方法
            } else {
                // 修改用户信息
                // updateUserName updateUserPwd updateUserCode
                // updateUserSex updateUserAge updateUserPower
                String updateDepID = request.getParameter("updateUserDept");
                String updateUserName = request.getParameter("username");
                String updateUserPwd = request.getParameter("password");
                String updateUserCode = request.getParameter("updateUserCode");
                String updateUserSex = request.getParameter("updateUserSex");
                String updateUserAge = request.getParameter("updateUserAge");
                String updateUserPower = request.getParameter("updateUserPower");
                String id = request.getParameter("id");
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setDepID(Integer.parseInt(updateDepID));
                user.setUserName(updateUserName);
                user.setUserPwd(updateUserPwd);
                user.setUserCode(updateUserCode);
                user.setUserSex(updateUserSex);
                user.setUserAge(Integer.parseInt(updateUserAge));
                user.setUserPower(Integer.parseInt(updateUserPower));
                if (userService.updateUser(user)) {
                    System.out.println("修改当前用户成功");
                    request.setAttribute("ok", "保存成功");
                    request.getRequestDispatcher("/normalPage/updateUser.jsp").forward(request, response);
                } else {
                    System.out.println("失败");
                    request.getRequestDispatcher("/normalPage/updateUser.jsp").forward(request, response);
                }
            }
        }
        //用户查询--分页
        if ("searchUser".equals(request.getParameter("flag"))) {
            int showRowCount = 5;
            System.out.println("show=" + request.getParameter("showRowCount"));
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让user都为空，查询所有
            User user = new User(-1, -1, "", "", "", "", -1, -1);
            int rowCount = userService.getRowCount(user);
            int pageCount = userService.getPageCount(user, pageSize);
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

            List<User> findAllUser = userService.findAllUser(pageNow, pageSize);
            request.setAttribute("findAllUser", findAllUser);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/normalPage/searchUser.jsp").forward(request, response);
        }
        //compoundSearch--复合查询
        if ("compoundSearch".equals(request.getParameter("flag"))) {
            String searchUserName = request.getParameter("searchUserName");
//            System.out.println("searchUserName="+searchUserName);
            String searchDepID = request.getParameter("searchDepID");
//            System.out.println("searchDepID="+searchDepID);
            String searchUserCode = request.getParameter("searchUserCode");
//            System.out.println("searchUserCode="+searchUserCode);
            String searchUserSex = request.getParameter("searchUserSex");
//            System.out.println("searchUserSex="+searchUserSex);
            String searchUserAge = request.getParameter("searchUserAge");
//            System.out.println("searchUserAge="+searchUserAge);
            String searchUserPower = request.getParameter("searchUserPower");
//            System.out.println("searchUserPower="+searchUserPower);
            SearchUser searchUser = new SearchUser();
            searchUser.setSearchDepID(Integer.parseInt(searchDepID));
            searchUser.setSearchUserName(searchUserName);
            searchUser.setSearchUserCode(searchUserCode);
            searchUser.setSearchUserSex(searchUserSex);
            if (searchUserAge == "") {
                searchUserAge = -1 + "";
            }
            searchUser.setSearchUserAge(Integer.parseInt(searchUserAge));
            searchUser.setSearchUserPower(Integer.parseInt(searchUserPower));

            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //，查询所有
            int rowCount = userService.getSearchRowCount(searchUser);
            int pageCount = userService.getSearchPageCount(searchUser, pageSize);

//            System.out.println("pageCount"+pageCount);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            //判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<User> findSearchUser = userService.findSearchUser(searchUser, pageNow, pageSize);
            request.setAttribute("findAllUser", findSearchUser);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("searchUserName", searchUserName);
            request.setAttribute("searchDepID", searchDepID);
            request.setAttribute("searchUserCode", searchUserCode);
            request.setAttribute("searchUserSex", searchUserSex);
            request.setAttribute("searchUserAge", searchUserAge);
            request.setAttribute("searchUserPower", searchUserPower);
            //传个标记，用于控制分页
            request.setAttribute("issearch", "ok");

            request.getRequestDispatcher("/normalPage/searchUser.jsp").forward(request, response);
        }

        //超级管理员修改登陆用户
        if ("superUpdate".equals(request.getParameter("flag"))) {
            // 创建map集合，验证修改数据是否有误
            Map<String, String> map = new HashMap<String, String>();
            // //根据条件添加map中 updateUserName updateUserPwd updateUserCode
            // updateUserSex updateUserAge updateUserPower
            String regex = "^[0-9]*$";
            if (request.getParameter("username") == null || request.getParameter("username").trim() == "") {
                map.put("username", "用户名不能为空");
            }
            if (request.getParameter("password") == null || request.getParameter("password").trim() == ""
                    || request.getParameter("password").length() < 6) {
                map.put("password", "密码不能为空，长度必须是6位以上");
            }
            if (request.getParameter("updateUserCode") == null || request.getParameter("updateUserCode").trim() == ""
                    || request.getParameter("updateUserCode").length() < 4) {
                map.put("updateUserCode", "编号不能为空并且大于4位");
            }
            if (!(request.getParameter("updateUserAge").matches(regex)) || request.getParameter("updateUserAge") == null
                    || request.getParameter("updateUserAge").trim() == ""
                    || (Integer.parseInt(request.getParameter("updateUserAge")) < 0
                    && Integer.parseInt(request.getParameter("updateUserAge")) > 120)) {
                map.put("updateUserAge", "年龄不符合");
            }
            // //如果map的长度！=0，证明有误，给出提示，并重返修改页面
            if (map.size() > 0) {
                request.getRequestDispatcher("/superPage/superUpdateUser.jsp").forward(request, response);
                // 如果无误添加用户，封装用户数据，调用service方法
            } else {
                // 修改用户信息
                // updateUserName updateUserPwd updateUserCode
                // updateUserSex updateUserAge updateUserPower
                String updateDepID = request.getParameter("updateUserDept");
                String updateUserName = request.getParameter("username");
                String updateUserPwd = request.getParameter("password");
                String updateUserCode = request.getParameter("updateUserCode");
                String updateUserSex = request.getParameter("updateUserSex");
                String updateUserAge = request.getParameter("updateUserAge");
                String updateUserPower = request.getParameter("updateUserPower");
                String id = request.getParameter("id");
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setDepID(Integer.parseInt(updateDepID));
                user.setUserName(updateUserName);
                user.setUserPwd(updateUserPwd);
                user.setUserCode(updateUserCode);
                user.setUserSex(updateUserSex);
                user.setUserAge(Integer.parseInt(updateUserAge));
                user.setUserPower(Integer.parseInt(updateUserPower));
                if (userService.updateUser(user)) {
                    System.out.println("修改当前用户成功");
                    request.setAttribute("ok", "保存成功");
                    request.getRequestDispatcher("/superPage/superUpdateUser.jsp").forward(request, response);
                } else {
                    System.out.println("失败");
                    request.getRequestDispatcher("/superPage/superUpdateUser.jsp").forward(request, response);
                }
            }
        }

        //超级管理员用户查询--分页
        if ("superSearchUser".equals(request.getParameter("flag"))) {
            int showRowCount = 5;
            System.out.println("show=" + request.getParameter("showRowCount"));
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //让user都为空，查询所有
            User user = new User(-1, -1, "", "", "", "", -1, -1);
            int rowCount = userService.getRowCount(user);
            int pageCount = userService.getPageCount(user, pageSize);
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

            List<User> findAllUser = userService.findAllUser(pageNow, pageSize);
            request.setAttribute("findAllUser", findAllUser);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.getRequestDispatcher("/superPage/superSearchUser.jsp").forward(request, response);
        }
        //superCompoundSearch--超级用户复合查询
        if ("superCompoundSearch".equals(request.getParameter("flag"))) {
            String searchUserName = request.getParameter("searchUserName");
//            System.out.println("searchUserName="+searchUserName);
            String searchDepID = request.getParameter("searchDepID");
//            System.out.println("searchDepID="+searchDepID);
            String searchUserCode = request.getParameter("searchUserCode");
//            System.out.println("searchUserCode="+searchUserCode);
            String searchUserSex = request.getParameter("searchUserSex");
//            System.out.println("searchUserSex="+searchUserSex);
            String searchUserAge = request.getParameter("searchUserAge");
//            System.out.println("searchUserAge="+searchUserAge);
            String searchUserPower = request.getParameter("searchUserPower");
//            System.out.println("searchUserPower="+searchUserPower);
            SearchUser searchUser = new SearchUser();
            searchUser.setSearchDepID(Integer.parseInt(searchDepID));
            searchUser.setSearchUserName(searchUserName);
            searchUser.setSearchUserCode(searchUserCode);
            searchUser.setSearchUserSex(searchUserSex);
            if (searchUserAge == "") {
                searchUserAge = -1 + "";
            }
            searchUser.setSearchUserAge(Integer.parseInt(searchUserAge));
            searchUser.setSearchUserPower(Integer.parseInt(searchUserPower));

            int showRowCount = 5;
            if (request.getParameter("showRowCount") != null) {
                showRowCount = Integer.parseInt(request.getParameter("showRowCount"));

            }

            int start = 1;
            int end = 5;
            int pageNow = 1;
            int pageSize = showRowCount;

            //，查询所有
            int rowCount = userService.getSearchRowCount(searchUser);
            int pageCount = userService.getSearchPageCount(searchUser, pageSize);

//            System.out.println("pageCount"+pageCount);
            if (request.getParameter("pageNow") != null && request.getParameter("pageNow").trim() != "") {
                pageNow = Integer.parseInt(request.getParameter("pageNow"));
            }
            //判断跳转页面输入的是否是数字,page为空
            String page = request.getParameter("page");
            String regex = "^[0-9]*$";
            if (page != null && page.matches(regex) && page.trim() != "") {
                if (Integer.parseInt(page) > 1 && Integer.parseInt(page) <= pageCount) {
                    pageNow = Integer.parseInt(page);
                }

            }

            List<User> findSearchUser = userService.findSearchUser(searchUser, pageNow, pageSize);
            request.setAttribute("findAllUser", findSearchUser);
            request.setAttribute("pageNow", pageNow);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("rowCount", rowCount);
            request.setAttribute("showRowCount", pageSize);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("searchUserName", searchUserName);
            request.setAttribute("searchDepID", searchDepID);
            request.setAttribute("searchUserCode", searchUserCode);
            request.setAttribute("searchUserSex", searchUserSex);
            request.setAttribute("searchUserAge", searchUserAge);
            request.setAttribute("searchUserPower", searchUserPower);
            //传个标记，用于控制分页
            request.setAttribute("issearch", "ok");

            request.getRequestDispatcher("/superPage/superSearchUser.jsp").forward(request, response);
        }
        // 删除单条数据
        if ("delete".equals(request.getParameter("flag"))) {
            if ("superCompoundSearch".equals(request.getParameter("superCompoundSearch"))) {
                if (request.getParameter("id") == null) {
                    //传个标记，用于控制分页和复合查询分页
                    request.setAttribute("issearch", "ok");
                    request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request, response);
                }
                int id = Integer.parseInt(request.getParameter("id"));
                if (userService.deleteUser(id)) {
                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request, response);
                }
            } else {
                if (request.getParameter("id") == null) {

                    request.getRequestDispatcher("/userAction?flag=superSearchUser").forward(request, response);
                }
                int id = Integer.parseInt(request.getParameter("id"));
                if (userService.deleteUser(id)) {

                    request.getRequestDispatcher("/userAction?flag=superSearchUser").forward(request, response);
                }
            }

        }
        // 超级用户批量删除
        if ("batch".equals(request.getParameter("flag"))) {
            if ("superCompoundSearch".equals(request.getParameter("superCompoundSearch"))) {
                if (request.getParameter("ids") == null) {
                    request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request, response);
                }
                String[] ids = request.getParameterValues("ids");
                for (int i = 0; i < ids.length; i++) {
                    userService.deleteUser(Integer.parseInt(ids[i]));
                }
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                //&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}
                request.getRequestDispatcher("/userAction?flag=superCompoundSearch&pageNow=" + pageNow).forward(request, response);
            } else {
                if (request.getParameter("ids") == null) {
                    request.getRequestDispatcher("/userAction?flag=superSearchUser").forward(request, response);
                }
                String[] ids = request.getParameterValues("ids");
                for (int i = 0; i < ids.length; i++) {
                    userService.deleteUser(Integer.parseInt(ids[i]));
                }
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));
//                //传个标记，用于控制分页
//                request.setAttribute("issearch", "ok");
                request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow).forward(request, response);
            }

        }

        //ajax--接受要修改的用户id
        if ("revise".equals(request.getParameter("flag"))) {
//            System.out.println("revise");
            int updateId = Integer.parseInt(request.getParameter("updateId"));
//            System.out.println(updateId);
//            String name = "测试";
//            out.write(name);
            //通过id查找用户信息
            User userInfo = userService.getUserInfo(updateId);
//            System.out.println(userInfo.getId()+userInfo.getDepID()+userInfo.getUserName()+userInfo.getUserPwd()+userInfo.getUserCode()+userInfo.getUserAge()+userInfo.getUserSex()+userInfo.getUserPower());
            JSONArray jsonArray = JSONArray.fromObject(userInfo);
//            System.out.println(jsonArray);
            out.write(jsonArray.toString());

        }

        //超级管理员修改其他用户信息登陆用户
        if ("superUpdateOther".equals(request.getParameter("flag"))) {
            // 创建map集合，验证修改数据是否有误
            Map<String, String> map = new HashMap<String, String>();
            // //根据条件添加map中 updateUserName updateUserPwd updateUserCode
            // updateUserSex updateUserAge updateUserPower
            String regex = "^[0-9]*$";
            if (request.getParameter("username") == null || request.getParameter("username").trim() == "") {
                map.put("username", "用户名不能为空");
            }
            if (request.getParameter("password") == null || request.getParameter("password").trim() == ""
                    || request.getParameter("password").length() < 6) {
                map.put("password", "密码不能为空，长度必须是6位以上");
            }
            if (request.getParameter("updateUserCode") == null || request.getParameter("updateUserCode").trim() == ""
                    || request.getParameter("updateUserCode").length() < 4) {
                map.put("updateUserCode", "编号不能为空并且大于4位");
            }
            if (!(request.getParameter("updateUserAge").matches(regex)) || request.getParameter("updateUserAge") == null
                    || request.getParameter("updateUserAge").trim() == ""
                    || (Integer.parseInt(request.getParameter("updateUserAge")) < 0
                    && Integer.parseInt(request.getParameter("updateUserAge")) > 120)) {
                map.put("updateUserAge", "年龄不符合");
            }
            // //如果map的长度！=0，证明有误，给出提示，并重返修改页面
            if (map.size() > 0) {
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                //传个标记，用于控制分页
                request.setAttribute("issearch", "ok");
                request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);
                // 如果无误添加用户，封装用户数据，调用service方法
            } else {
                // 修改用户信息
                // updateUserName updateUserPwd updateUserCode
                // updateUserSex updateUserAge updateUserPower
                String updateDepID = request.getParameter("updateUserDept");
//                System.out.println(updateDepID);
                String updateUserName = request.getParameter("username");
//                System.out.println(updateUserName);
                String updateUserPwd = request.getParameter("password");
//                System.out.println(updateUserPwd);
                String updateUserCode = request.getParameter("updateUserCode");
//                System.out.println(updateUserCode);
                String updateUserSex = request.getParameter("updateUserSex");
//                System.out.println(updateUserSex);
                String updateUserAge = request.getParameter("updateUserAge");
//                System.out.println(updateUserAge);
                String updateUserPower = request.getParameter("updateUserPower");
//                System.out.println(updateUserPower);
                String id = request.getParameter("id");
//                System.out.println(id);
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setDepID(Integer.parseInt(updateDepID));
                user.setUserName(updateUserName);
                user.setUserPwd(updateUserPwd);
                user.setUserCode(updateUserCode);
                user.setUserSex(updateUserSex);
                user.setUserAge(Integer.parseInt(updateUserAge));
                user.setUserPower(Integer.parseInt(updateUserPower));
                if (userService.updateUser(user)) {
                    System.out.println("修改当前用户成功");

                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                    request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);

                } else {
                    System.out.println("失败");
                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                    request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);

                }
            }
        }
        // 添加用户
        if ("addUser".equals(request.getParameter("flag"))) {
            String updateDepID = request.getParameter("updateUserDept");
            String updateUserName = request.getParameter("username");
            String updateUserPwd = request.getParameter("password");
            String updateUserCode = request.getParameter("updateUserCode");
            String updateUserSex = request.getParameter("updateUserSex");
            String updateUserAge = request.getParameter("updateUserAge");
            String updateUserPower = request.getParameter("updateUserPower");
            User user = new User();
            user.setDepID(Integer.parseInt(updateDepID));
            user.setUserName(updateUserName);
            user.setUserPwd(updateUserPwd);
            user.setUserCode(updateUserCode);
            user.setUserSex(updateUserSex);
            user.setUserAge(Integer.parseInt(updateUserAge));
            user.setUserPower(Integer.parseInt(updateUserPower));
            if (userService.addUser(user)) {

                request.getRequestDispatcher("/userAction?flag=superSearchUser").forward(request, response);
            } else {
                // 失败
                request.getRequestDispatcher("/userAction?flag=superSearchUser").forward(request, response);
            }

        }

        //超级用户复合查询superSearchUpdateOther，修改用户
        if ("superSearchUpdateOther".equals(request.getParameter("flag"))){
            // 创建map集合，验证修改数据是否有误
            Map<String, String> map = new HashMap<String, String>();
            // //根据条件添加map中 updateUserName updateUserPwd updateUserCode
            // updateUserSex updateUserAge updateUserPower
            String regex = "^[0-9]*$";
            if (request.getParameter("username") == null || request.getParameter("username").trim() == "") {
                map.put("username", "用户名不能为空");
            }
            if (request.getParameter("password") == null || request.getParameter("password").trim() == ""
                    || request.getParameter("password").length() < 6) {
                map.put("password", "密码不能为空，长度必须是6位以上");
            }
            if (request.getParameter("updateUserCode") == null || request.getParameter("updateUserCode").trim() == ""
                    || request.getParameter("updateUserCode").length() < 4) {
                map.put("updateUserCode", "编号不能为空并且大于4位");
            }
            if (!(request.getParameter("updateUserAge").matches(regex)) || request.getParameter("updateUserAge") == null
                    || request.getParameter("updateUserAge").trim() == ""
                    || (Integer.parseInt(request.getParameter("updateUserAge")) < 0
                    && Integer.parseInt(request.getParameter("updateUserAge")) > 120)) {
                map.put("updateUserAge", "年龄不符合");
            }
            // //如果map的长度！=0，证明有误，给出提示，并重返修改页面
            if (map.size() > 0) {
                int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
                //&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}
                String searchUserName = request.getParameter("searchUserName");
                int searchDepID = Integer.parseInt(request.getParameter("searchDepID"));
                String searchUserCode = request.getParameter("searchUserCode");
                String searchUserSex = request.getParameter("searchUserSex");
                int searchUserAge = Integer.parseInt(request.getParameter("searchUserAge"));
                int searchUserPower = Integer.parseInt(request.getParameter("searchUserPower"));
                //传个标记，用于控制分页
                request.setAttribute("issearch", "ok");
                request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request,response);
//                request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount+"&searchUserName="+searchUserName+"searchDepID="+searchDepID+"searchUserCode="+searchUserCode+"searchUserSex="+searchUserSex+"searchUserAge="+searchUserAge+"searchUserPower="+searchUserPower).forward(request, response);
                // 如果无误添加用户，封装用户数据，调用service方法
            } else {
                // 修改用户信息
                // updateUserName updateUserPwd updateUserCode
                // updateUserSex updateUserAge updateUserPower
                String updateDepID = request.getParameter("updateUserDept");
//                System.out.println(updateDepID);
                String updateUserName = request.getParameter("username");
//                System.out.println(updateUserName);
                String updateUserPwd = request.getParameter("password");
//                System.out.println(updateUserPwd);
                String updateUserCode = request.getParameter("updateUserCode");
//                System.out.println(updateUserCode);
                String updateUserSex = request.getParameter("updateUserSex");
//                System.out.println(updateUserSex);
                String updateUserAge = request.getParameter("updateUserAge");
//                System.out.println(updateUserAge);
                String updateUserPower = request.getParameter("updateUserPower");
//                System.out.println(updateUserPower);
                String id = request.getParameter("id");
//                System.out.println(id);
                User user = new User();
                user.setId(Integer.parseInt(id));
                user.setDepID(Integer.parseInt(updateDepID));
                user.setUserName(updateUserName);
                user.setUserPwd(updateUserPwd);
                user.setUserCode(updateUserCode);
                user.setUserSex(updateUserSex);
                user.setUserAge(Integer.parseInt(updateUserAge));
                user.setUserPower(Integer.parseInt(updateUserPower));
                if (userService.updateUser(user)) {
                    System.out.println("修改当前用户成功");

                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
//                    request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);
                    request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request,response);
                } else {
                    System.out.println("失败");
                    //传个标记，用于控制分页
                    request.setAttribute("issearch", "ok");
                    int pageNow = Integer.parseInt(request.getParameter("pageNow"));
                    int showRowCount = Integer.parseInt(request.getParameter("showRowCount"));
//                    request.getRequestDispatcher("/userAction?flag=superSearchUser&pageNow=" + pageNow + "&showRowCount=" + showRowCount).forward(request, response);
                    request.getRequestDispatcher("/userAction?flag=superCompoundSearch").forward(request,response);
                }
            }
        }












    }




}




