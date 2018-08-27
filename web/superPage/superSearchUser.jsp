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

            //控制修改信息

            $("button[name='revise']").click(function () {
                $("#updateUser").css('display','block');
                <%--//通过ajax向后台传id，后台通过id找出修改的当前用户信息，并返回前台--%>
                $.ajax({
                    type:"get",
                    dataType : "json",
                    url : "${pageContext.request.contextPath}/userAction",
                    data : {
                        updateId : $(this).val(),
                        flag : "revise"
                    },
                    success : function(data) {
                        // alert(JSON.stringify(data));
                        //遍历json
                        $.each(data, function(key, val) {
                            var id = val.id;
                            // alert(id);
                            var depID = val.depID;
                            var userName = val.userName;
                            var userCode = val.userCode;
                            var userSex = val.userSex;
                            var userAge = val.userAge;
                            var userPower = val.userPower;

                            $("#updateUserName").val(userName);
                            $("#updateUserDept").val(depID);
                            $("#updateUserCode").val(userCode);
                            $("#updateUserSex").val(userSex);
                            $("#updateUserAge").val(userAge);
                            $("#updateUserPower").val(userPower);
                            $("#hidId").val(id);

                        });

                    },

                });


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
                            $("#msg1").html("用户名可以修改");

                        } else {
                            $("#msg1").html("用户名已存在");
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
                            $("#msg2").html("必须是6位以上");

                        } else {
                            $("#msg2").html("");
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
                            $("#msg3").html("编号不能为空并且大于4位");

                        } else {
                            $("#msg3").html("");
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
                            $("#msg4").html("年龄不符合");

                        }else {
                            $("#msg4").html("");
                        }
                    },
                });
            })


        })
    </script>
    <style type="text/css">
        input {
            width: 100px;
            outline: none;
            background-color: rgba(0, 0, 0, 0);
            border-radius: 20px;
            color: white;
            padding-left: 8px;
            border: 1px solid white;

        }

        input::-webkit-input-placeholder {
            /* placeholder颜色  */
            color: white;
            /* placeholder字体大小  */
            font-size: 12px;
            /* placeholder位置  */

        }

        .updateUser {
            width: 86%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            position: absolute;
            z-index: 1;
            margin-left: 50px;
            padding: 0;
            display: none;
        }
        .updateUserBox {
            background-color: white;
            width: 590px;
            height: 600px;
            margin: 0,auto;
            margin-left: 305px;
            margin-top: 30px;
            padding-top: 50px;
        }
    </style>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="js/html5shiv.min.js"></script>--%>
    <%--<script src="js/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/assets/css/fresh-bootstrap-table.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>


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
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if ("last".equals(cookies[i].getName())) {
                time = cookies[i].getValue();
            }
        }
    }

    request.setAttribute("time", time);
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
            <p id="logoP"><img id="logo" alt="左右结构项目"
                               src="${pageContext.request.contextPath}/images/ccps_images/logo.png"><span>CMS新闻系统</span>
            </p>
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
        <a href="${pageContext.request.contextPath}/newsAction?flag=superSearchNews">

            <div class="meun-item " style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">新闻查询
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/newsTypeAction?flag=superSearchTypeid">

            <div class="meun-item " style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">栏目查询
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/depAction?flag=superSearchDep">

            <div class="meun-item " style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">部门查询
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/userAction?flag=superSearchUser">
            <div class="meun-item meun-item-active" style="
    padding-left: 20px;
"><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">用户查询
            </div>
        </a>

        <a href="${pageContext.request.contextPath}/superPage/superUpdateUser.jsp">
            <div class="meun-item" style="
    padding-left: 20px;
