<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>cms</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <!--<link rel="stylesheet" href="css/css">-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login_css/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_css/supersized.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!--<link rel="preload" href="./Fullscreen Login_files/f(1).txt" as="script">-->
    <!--<link rel="preload" href="../txt/f(1).txt" as="script">-->
    <!--<script src="../txt/f.txt"></script>-->
    <!--<script src="./Fullscreen Login_files/f.txt"></script>-->
    <!--<script src="./Fullscreen Login_files/ca-pub-1542822386688301.js"></script>-->
    <!-- Javascript -->
    <script src="${pageContext.request.contextPath}/js/login_js/jquery-1.8.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/login_js/supersized.3.2.7.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/login_js/supersized-init.js"></script>
    <script src="${pageContext.request.contextPath}/js/login_js/scripts.js"></script>


    <script type="text/javascript">
        $(function () {
            //获取 用户名
            $("#username").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        username: $("#username").val(),
                        flag: "checkUser"
                    },
                    success: function (data) {
                        if (data == "yes") {
                            $("#msgok").html("用户名可以注册");

                        } else {
                            $("#msgok").html("用户名已存在");
                        }
                    },
                });
            });
            $("#password").blur(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/registAjax",
                    data: {
                        password: $(this).val(),
                        flag: "password"
                    },
                    success: function (data) {

                        if (data == "no") {
                            $("#err").html("必须是6位以上");

                        } else {
                            $("#err").html("");
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
                            $("#userCode").html("编号不能为空并且大于4位");

                        } else {
                            $("#userCode").html("");
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
                            $("#age").html("年龄不符合");

                        }else {
                            $("#age").html("");
                        }
                    },
                });
            })


        })
    </script>
    <style type="text/css">
        body {
            background: #fff url("${pageContext.request.contextPath}/images/login_img/3.jpg") no-repeat left top;
            background-size: 100%;
        }
    </style>
</head>

<body>
<div style="width:728px;height:90px;margin:0 auto;">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/login_css/astyle.css">
    <div align="center">

        <!-- cssmoban_728*90_show -->
        <ins class="adsbygoogle"
             style="display:inline-block;width:728px;height:90px"
             data-ad-client="ca-pub-1542822386688301" data-ad-slot="3852412413"
             data-adsbygoogle-status="done">
            <ins id="aswift_0_expand"
                 style="display:inline-table;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:728px;background-color:transparent;">
                <ins id="aswift_0_anchor"
                     style="display:block;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:728px;background-color:transparent;">
                    <iframe width="728" height="90" frameborder="0" marginwidth="0"
                            marginheight="0" vspace="0" hspace="0" allowtransparency="true"
                            scrolling="no" allowfullscreen="true"
                            onload="var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){try{h=s.upd(h,i)}catch(e){}w.location.replace(h)}}"
                            id="aswift_0" name="aswift_0"
                            style="left:0;position:absolute;top:0;width:728px;height:90px;"
                            src="${pageContext.request.contextPath}/html/saved_resource.html"></iframe>
                </ins>
            </ins>
        </ins>
        <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
    </div>
</div>


<c:set var="msg" value=""></c:set>
<div class="page-container" style="margin-top:-75px;">
    <h1>CMS用户注册</h1>


    <form action="${pageContext.request.contextPath}/userAction?flag=regUser" method="post">


        <input type="text" name="username" class="username" id="username" placeholder="Username" value=""><span
            id="msgok"></span>
        <input type="password" name="password" class="password" id="password" placeholder="Password" value=""><span
            id="err" style="
    position:  absolute;
    margin-left: 10px;
    margin-top: 40px;
    "></span>
        <select name="updateUserDept" class="form-control input-sm duiqi dept"
                style="margin-top: 7px;	width: 302px;
    height: 42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;">
            <option value="1">财务部</option>
            <option value="2">人力部</option>
            <option value="3">教育部</option>
            <option value="4">学习部</option>
            <option value="5">市场部</option>
            <option value="6">商务部</option>
        </select>

        <input type="text" name="updateUserCode" class="username" id="updateUserCode" placeholder="UserCode"
               value=""><span id="userCode" style="
    position:  absolute;
    margin-left: 10px;
    margin-top: 40px;
    "></span>
        <select name="updateUserSex" class="form-control input-sm duiqi dept"
                style="margin-top: 7px;	width: 302px;
    height: 42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;">
            <option value="男">男</option>
            <option value="女">女</option>

        </select>
        <input type="text" name="updateUserAge" class="username" id="updateUserAge" placeholder="Age" value=""><span
            id="age" style="
    position:  absolute;
    margin-left: 10px;
    margin-top: 40px;
    "></span>
        <select name="updateUserPower" class="form-control input-sm duiqi dept"
                style="margin-top: 7px;	width: 302px;
    height: 42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;">
            <option value="0">普通管理员</option>
            <option value="1">超级管理员</option>

        </select>

        <button type="submit">注册</button>
        </br>
        <button><a href="${pageContext.request.contextPath}/login.jsp" id="can"
                   style="padding-left: 0;color:white;text-decoration: none;">取消</a></button>
    </form>


</div>
<div align="center">Collect from liujiang</div>


<iframe id="google_osd_static_frame_2719722702574"
        name="google_osd_static_frame"
        style="display: none; width: 0px; height: 0px;"
        src="${pageContext.request.contextPath}/html/saved_resource(1).html"></iframe>
</body>

</html>