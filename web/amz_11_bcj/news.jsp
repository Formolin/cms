<%--
  Created by IntelliJ IDEA./Users/liujiang/Idea/cms/web/amz_11_bcj/news.jsp
  User: liujiangamz_11_bcj/news.jsp/Users/liujiang/Idea/cms/web/amz_11_bcj/news.jsp
  Date: 2018/8/15
  Time: 21:23amz_11_bcj/news.jsp
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!--360 browser -->
    <meta name="renderer" content="webkit">
    <meta name="author" content="wos">
    <!-- Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="amz_11_bcj/images/i/app.png">
    <!--Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="amz_11_bcj/images/i/app.png">
    <!--Win8 or 10 -->
    <meta name="msapplication-TileImage" content="images/i/app.png">
    <meta name="msapplication-TileColor" content="#e1652f">

    <link rel="icon" type="image/png" href="amz_11_bcj/images/i/favicon.png">
    <link rel="stylesheet" href="amz_11_bcj/assets/css/amazeui.min.css">
    <link rel="stylesheet" href="amz_11_bcj/css/public.css">

    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="amz_11_bcj/assets/js/jquery.min.js"></script>
    <!--<![endif]-->
    <!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="amz_11_bcj/assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
    <script src="amz_11_bcj/assets/js/amazeui.min.js"></script>
    <script src="amz_11_bcj/js/public.js"></script>
</head>
<body>

<header class="am-topbar am-topbar-fixed-top wos-header">
    <div class="am-container">
        <h1 class="am-topbar-brand">
            <a href="#"><img src="amz_11_bcj/images/logo.png" alt=""></a>
        </h1>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-warning am-show-sm-only"
                data-am-collapse="{target: '#collapse-head'}">
            <span class="am-sr-only">导航切换</span>
            <span class="am-icon-bars"></span>
        </button>

        <div class="am-collapse am-topbar-collapse" id="collapse-head">
            <ul class="am-nav am-nav-pills am-topbar-nav">
                <li class="am-active"><a href="index.jsp">首页</a></li>
                <li><a href="#">资讯</a></li>
                <li><a href="#">专栏</a></li>
                <li class="am-dropdown" data-am-dropdown>
                    <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                        案例 <span class="am-icon-caret-down"></span>
                    </a>
                    <ul class="am-dropdown-content">
                        <li class="am-dropdown-header">案例</li>
                        <li><a href="#">1. 游戏案例</a></li>
                        <li><a href="#">2. 营销案例</a></li>
                        <li><a href="#">3. 工具案例</a></li>
                    </ul>
                </li>
                <li><a href="#">评测</a></li>
                <li><a href="#">活动</a></li>
            </ul>

            <div class="am-topbar-right">
                <button class="am-btn am-btn-default am-topbar-btn am-btn-sm"><span class="am-icon-pencil"></span>注册
                </button>
            </div>

            <div class="am-topbar-right">
                <button class="am-btn am-btn-danger am-topbar-btn am-btn-sm"><span class="am-icon-user"></span> 登录
                </button>
            </div>
        </div>
    </div>
</header>


