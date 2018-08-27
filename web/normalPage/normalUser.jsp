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
    <script type="text/javascript" src="../js/ccps_js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/ccps_js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../ckeditor4.4/ckeditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ckfinder/ckfinder.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#add").click(function () {
                $("#adddiv").append("<input id='upload' type='file' name='upload' /></br>")
            })


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
    <link rel="stylesheet" type="text/css" href="../css/ccps_css/flat-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/ccps_css/jquery.nouislider.css">
    <style type="text/css">
        #add {
            width:  105px;
            background-color: rgba(0,0,0,0);
            height:  30px;
            text-align:  center;
            line-height:  30px;
        }
        #upload {
            display: inline-block;
            margin-left: 305px;
        }
    </style>
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
    <%--<jsp:forward page="/login.jsp"></jsp:forward>--%>
</c:if>
<div id="wrap">
    <!-- 左侧菜单栏目块 -->
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP"><img id="logo" alt="左右结构项目" src="../images/ccps_images/logo.png"><span>CMS新闻系统</span></p>
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

            <div class="meun-item meun-item-active" style="
    padding-left: 20px;
"><img
                    src="../images/ccps_images/icon_source.png">发布新闻
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/newsAction?flag=searchNews">

            <div class="meun-item " style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">新闻查询
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/userAction?flag=searchUser">
            <div class="meun-item" style="
    padding-left: 20px;
"><img
                    src="../images/ccps_images/icon_source.png">用户查询
            </div>
        </a>

        <a href="${pageContext.request.contextPath}/normalPage/updateUser.jsp">
            <div class="meun-item" style="
    padding-left: 20px;
">
                <img src="${pageContext.request.contextPath}/images/ccps_images/icon_change_grey.png">修改个人信息
            </div>
        </a>

    </div>
    <!-- 右侧具体内容栏目 -->
    <div id="rightContent">

        <!-- 权限管理模块 -->
        <div role="tabpanel" class="tab-pane" id="char">


            <div class="data-div">

                <div class="tablebody">
                    <%--newsAction??flag=release--%>
                    <form action="${pageContext.request.contextPath}/newsfileAction" method="post" enctype="multipart/form-data">
                        <div class="row" style="margin-left: 0;margin-right: 0">
                            <div class="col-xs-1 ">

                            </div>
                            <div class="col-xs-4" style="font-size: 18px">
                                新闻标题：
                            </div>
                            <div class="col-xs-5">
                                <input type="text" name="title" style="height: 50px;
                                     width: 400px;
                                        margin-left: -200px;margin-top: 10px">
                                <input type="hidden" name="sflag" value="0" /></br>
                            </div>
                            <div class="col-xs-2">

                            </div>
                        </div>
                        <div class="row" style="margin-left: 0;margin-right: 0">
                            <div class="col-xs-1 ">

                            </div>
                            <div class="col-xs-4" style="font-size: 18px">
                                新闻栏目：
                            </div>
                            <div class="col-xs-5">
                                <select name="typeid" style="margin-top: 10px;
                                margin-left: -200px;
                                width: 400px;
                                height: 50px;
                                font-size: 14px;">
                                    <option value="0">请选择类别</option>
                                    <option value="1">财经</option>
                                    <option value="2">体育</option>
                                    <option value="3">科技</option>
                                </select>
                            </div>
                            <div class="col-xs-2">

                            </div>

                        </div>
                        <div id="adddiv"class="row" style="height: 100%;padding-top: 15px;padding-bottom: 15px;margin-left: 0;margin-right: 0">
                            <p id="p" style="font-size: 18px;    margin-left: 115px;
    font-size: 18px;
    line-height: 70px;display: inline-block"><input type="button" value="添加附件📎" id="add" style="position: absolute"></p></br>
                            <input id="upload" type="file" name="upload" /></br>
                        </div>

                        <textarea id="txt" name="content" id="content" value="123"/></textarea>
                        <input type="submit" id="sub" style="width: 100px;
    height: 30px;
    margin-top: 50px;
    margin-right: 20px;
    background-color:lawngreen;
    color: white;
}"/>
                        <input type="reset" name="" id="reset" value="重置" style="width: 100px;
    height: 30px;
    margin-top: 50px;
    margin-right: 20px;
    background-color:red;
    color: white;
}"/>
                    </form>
                    <script type="text/javascript">
                        window.onload = function () {
                            editor = CKEDITOR.replace('content'); //参数‘content’是textarea元素的name属性值，而非id属性值
                            CKFinder.setupCKEditor(editor, '${pageContext.request.contextPath}/ckfinder/');
                        }
                    </script>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
