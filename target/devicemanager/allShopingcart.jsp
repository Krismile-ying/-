<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%  String loginname = (String) session.getAttribute("loginname");  %>
<%  Integer uid= (Integer)session.getAttribute("uid"); %>
<!DOCTYPE html>
<html>
<title>购物车列表</title>
<head>
    <!--必须使用bootstrap.min14ed.css，即3.3.6版本的bootstrap -->
    <!--如果使用bootstrap.css，即2.3.0版本的bootstrap，则会显示不正常 -->
    <link rel="stylesheet" href="matrixadmin/css/bootstrap.min14ed.css" />
    <link rel="stylesheet" href="matrixadmin/css/style.min862f.css" />
    <link rel="stylesheet" href="matrixadmin/css/animate.min.css" />
    <link rel="stylesheet"href="matrixadmin/font-awesome/css/font-awesome.css" />
    <script src="matrixadmin/js/jquery.min.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1"
                                          aria-expanded="true"><%=loginname%>的购物车</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="tab-content">
    <div id="tab-1" class="tab-pane active">
        <div class="panel-body">
            <table class="table table-bordered" id="datatable1">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>购物编号</th>
                    <th>设备编号</th>
                    <th>设备分类编号</th>
                    <th>设备名称</th>
                    <th>设备价格</th>
                    <th>购物数量</th>
                    <th>用户编号</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#datatable1').DataTable({
            destory : true,
            searching : true,
            bLengthChange : true,
            ordering : false,
            bScrollInfinite : true,
            bScrollCollapse : true,
            ajax : "findAllShopingcartByUserId.action?userId=<%=uid%>",
            aoColumns : [ {
                "data" : null,
                //data : 当前单元格的数据，type: 当前列的类型,row: 当前行完整的数据对象
                "render" : function(
                    data, type,
                    row) {
                    var html = "<input name='checkBox1' type='checkbox' value='"+data['id']+"'></input>"
                    return html;
                }
            }, {
                "data" : "ShopingcartID"
            }, {
                "data" : "DeviceID"
            },{
                "data" : "DeviceClassId"
            }, {
                "data" : "DeviceName"
            }, {
                "data" : "DevicePrice"
            }, {
                "data" : "BuyNum"
            }, {
                "data" : "UserID"
            }, {
                "data": "ShopingcartID",
                "render": function (
                    data, type, full, callback) {
                    var html = "<div style=\'margin-top:5px;\' ><a  href='findShopingcartById?shopingcartId=" + data + " ' class=\'btn btn-success btn-xs\' ><i class=\'fa fa-arrow-up\'></i>修改</a><div>"
                    html += "<div style='margin-top:5px' ><a href='deleteShopingcart?deleteShopingcartID=" + data + " ' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-close'></i>删除</a></div>";
                    return html;
                }
            }],
        });
    });
</script>

<script src="matrixadmin/js/bootstrap.min.js"></script>
<script src="matrixadmin/js/jquery.dataTables.js"></script>
<script src="matrixadmin/js/dataTables.bootstrap.js"></script>

</body>


