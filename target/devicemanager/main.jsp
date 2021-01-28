<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%  String loginname = (String) session.getAttribute("loginname");  %>
<%  Integer uid= (Integer)session.getAttribute("uid"); %>
<% String shopingorderId=request.getParameter("shopingorderId"); %>
<%  // 如果loginname为null，则强制将本页面跳转到login.jsp
    if(loginname==null){
%>
<script type="text/javascript">
    window.location.href="login.jsp"
</script>
<%
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>“设备管理”管理系统平台</title>
<head>
    <script src="matrixadmin/js/jquery.min.js"></script>
    <!-- bootstrap基本样式 -->
    <link rel="stylesheet" href="matrixadmin/css/bootstrap.min.css" />
    <!-- matrixadmin项目中的导航栏首页实现“初步导航列表效果”的样式 -->
    <link rel="stylesheet" href="matrixadmin/css/matrix-style.css" />
    <!-- matrixadmin项目中的导航栏首页实现“精细导航列表效果”的样式 -->
    <link rel="stylesheet" href="matrixadmin/css/matrix-media.css" />
    <!-- 导航栏首页中，显示小图片的样式 -->
    <link rel="stylesheet" href="matrixadmin/font-awesome/css/font-awesome.css" />

</head>

<body onunload="unLogin()">
<script type="text/javascript">
    function  unLogin(){

        window.location.href = "unLogin";
    }
</script>
<!-- bootstrap.min.js文件，这是bootstrap库所需的基本js库文件 -->
<script src="matrixadmin/js/bootstrap.min.js"></script>
<!--  matrix. js文件，这是matrixadmin项目中的导航栏首页中导航条中主功能项点击后显示子功能项列表的下拉效果 -->
<script src="matrixadmin/js/matrix.js"></script>
<!--  左边div区块 -->
<div id="header">
    <h1>
        <a>管理平台</a>
    </h1>
</div>
<!-- 右边div区块 -->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="icon icon-user"></i>&nbsp;
                <span class="text">欢迎你，<%=loginname%></span>&nbsp;
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <%--<li>
                    <a class="menu_a" link="allActivity.action">
                        <i class="icon-check"></i> 我的发布
                    </a>
                </li>--%>
                <li class="divider"></li>
                <li>
                    <a class="menu_a" onclick="unLogin(this)">
                        <i class="icon-key"></i> 退出系统
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>
<!-- 新建左侧导航栏区块 -->
<div id="sidebar" >
    <ul id="navbar">
        <li class='submenu'>
            <a herf='#'><i class="icon icon-list"></i><span>设备管理</span></a>
            <ul>
                <li>
                    <a class="menu_a" link="addDeviceClass.jsp"><i
                            class="icon icon-caret-right"></i>添加设备分类</a>
                </li>
                <li>
                    <a class="menu_a" link="allDeviceClass.jsp"><i
                            class="icon icon-caret-right"></i>设备列表</a>
                </li>
         <%--       <li>
                    <a class="menu_a" link="allDevice.jsp"><i
                            class="icon icon-caret-right"></i>设备列表</a>
                </li>--%>
                <li>
                    <a class="menu_a" link="addDevice.jsp"><i
                            class="icon icon-caret-right"></i>添加设备</a>
                </li>

            </ul>
        </li>
        <li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
            <span>咨询管理</span>
        </a>
            <ul>
        <li><a class="menu_a" link="addInformation.html"><i
                class="icon icon-caret-right"></i>添加咨询</a></li>
        <li><a class="menu_a" link="allInformation.jsp"><i
                class="icon icon-caret-right"></i>咨询信息</a></li>
                <li><a class="menu_a" link="updateInformation.html"><i
                        class="icon icon-caret-right"></i>修改咨询</a></li>
            </ul>
        </li>
        <li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
            <span>订单管理</span>
        </a>
            <ul>
                <li><a class="menu_a" link="addShopingorder.jsp"><i
                        class="icon icon-caret-right"></i>添加订单</a></li>
                <c:if test="${uid == 1 }">
                    <li><a class="menu_a" link="allShopingorder.jsp"><i
                            class="icon icon-caret-right"></i>订单列表</a></li>
                </c:if>
                <c:if test="${uid == 2 }">
                    <li><a class="menu_a" link="allShopingorder.jsp"><i
                            class="icon icon-caret-right"></i>订单列表</a></li>
                </c:if>
                <li><a class="menu_a" link="addShopingorderitem.jsp"><i
                        class="icon icon-caret-right"></i>添加订单项</a></li>
            </ul>
        </li>
        <li class="submenu"><a href="#"> <i class="icon icon-lock"></i>
            <span>购物车管理</span>
        </a>
            <ul>
                <li><a class="menu_a" link="addShopingcart.jsp"><i
                        class="icon icon-caret-right"></i>添加购物项</a></li>
                <c:if test="${uid == 1 }">
                    <li><a class="menu_a" link="allShopingcart.jsp"><i
                            class="icon icon-caret-right"></i>购物车列表</a></li>
                </c:if>
                <c:if test="${uid == 2 }">
                    <li><a class="menu_a" link="allShopingcart.jsp"><i
                            class="icon icon-caret-right"></i>购物车列表</a></li>
                </c:if>
                <li><a class="menu_a" link="jiesuanShopingcart.jsp"><i
                        class="icon icon-caret-right"></i>结算购物车</a></li>
            </ul>
        </li>
    </ul>
</div>
<!-- 新建右侧详情列表区块 -->
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="main.jsp" title="返回主页" class="tip-bottom">
                <i class="icon-home"></i>Home
            </a>
            <a href="javascript:history.back();">return</a>
        </div>
    </div>
    <iframe src="allActivity.jsp" id="iframe-main" style="width:100%;"></iframe>
</div>
<script type="text/javascript">
    // 调整相关元素(body、iframe-main和sidebar)高度
    function init() {
        $("body").height($(window).height() - 80);
        $("#iframe-main").height($(window).height() - 90);
        $("#sidebar").height($(window).height() - 50);
    }
    $(function() {
        init();
        $(window).resize(function() {
            init();
        });
    });
</script>
</body>
</html>