<div class="am-g am-container">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-8">
        <div class="newstitles">
            <h2>${title}</h2>
            <img src="amz_11_bcj/Temp-images/face.jpg" class="face">
            <span>Rosis</span>
            时间：${creattime} 栏目：${typeid}
        </div>
        <a href="#"><img src="amz_11_bcj/Temp-images/ad2.png" class="am-img-responsive" width="100%"/></a>
        <%--request.setAttribute("newsFlag",newsFlag);--%>
        <%--request.setAttribute("nid",nid);--%>
        <%--request.setAttribute("title",title);--%>
        <%--request.setAttribute("typeid",typeid);--%>
        <%--request.setAttribute("content",content);--%>
        <%--request.setAttribute("creattime",creattime);--%>
        <div class="contents">
            ${content}

        </div>
        <div class="shang">
            <img src="amz_11_bcj/images/shang.png">
        </div>
        <!--data-ds-short-name="amazeui" 多说的用户名-->
        <div data-am-widget="duoshuo" class="am-duoshuo am-duoshuo-default" data-ds-short-name="amazeui">
            <div class="ds-thread">
            </div>
        </div>
    </div>
    <%--<div class="am-u-sm-0 am-u-md-0 am-u-lg-4">--%>
    <%--<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">--%>
    <%--<h2 class="am-titlebar-title ">--%>
    <%--个人专栏--%>
    <%--</h2>--%>
    <%--<nav class="am-titlebar-nav">--%>
    <%--<a href="#more">more &raquo;</a>--%>
    <%--</nav>--%>
    <%--</div>--%>

    <%--<div data-am-widget="list_news" class="am-list-news am-list-news-default right-bg">--%>
    <%--<ul class="am-list"  >--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="amz_11_bcj/Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="amz_11_bcj/Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">--%>
    <%--<h2 class="am-titlebar-title ">--%>
    <%--合作专栏--%>
    <%--</h2>--%>
    <%--<nav class="am-titlebar-nav">--%>
    <%--<a href="#more">more &raquo;</a>--%>
    <%--</nav>--%>
    <%--</div>--%>

    <%--<div data-am-widget="list_news" class="am-list-news am-list-news-default right-bg">--%>
    <%--<ul class="am-list">--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg" class="face"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--<div data-am-widget="titlebar" class="am-titlebar am-titlebar-default">--%>
    <%--<h2 class="am-titlebar-title ">--%>
    <%--评测--%>
    <%--</h2>--%>
    <%--<nav class="am-titlebar-nav">--%>
    <%--<a href="#more">more &raquo;</a>--%>
    <%--</nav>--%>
    <%--</div>--%>

    <%--<div data-am-widget="list_news" class="am-list-news am-list-news-default right-bg">--%>
    <%--<ul class="am-list"  >--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />--%>
    <%--<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left">--%>
    <%--<div class="am-u-sm-4 am-list-thumb">--%>
    <%--<a href="http://www.douban.com/online/11624755/">--%>
    <%--<img src="Temp-images/face.jpg"/>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class=" am-u-sm-8 am-list-main">--%>
    <%--<h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/">勾三古寺</a></h3>--%>

    <%--<div class="am-list-item-text">代码压缩和最小化。在这里，我们为你收集了9个最好的JavaScript压缩工具将帮</div>--%>

    <%--</div>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>

    <%--<ul class="am-gallery am-avg-sm-1--%>
    <%--am-avg-md-1 am-avg-lg-1 am-gallery-default" data-am-gallery="{ pureview: true }" >--%>
    <%--<li>--%>
    <%--<div class="am-gallery-item">--%>
    <%--<a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">--%>
    <%--<img src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想"/>--%>
    <%--<h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>--%>
    <%--<div class="am-gallery-desc">--%>
    <%--<div class="am-fr">北京</div>--%>
    <%--<div class="am-fl">2016/11/11</div>--%>
    <%--</div>--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="am-gallery-item">--%>
    <%--<a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">--%>
    <%--<img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>--%>
    <%--<h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>--%>
    <%--<div class="am-gallery-desc">--%>
    <%--<div class="am-fr">北京</div>--%>
    <%--<div class="am-fl">2016/11/11</div>--%>
    <%--</div>--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<div class="am-gallery-item">--%>
    <%--<a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">--%>
    <%--<img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>--%>
    <%--<h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>--%>
    <%--<div class="am-gallery-desc">--%>
    <%--<div class="am-fr">北京</div>--%>
    <%--<div class="am-fl">2016/11/11</div>--%>
    <%--</div>--%>
    <%--</a>--%>
    <%--</div>--%>
    <%--</li>--%>
    <%--</ul>--%>

    <%--</div>--%>
</div>
</div>


<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
    <a href="#top" title="回到顶部">
        <span class="am-gotop-title">回到顶部</span>
        <i class="am-gotop-icon am-icon-chevron-up"></i>
    </a>
</div>

<%--<footer>--%>
<%--<div class="content">--%>
<%--<ul class="am-avg-sm-5 am-avg-md-5 am-avg-lg-5 am-thumbnails">--%>
<%--<li><a href="#">联系我们</a></li>--%>
<%--<li><a href="#">加入我们</a></li>--%>
<%--<li><a href="#">合作伙伴</a></li>--%>
<%--<li><a href="#">广告及服务</a></li>--%>
<%--<li><a href="#">友情链接</a></li>--%>
<%--</ul>--%>
<%--<div class="btnlogo"><img src="images/btnlogo.png"/></div>--%>
<%--<p>Amaze UI出品<br>© 2016 AllMobilize, Inc. Licensed under MIT license. 模板收集自 <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> -  More Templates  <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>.</p>--%>
<%--<div class="w2div">--%>
<%--<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2--%>
<%--am-avg-md-2 am-avg-lg-2 am-gallery-overlay" data-am-gallery="{ pureview: true }" >--%>
<%--<li class="w2">--%>
<%--<div class="am-gallery-item">--%>
<%--<a href="Temp-images/dd.jpg">--%>
<%--<img src="Temp-images/dd.jpg" />--%>
<%--<h3 class="am-gallery-title">订阅号：Amaze UI</h3>--%>
<%--</a>--%>
<%--</div>--%>
<%--</li>--%>
<%--<li   class="w2">--%>
<%--<div class="am-gallery-item">--%>
<%--<a href="Temp-images/dd.jpg">--%>
<%--<img src="Temp-images/dd.jpg"/>--%>
<%--<h3 class="am-gallery-title">服务号：Amaze UI</h3>--%>
<%--</a>--%>
<%--</div>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</footer>--%>
</body>
</html>
