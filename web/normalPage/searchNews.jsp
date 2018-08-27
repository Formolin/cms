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


                //		var time = document.getElementById("time");
                //	time.onfocus = function () {
                //				this.setAttribute("type","datetime-local");
                //
                //	}
                //	time.onblur = function(){
                //		this.setAttribute("type","text");
                ////		alert(this.value);
                ////		2018-08-08T12:59
                ////		alert(new Date());//Tue Aug 07 2018 15:37:36 GMT+0800 (中国标准时间)
                ////		alert(new Date().getTime());//1533627493090
                ////		alert(formatDate(new Date()));//2018-08-07 15:38:43
                ////		alert(formatDate(this.value));//2018-08-06 02:01:00
                //
                //		//如果为空
                //		if (!this.value) {
                //			this.value = formatDate(new Date());
                //		} else{
                //			this.value = formatDate(this.value);
                //		}
                ////2018-2-2 10:23:23
                //	}
                //

                function formatDate(time) {
                    var date = new Date(time);

                    var year = date.getFullYear(),
                        month = date.getMonth() + 1, //月份是从0开始的
                        day = date.getDate(),
                        hour = date.getHours(),
                        min = date.getMinutes(),
                        sec = date.getSeconds();
                    var newTime = year + '-' +
                        (month < 10 ? '0' + month : month) + '-' +
                        (day < 10 ? '0' + day : day) + ' ' +
                        (hour < 10 ? '0' + hour : hour) + ':' +
                        (min < 10 ? '0' + min : min) + ':' +
                        (sec < 10 ? '0' + sec : sec);

                    return newTime;

                }

                var time = document.getElementById("time");
                var startcreattime = document.getElementById("startcreattime");
                var endcreattime = document.getElementById("endcreattime");
                time.onchange = function() {
                    if ("1" == this.value) {
                        //先获取当前时间
                        var now = new Date(); //当前时间
                        var nowTime = now.getTime(); //当前时间的毫秒值   1533641656447
                        var day = now.getDay(); //当前时间返回本周的星期几
                        var oneDayLong = 24 * 60 * 60 * 1000; //一天的长度，按毫秒计算
                        var MondayTime = nowTime - (day - 1) * oneDayLong; //获取周一的时间
                        var monday = new Date(MondayTime);
                        var year = monday.getFullYear();
                        var month = monday.getMonth() + 1;
                        var day = monday.getDate(); //从 Date 对象返回一个月中的某一天 (1 ~ 31)。
                        var day2 = monday.getDate() + 6;
                        var startTime = formatDate(year + '-' + month + '-' + day);
                        var endTime = formatDate(year + '-' + month + '-' + day2);
                        //存到input的值,需先清空所有的值，之后在赋值
                        startcreattime.setAttribute("type", "text");
                        endcreattime.setAttribute("type", "text");
                        startcreattime.value = startTime;
                        endcreattime.value = endTime;

                    }
                }
                //自己修改时间
                startcreattime.onfocus = function() {
                    this.setAttribute("type", "datetime-local");
                }
                endcreattime.onfocus = function() {
                    this.setAttribute("type", "datetime-local");
                }
                startcreattime.onblur = function() {
                    this.setAttribute("type", "text");
                    if (!this.value) {
                        this.value = formatDate(new Date());
                    } else {
                        this.value = formatDate(this.value);
                    }
                }
                endcreattime.onblur = function() {
                    this.setAttribute("type", "text");
                    if (!this.value) {
                        this.value = formatDate(new Date());
                    } else {
                        this.value = formatDate(this.value);
                    }
                }


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
        <a href="${pageContext.request.contextPath}/normalPage/normalUser.jsp">

            <div class="meun-item " style="
        padding-left: 20px;
    "><img
                    src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">发布新闻
            </div>
        </a>
        <a href="${pageContext.request.contextPath}/newsAction?flag=searchNews">

            <div class="meun-item meun-item-active" style="padding-left: 20px;">
            <img src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">新闻查询
            </div>
    </a>

    <a href="${pageContext.request.contextPath}/userAction?flag=searchUser">
        <div class="meun-item " style="
    padding-left: 20px;
