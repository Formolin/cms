<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="左右结构项目，属于大人员的社交工具">
    <meta name="keywords" content="左右结构项目 社交 占座 ">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title>CMS新闻管理系统</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ccps_js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ccps_js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor4.4/ckeditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".meun-item").click(function () {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
                var itmeObj = $(".meun-item").find("img");
                itmeObj.each(function () {
                    var items = $(this).attr("src");
                    items = items.replace("_grey.png", ".png");
                    items = items.replace(".png", "_grey.png")
                    $(this).attr("src", items);
                });
                var attrObj = $(this).find("img").attr("src");
                ;
                attrObj = attrObj.replace("_grey.png", ".png");
                $(this).find("img").attr("src", attrObj);
            });
            $("#topAD").click(function () {
                $("#topA").toggleClass(" glyphicon-triangle-right");
                $("#topA").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topBD").click(function () {
                $("#topB").toggleClass(" glyphicon-triangle-right");
                $("#topB").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topCD").click(function () {
                $("#topC").toggleClass(" glyphicon-triangle-right");
                $("#topC").toggleClass(" glyphicon-triangle-bottom");
            });
            $(".toggle-btn").click(function () {
                $("#leftMeun").toggleClass("show");
                $("#rightContent").toggleClass("pd0px");
            })
            $("#reset").click(function () {
                CKEDITOR.instances.content.setData("");

            })
            //获取 用户名
            $("#updateUserName").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        username: $("#updateUserName").val(),
                        flag: "checkUser"
                    },
                    success: function (data) {

                        if (data == "yes") {
                            $("#msg").html("用户名可以修改");

                        } else {
                            $("#msg").html("用户名已存在");
                        }
                    },
                });
            });
            $("#updateUserPwd").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        password: $(this).val(),
                        flag: "password"
                    },
                    success: function (data) {

                        if (data == "no") {
                            $("#msg").html("必须是6位以上");

                        } else {
                            $("#msg").html("");
                        }
                    }
                })
            })
            //ajax-判断编号
            $("#updateUserCode").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        updateUserCode: $("#updateUserCode").val(),
                        flag: "updateUserCode"
                    },
                    success: function (data) {

                        if (data == "no") {
                            $("#msg").html("编号不能为空并且大于4位");

                        } else {
                            $("#msg").html("");
                        }
                    },
                });
            })
            // updateUserAge-ajax
            $("#updateUserAge").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        updateUserAge: $("#updateUserAge").val(),
                        flag: "updateUserAge"
                    },
                    success: function (data) {

                        if (data == "no") {
                            $("#msg").html("年龄不符合");

                        }else {
                            $("#msg").html("");
                        }
                    },
                });
            })

        })
    </script>

    <%--<!--[if lt IE 9]>--%>
    <%--<script src="js/html5shiv.min.js"></script>--%>
    <%--<script src="js/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
    <link href="${pageContext.request.contextPath}/css/ccps_css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ccps_css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ccps_css/slide.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ccps_css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ccps_css/flat-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ccps_css/jquery.nouislider.css">
</head>
<body>
<%-- 获取登陆时间的cookie--%>
<%
    Cookie[] cookies = request.getCookies();
    String time = "";
    if(cookies!=null){
        for (int i = 0; i < cookies.length; i++) {
            if ("last".equals(cookies[i].getName())){
                time = cookies[i].getValue();
            }
        }
    }

    request.setAttribute("time",time);
%>
<%--验证非法登陆 --%>
<c:if test="${sessionScope.username == null && cookies == null }">
    <c:redirect url="/login.jsp"></c:redirect>
</c:if>

</body>
<%--验证非法登陆 --%>
<c:if test="${sessionScope.username == null && cookies == null }">
    <c:redirect url="/login.jsp"></c:redirect>
    <%--<jsp:forward page="/login.jsp"></jsp:forward>--%>