">
                <img src="${pageContext.request.contextPath}/images/ccps_images/icon_change_grey.png">修改个人信息
            </div>
        </a>

    </div>
    <!-- 右侧具体内容栏目 -->
    <div class="wrapper" style="margin-top: 5px">
        <%--标记，用于进入复合查询页面--%>
        <c:set var="ok" value="ok"></c:set>
        <div class="container">
            <%--修改用户--%>
            <div class="updateUser" id="updateUser">

                <div class="updateUserBox">
                    <p style="text-align: center;" >修改用户信息</p>
                    <c:if test="${ok eq issearch}">
                            <form class="form-horizontal" action="${pageContext.request.contextPath}/userAction?flag=superSearchUpdateOther&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                method="post">
                    </c:if>
                    <c:if test="${no eq issearch}">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/userAction?flag=superUpdateOther&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}"
                             method="post">
                    </c:if>

                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">用户名：</label>
                            <div class="col-xs-5" style="width: 320px">
                                <input type="text" name="username"
                                       class="form-control input-sm duiqi" id="updateUserName"
                                       value=""
                                       style="margin-top: 7px;display: inline-block"><span id="msg1" style="color: red;margin-left: 5px" ></span>
                                <input type="hidden" id="hidId" name="id" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">密码：</label>
                            <div class="col-xs-5" style="width: 320px">
                                <input type="password" name="password"
                                       class="form-control input-sm duiqi" id="updateUserPwd" placeholder=""
                                       style="margin-top: 7px;display: inline-block" ><span id="msg2" style="color: red;margin-left: 5px" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">部门：</label>
                            <div class="col-xs-5" style="width: 320px">

                                <select id="updateUserDept" name="updateUserDept" class="form-control input-sm duiqi"
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
                            <div class="col-xs-5" style="width: 378px">
                                <input type="text" name="updateUserCode"
                                       class="form-control input-sm duiqi" id="updateUserCode" placeholder=""
                                       style="margin-top: 7px;display: inline-block" value=""><span id="msg3" style="color: red;margin-left: 5px" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">性别：</label>
                            <div class="col-xs-5"
                                 style="margin-top: 6px;">
                                <input type="radio" name="updateUserSex" id="sexman" placeholder=""
                                       style="margin-top: 7px;width: 20px" value='男' checked="checked">男 <input type="radio" name="updateUserSex"
                                                                                   id="sexwoman" placeholder=""
                                                                                   style="margin-top: 7px;width: 20px"
                                                                                   value="女">女

                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">年龄：</label>
                            <div class="col-xs-5" style="width: 320px">
                                <input type="text" name="updateUserAge"
                                       class="form-control input-sm duiqi" id="updateUserAge" placeholder=""
                                       style="margin-top: 7px;display: inline-block" value=""><span id="msg4" style="color: red;margin-left: 5px" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">权限：</label>
                            <div class="col-xs-5" >

                                <select name="updateUserPower" id="updateUserPower" class="form-control input-sm duiqi"
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
            <%--修改用户结束--%>
            <div class="row">
                <div class="col-md-8 col-md-offset-2">

                    <div class="fresh-table full-color-orange" style="width: 1000px;">
                        <!--    Available colors for the full background: full-color-blue, full-color-azure, full-color-green, full-color-red, full-color-orange
                                Available colors only for the toolbar: toolbar-color-blue, toolbar-color-azure, toolbar-color-green, toolbar-color-red, toolbar-color-orange
                        -->

                        <div class="toolbar" style="padding-top: 40px;
    padding-bottom: 20px;
    padding-left: 20px;">
                            <%--复合查询--%>

                                <a href="${pageContext.request.contextPath}/superPage/addUser.jsp"><div style="
    width:  80px;
    border: 1px solid white;
    text-align:  center;
    color:  white;
    position:  absolute;
    font-weight:  bold;
