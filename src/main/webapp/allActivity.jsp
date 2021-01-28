<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%  String loginname = (String) session.getAttribute("loginname");  %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>

<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>活动内容</title>

    <link href="jules/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="jules/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="jules/css/animate.min.css" rel="stylesheet">
    <link href="jules/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">

    </style>
    <script type="text/javascript" src="js/jquery.min.js">
    </script>
    <script src="js/qrcode.min.js">
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight article">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="text-center ">

                        <div class="ibox float-e-margins">
                            <div class="form-group" style="height: 50px;">
                                <label class="col-sm-2" style="a">欢迎您，<%=loginname%>已成功登录</label>
                            </div>
                            <div class="ibox-title" style="text-align: center">
                                <h2>活动内容</h2>
                            </div>
                            <div class="ibox-content" style="text-align:center">
                                <div class="form-group" style="height: 50px;font-size: 18px">
                                    <label class="col-sm-2" style="a">目录：</label>
                                </div>
                                <div class="form-group" style="height: 40px;font-size: 20px">
                                    <label class="col-sm-4 control-label">1.设备管理</label>
                                </div>
                                <div class="form-group" style="height: 40px;font-size: 20px">

                                    <label class="col-sm-4 control-label">2.咨询管理</label>
                                    <span>${data.ReceiveAddress}</span>
                                </div>
                                <div class="form-group" style="height: 40px;font-size: 20px">
                                    <label class="col-sm-2" style="a">3.订单管理</label>
                                </div>
                                <div class="form-group" style="height: 40px;font-size: 20px">
                                    <label class="col-sm-2 control-label">4.购物车管理</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="jules/js/jquery.min.js?v=2.1.4"></script>
<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
<script src="jules/js/content.min.js?v=1.0.0"></script>
</body>
</html>