</c:if>
<!-- 左侧菜单栏目块 -->
<div id="wrap">
    <!-- 左侧菜单栏目块 -->
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP"><img id="logo" alt="左右结构项目" src="${pageContext.request.contextPath}/images/ccps_images/logo.png"><span>CMS新闻系统</span></p>
        </div>
        <div id="personInfor">
            <p id="userName">${sessionScope.username} </p>
            <p>${sessionScope.power }</p>
            <p>
                上次登陆时间：<span>${time}</span>
            </p>
            <p>
                访问次数：<span>${applicationScope.visits }</span>
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/login.jsp">退出登录</a>
            </p>
        </div>
        <a href="${pageContext.request.contextPath}/normalPage/normalUser.jsp">
            <div class="meun-item" style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">发布新闻
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/newsAction?flag=searchNews">

            <div class="meun-item" style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">新闻查询
            </div>
        </a>

        <a href="${pageContext.request.contextPath}/userAction?flag=searchUser">
            <div class="meun-item" style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">用户查询
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/normalPage/updateUser.jsp">
            <div class="meun-item meun-item-active" style="
    padding-left: 20px;
">
                <img src="${pageContext.request.contextPath}/images/ccps_images/icon_change_grey.png">修改个人信息
            </div>
        </a>

    </div>
    <%-- //右侧区域--%>
    <div class="right">
        <div class="check-div" style="width: 1225px;">修改用户信息<span id="msg" style="margin-left:10px;color:red">${ok }</span></div>
        <div
                style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
            <form class="form-horizontal" action="${pageContext.request.contextPath}/userAction?flag=update" method="post">
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">用户名：</label>
                    <div class="col-xs-5">
                        <input type="text" name="username"
                               class="form-control input-sm duiqi" id="updateUserName" value="${sessionScope.username }"
                               style="margin-top: 7px;">
                        <input type="hidden" name="id" value="${sessionScope.id }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">密码：</label>
                    <div class="col-xs-5">
                        <input type="password" name="password"
                               class="form-control input-sm duiqi" id="updateUserPwd" placeholder=""
                               style="margin-top: 7px;">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">部门：</label>
                    <div class="col-xs-5">

                        <select name="updateUserDept" class="form-control input-sm duiqi"
                                style="margin-top: 7px;">
                            <option value="1">财务部</option>
                            <option value="2">人力部</option>
                            <option value="3">教育部</option>
                            <option value="4">学习部</option>
                            <option value="5">市场部</option>
                            <option value="6">商务部</option>
                        </select>
                        <!--<input type="" class="form-control input-sm duiqi" id="sKnot" placeholder="" style="margin-top: 7px;">-->
                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">个人编号：</label>
                    <div class="col-xs-5">
                        <input type="text" name="updateUserCode"
                               class="form-control input-sm duiqi" id="updateUserCode" placeholder=""
                               style="margin-top: 7px;">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">性别：</label>
                    <div class="col-xs-5"
                         style="text-align: center;margin-top: 6px;">
                        <input type="radio" name="updateUserSex" id="sexman" placeholder=""
                               style="margin-top: 7px;" value='1'>男 <input type="radio" name="updateUserSex"
                                                                           id="sexwoman" placeholder=""
                                                                           style="margin-top: 7px;    margin-left: 55px;" value="2">女

                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">年龄：</label>
                    <div class="col-xs-5">
                        <input type="text" name="updateUserAge"
                               class="form-control input-sm duiqi" id="updateUserAge" placeholder=""
                               style="margin-top: 7px;">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sKnot" class="col-xs-4 control-label">权限：</label>
                    <div class="col-xs-5">

                        <select name="updateUserPower" class="form-control input-sm duiqi"
                                style="margin-top: 7px;">
                            <option value="0">普通管理员</option>
                            <option value="1">超级管理员</option>

                        </select>
                        <!--<input type="" class="form-control input-sm duiqi" id="sKnot" placeholder="" style="margin-top: 7px;">-->
                    </div>
                </div>
                <div class="form-group text-right">
                    <div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
                        <button type="reset" class="btn btn-xs btn-white">取 消</button>
                        <button type="submit" class="btn btn-xs btn-green">保存</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

    </body>

</html>