">添加用户</div></a>
                            <form action="${pageContext.request.contextPath}/userAction?flag=superCompoundSearch&showRowCount=${showRowCount}"
                                  method="post"
                                  style="padding-left: 175px;">
                                <input type="hidden" name="id">
                                <input type="text" value="${searchUserName}" placeholder="请输入姓名..." style="color: white"
                                       name="searchUserName">

                                <select name="searchDepID" style="width: 100px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    margin-left: 10px;
    color: white;">
                                    <option value="-1">请选择部门</option>
                                    <option value="4" ${searchDepID == 4?"selected='selected'":""}>学习部</option>
                                    <option value="5" ${searchDepID == 5?"selected='selected'":""}>市场部</option>
                                </select>
                                <input value="${searchUserCode}" name="searchUserCode" type="text"
                                       placeholder="请输入编号..."
                                       style="margin-right: 10px;color: white;">
                                <select name="searchUserSex" style="width: 100px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    color: white;">
                                    <option value="">选择性别</option>
                                    <option value="男" ${searchUserSex eq "男"?"selected='selected'":""}>男</option>
                                    <option value="女" ${searchUserSex eq "女"?"selected='selected'":""}>女</option>
                                </select>
                                <input value="${searchUserAge == -1 ? "":searchUserAge}" name="searchUserAge"
                                       type="text" placeholder="请输入年龄..."
                                       style="margin-right: 10px;color:white">
                                <select name="searchUserPower" style="width: 100px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    color: white;">
                                    <option value="-1">请选择权限</option>
                                    <option value="0" ${searchUserPower == 0?"selected='selected'":""}>普通管理员</option>
                                    <option value="1" ${searchUserPower == 1?"selected='selected'":""}>超级管理员</option>
                                </select>
                                <input type="submit" value="查询" style="color: white;">
                            </form>

                        </div>

                        <table id="fresh-table" class="table">
                            <c:if test="${ok eq issearch}">
                            <form action="${pageContext.request.contextPath}/userAction?flag=batch&superCompoundSearch=superCompoundSearch&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                  method="post">

                                </c:if>
                                <c:if test="${no eq issearch}">
                                <form action="${pageContext.request.contextPath}/userAction?flag=batch&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}"
                                      method="post">

                                    </c:if>
                                    <thead>
                                    <th data-field="id">ID</th>
                                    <th data-field="id">部门</th>
                                    <th data-field="salary" data-sortable="true">姓名</th>
                                    <th data-field="country" data-sortable="true">密码</th>
                                    <th data-field="country" data-sortable="true">编号</th>
                                    <th data-field="country" data-sortable="true">性别</th>
                                    <th data-field="country" data-sortable="true">年龄</th>
                                    <th data-field="country" data-sortable="true">权限</th>
                                    <th data-field="country" data-sortable="true">管理</th>
                                    <th data-field="country" data-sortable="true"><input type="submit" value="批量删除">
                                    </th>

                                    </thead>
                                    <tbody>
                                    <!--//for循环-->
                                    <c:forEach items="${findAllUser }" var="list">
                                        <c:set var="power1" value="普通管理员"></c:set>
                                        <c:set var="power2" value="超级管理员"></c:set>
                                        <c:set var="dep4" value="学习部"></c:set>
                                        <c:set var="dep5" value="市场部"></c:set>
                                    <tr>
                                        <td>${list.id }</td>
                                        <td>
                                            <c:if test="${list.depID==4 }">
                                                ${dep4 }
                                            </c:if>
                                            <c:if test="${list.depID==5 }">
                                                ${dep5 }
                                            </c:if>
                                        </td>
                                        <td>${list.userName }</td>
                                        <td>${list.userPwd }</td>
                                        <td>${list.userCode }</td>
                                        <td>${list.userSex }</td>
                                        <td>${list.userAge }</td>
                                        <td><c:if test="${list.userPower==0 }">${power1 }</c:if> <c:if
                                                test="${list.userPower==1 }">
                                            <font color='black'><b>${power2 }</b></font>
                                        </c:if></td>
                                        <td>
                                            <c:if test="${ok eq issearch}">

                                                <button type="button" name="revise" value="${list.id}" style="background-color: cadetblue;">修改</button>
                                                <button type="button" onclick="window.location.href='userAction?flag=delete&superCompoundSearch=superCompoundSearch&id=${list.id }&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}'"
                                                        style="    background-color: slateblue;">删除
                                                </button>

                                            </c:if>
                                            <c:if test="${no eq issearch}">
                                                <button type="button" name="revise" value="${list.id}" style="background-color: cadetblue;">修改</button>
                                                <button type="button" onclick="window.location.href='userAction?flag=delete&id=${list.id }&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}'"
                                                        style="    background-color: slateblue;">删除
                                                </button>

                                            </c:if>
                                        </td>
                                        <td style="text-align: right;">
                                            <input type="checkbox" name="ids" value="${list.id}">
                                        </td>

                                    </tr>
                                    </c:forEach>

                                </form>
                                </tbody>
                        </table>
                        <div class="toolbar">
                            <%--分页--%>
                            <p style="    display: inline-block;
                                    color: white;
                                    margin-left: 20px;
                                    margin-right: 10px;">显示数据
                            </p>

                            <select name="showRowCount" id="showRowCount" style="background-color: rgba(0,0,0,0);
                                        color: white;
                                        border-color: white;">
                                <c:if test="${showRowCount == 5}">
                                    <option value="5" selected="selected">5</option>
                                    <option value="8">8</option>
                                    <option value="11">11</option>
                                </c:if>
                                <c:if test="${showRowCount == 8}">
                                    <option value="5">5</option>
                                    <option value="8" selected="selected">8</option>
                                    <option value="11">11</option>
                                </c:if>
                                <c:if test="${showRowCount == 11}">
                                    <option value="5">5</option>
                                    <option value="8">8</option>
                                    <option value="11" selected="selected">11</option>
                                </c:if>

                            </select>
                            <script type="text/javascript">
                                window.onload = function () {
                                    var showRowCount = document.getElementById("showRowCount");
                                    showRowCount.onchange = function () {
                                        if (${ok eq issearch}) {
                                            location.href = "${pageContext.request.contextPath}/userAction?flag=superCompoundSearch&showRowCount=" + this.value + "&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}";
                                        } else {

                                            location.href = "${pageContext.request.contextPath}/userAction?flag=superSearchUser&showRowCount=" + this.value;
                                        }
                                    }

                                }

                            </script>


                            <c:if test="${pageNow != 1}">
                                <c:if test="${ok eq issearch}">
                                    <a href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=1&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">首页</a>

                                    <a href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${requestScope.pageNow-1 }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">上一页</a>
                                </c:if>
                                <c:if test="${no eq issearch}">
                                    <a href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=1&showRowCount=${showRowCount}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">首页</a>

                                    <a href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${requestScope.pageNow-1 }&showRowCount=${showRowCount}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">上一页</a>
                                </c:if>

                            </c:if>
                            <c:if test="${pageNow == 1}">
                                <a href="#"
                                   style="margin-left: 140px;"></a>

                                <a href="#"
                                   style=""></a>
                            </c:if>


                            <c:if test="${pageNow>3}">


                                <c:choose>
                                    <c:when test="${requestScope.pageNow == requestScope.pageCount}">
                                        <c:set var="end" value="${requestScope.pageCount}"></c:set>
                                    </c:when>
                                    <c:when test="${requestScope.pageNow == pageCount - 1 }">
                                        <c:set var="end" value="${requestScope.pageNow + 1 }"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="end" value="${pageNow+2}"></c:set>
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${ok eq issearch}">
                                    <c:forEach var="i" begin="${pageNow-2}" end="${end}" step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${no eq issearch}">
                                    <c:forEach var="i" begin="${pageNow-2}" end="${end}" step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </c:if>

                            <c:if test="${(pageNow>0 && pageNow <= 3) && end <= requestScope.pageCount}">
                                <c:if test="${ok eq issearch}">
                                    <c:forEach var="i" begin="${requestScope.start }" end="${requestScope.end }"
                                               step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${no eq issearch}">
                                    <c:forEach var="i" begin="${requestScope.start }" end="${requestScope.end }"
                                               step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </c:if>
                            <c:if test="${(pageNow>0 && pageNow < 6) && end > requestScope.pageCount}">
                                <c:if test="${ok eq issearch}">
                                    <c:forEach var="i" begin="${requestScope.start}" end="${requestScope.pageCount}"
                                               step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${no eq issearch}">
                                    <c:forEach var="i" begin="${requestScope.start}" end="${requestScope.pageCount}"
                                               step="1">
                                        <c:if test="${i==requestScope.pageNow }">
                                            <a style='color:black;background-color: white;display:inline-block;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;'
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                        <c:if test="${i!=requestScope.pageNow }">
                                            <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                               href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>

                            </c:if>


                            <c:if test="${(requestScope.pageNow != pageCount) && pageCount != 0}">
                                <c:if test="${ok eq issearch}">

                                    <a href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${requestScope.pageNow+1 }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">下一页</a>
                                    <a href="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${requestScope.pageCount }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">尾页</a>

                                </c:if>
                                <c:if test="${no eq issearch}">
                                    <a href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${requestScope.pageNow+1 }&showRowCount=${showRowCount}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">下一页</a>
                                    <a href="${pageContext.request.contextPath }/userAction?flag=superSearchUser&pageNow=${requestScope.pageCount }&showRowCount=${showRowCount}"
                                       style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">尾页</a>
                                </c:if>
                            </c:if>
                            <c:if test="${(requestScope.pageNow == pageCount) || pageCount == 0}">
                                <a style="margin-left: 150px"></a>

                            </c:if>


                            <p style="display: inline-block;
                                    color: white;
                                    margin-left: 20px;
                                    margin-right: 10px;">总页数：${requestScope.pageCount}</p>
                            <c:if test="${ok eq issearch}">
                                <form action="${pageContext.request.contextPath }/userAction?flag=superCompoundSearch&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}&searchUserName=${searchUserName}&searchDepID=${searchDepID}&searchUserCode=${searchUserCode}&searchUserSex=${searchUserSex}&searchUserAge=${searchUserAge}&searchUserPower=${searchUserPower}"
                                      method="post" style="display: inline-block">
                                    <input type="text" name="page" style="width: 50px;margin-left: 20px;color: white;"
                                           placeholder="       页"><input
                                        type="submit" value="跳转" style="width: 50px;color: white">
                                </form>
                            </c:if>
                            <c:if test="${no eq issearch}">
                                <form action="${pageContext.request.contextPath }/userAction?flag=superSearchUser&showRowCount=${showRowCount}"
                                      method="post" style="display: inline-block">
                                    <input type="text" name="page" style="width: 50px;margin-left: 20px;color: white;"
                                           placeholder="       页"><input
                                        type="submit" value="跳转" style="width: 50px;color: white">
                                </form>
                            </c:if>

                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