"><img
                src="${pageContext.request.contextPath}/images/ccps_images/icon_source.png">用户查询
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
<div class="wrapper" style="margin-top: 50px">
    <div class="container">
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
                        <%--标记，用于进入复合查询页面--%>
                        <c:set var="ok" value="ok"></c:set>
                        <form action="newsAction?flag=compoundSearch" method="post" style="margin-left: 70px">

                            <input type="text" name="searchTitle" id="stitle" value="${searchTitle}"
                                   placeholder="新闻标题..."
                                   style="color: white;width: 140;">
                            <select name="searchTypeid" style="width: 80px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    margin-left: 10px;
    color: white;">
                                <option value="-1" ${searchTypeid == -1 ?"selected='selected'":""}>全部栏目</option>
                                <option value="1" ${searchTypeid == 1 ?"selected='selected'":""}>财经</option>
                                <option value="2" ${searchTypeid == 2 ?"selected='selected'":""}>体育</option>
                                <option value="3" ${searchTypeid == 3 ?"selected='selected'":""}>科技</option>
                            </select>
                            <select name="searchFlag" style="width: 80px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    margin-left: 10px;
    color: white;">
                            <option value="-1" ${searchFlag == -1 ?"selected='selected'":""}>审核状态</option>
                            <option value="0" ${searchFlag == 0 ?"selected='selected'":""}>未审核</option>
                            <option value="1" ${searchFlag == 1 ?"selected='selected'":""}>已审核</option>
                        </select>

                           <p style="display: inline-block;color: white;font-size: 15px;
    font-weight: bold;">时间范围</p> <input type="text" name="searchStartcreattime" id="startcreattime"
                                               value="${searchStartcreattime}" placeholder="开始时间..." style="color: white;width: 150px;"/>
                            <input type="text" name="searchEndcreattime" id="endcreattime" value="${searchEndcreattime}"
                                   placeholder="结束时间..." style="color: white;width: 150px;"/>
                            <select name="time" id="time" style="width: 60px;
    height: 30px;
    background-color: rgba(0,0,0,0);
    border-radius: 12px;
    outline: none;
    border-color: white;
    margin-right: 10px;
    margin-left: 10px;
    color: white;" >
                                <option value="0">全部</option>
                                <option value="1">本周</option>
                                <option value="本月">本月</option>
                                <option value="本年">本年</option>
                                <option value="上周">上周</option>
                                <option value="上月">上月</option>
                                <option value="前年">前年</option>
                            </select> <input type="submit" value="查询" style="color: white;width:80px" />
                        </form>

                    </div>

                    <table id="fresh-table" class="table">
                        <thead>
                        <th data-field="id">ID</th>
                        <th data-field="id">新闻标题</th>
                        <th data-field="country" data-sortable="true">栏目</th>
                        <th data-field="country" data-sortable="true">审核</th>
                        <th data-field="country" data-sortable="true">发布时间</th>


                        </thead>
                        <tbody>
                        <!--//for循环-->
                        <c:forEach items="${findAllNews }" var="list">
                            <c:set var="t1" value="财经"></c:set>
                            <c:set var="t2" value="体育"></c:set>
                            <c:set var="t3" value="科技"></c:set>
                            <c:set var="f0" value="未审核"></c:set>
                            <c:set var="f1" value="已审核"></c:set>

                            <tr>
                                <td>${list.id }</td>

                                <td>${list.title }</td>

                                <td>
                                    <c:if test="${list.typeid == 1 }">
                                        ${t1 }
                                    </c:if>
                                    <c:if test="${list.typeid == 2 }">
                                        ${t2 }
                                    </c:if>
                                    <c:if test="${list.typeid == 3 }">
                                        ${t3 }
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${list.flag==0 }">
                                        ${f0}
                                    </c:if>
                                    <c:if test="${list.flag==1 }">
                                        <font color="black">${f1}</font>
                                    </c:if>
                                </td>
                                <td>${list.creattime }</td>


                            </tr>
                        </c:forEach>
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
                                        location.href = "${pageContext.request.contextPath}/newsAction?flag=compoundSearch&showRowCount=" + this.value + "&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}";
                                    } else {

                                        location.href = "${pageContext.request.contextPath}/newsAction?flag=searchNews&showRowCount=" + this.value;
                                    }
                                }

                            }

                        </script>


                        <c:if test="${pageNow != 1}">
                            <c:if test="${ok eq issearch}">
                                <a href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=1&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">首页</a>

                                <a href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${requestScope.pageNow-1 }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">上一页</a>
                            </c:if>
                            <c:if test="${no eq issearch}">
                                <a href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=1&showRowCount=${showRowCount}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">首页</a>

                                <a href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${requestScope.pageNow-1 }&showRowCount=${showRowCount}"
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${i }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}">${i }</a>
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
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                    </c:if>
                                    <c:if test="${i!=requestScope.pageNow }">
                                        <a style="display:inline-block;color: white;margin-left: 20px;width: 35px;
                                    height: 35px;
                                    font-size: 18px;
                                    border: 1px solid white;
                                    text-align: center;
                                    border-radius: 35px;"
                                           href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${i }&showRowCount=${showRowCount}">${i }</a>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </c:if>


                        <c:if test="${(requestScope.pageNow != pageCount) && pageCount != 0}">
                            <c:if test="${ok eq issearch}">

                                <a href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${requestScope.pageNow+1 }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">下一页</a>
                                <a href="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${requestScope.pageCount }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">尾页</a>

                            </c:if>
                            <c:if test="${no eq issearch}">
                                <a href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${requestScope.pageNow+1 }&showRowCount=${showRowCount}"
                                   style="color: white;margin-left: 20px;width: 20px;height: 20px;font-size: 18px;">下一页</a>
                                <a href="${pageContext.request.contextPath }/newsAction?flag=searchNews&pageNow=${requestScope.pageCount }&showRowCount=${showRowCount}"
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
                            <form action="${pageContext.request.contextPath }/newsAction?flag=compoundSearch&pageNow=${requestScope.pageNow }&showRowCount=${showRowCount}&searchTitle=${searchTitle}&searchTypeid=${searchTypeid}&searchFlag=${searchFlag}&searchStartcreattime=${searchStartcreattime}&searchEndcreattime=${searchEndcreattime}"
                                  method="post" style="display: inline-block">
                                <input type="text" name="page" style="width: 50px;margin-left: 20px;color: white;"
                                       placeholder="       页"><input
                                    type="submit" value="跳转" style="width: 50px;color: white">
                            </form>
                        </c:if>
                        <c:if test="${no eq issearch}">
                            <form action="${pageContext.request.contextPath }/newsAction?flag=searchNews&showRowCount=${showRowCount}"
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
