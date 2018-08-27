<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>下载文件显示页面</title>
  </head>
  
  <body>
  <form action="${pageContext.request.contextPath}/downloadAction?flag=down" method="post">
      <input type="text" name="newsid"/>

      <input type="submit" value="查询新闻id，下载附件"><br>
  </form>
  ${msg}
    <c:forEach var="names" items="${realnames}" begin="0" step="1">

        ${names}<a href="${pageContext.request.contextPath }/DownloadServlet?filename=${names}">下载</a><br>

    </c:forEach>
      <br/>

  <hr>
<p>服务器附件下载</p>
      <!-- 遍历Map集合 -->
    <c:forEach var="me" items="${fileNameMap}">
         ${me.value}<a href="${pageContext.request.contextPath }/DownloadServlet?filename=${me.key}">下载</a>
        <br/>
    </c:forEach>
  </body>
</html>
