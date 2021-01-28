<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/2/6
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>后台登陆</title>
<head>
    <style>
        .label-zjc {
            color: #B2DFEE;
            padding-left: 20px;
        }
        .h2-zjc {
            color:#00FFFF;
            font-size:35px;
        }
    </style>
    <script src="matrixadmin/js/jquery.min.js"></script>
    <link rel="stylesheet" href="matrixadmin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="matrixadmin/css/matrix-login.css" />
    <link rel="stylesheet" href="matrixadmin/font-awesome/css/font-awesome.css" />
</head>
<body>
<div id="loginbox">
    <div class="normal_text">
        <h2 class="h2-zjc">“设备管理”信息中心</h2>
    </div>
    <form method="get" id="loginform" class="form-vertical"
          action="login.action">
        <div class="control-group">
            <label class="label-zjc">用户名</label>
            <div class="controls">
                <div class="main_input_box">
						<span class="add-on bg_lg">
							<i class="icon-user" style="font-size:16px;"></i>
						</span>
                    <input name="name" type="text" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="label-zjc">登陆密码</label>
            <div class="controls">
                <div class="main_input_box">
						<span class="add-on bg_ly">
							<i class="icon-lock" style="font-size:16px;"></i>
						</span>
                    <input name="password" type="password" />
                </div>
            </div>
        </div>
        <div class="control-group">
            <div>&nbsp;</div>
        </div>
        <div class="form-actions">
				<span class="pull-right">
					<input type="button" id="loginBtn"
                           class="btn btn-success" style="width:480px;"
                           value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" />
				</span>
        </div>
    </form>
    <script type="text/javascript">
        window.onload = function() {
            $('#loginBtn').click(function() {
                $.ajax({
                    type : "get",
                    url : "login.action",
                    dataType : "json",
                    data : $('#loginform').serialize(),
                    success : function(resData) {
//alert("Tomcat服务器的返回值resData：" + resData);
//alert("Tomcat服务器的返回值resData.resResult：" + resData.resResult);
//alert("Tomcat服务器返回的json变量名为resResult，这是在WebAction类的login()函数中设置的json变量resResult值")
                        if (resData.resResult == 0) {
                            window.location.href = 'main.jsp'
                        } else {
                            alert("用户名或密码错误");
                        }
                    },
                    error : function() {
                        alert("失败!");
                    }
                });
            });
        };
    </script>
</div>
</body>
</html>
