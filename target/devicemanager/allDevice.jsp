<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<title>设备分类列表</title>
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
                                          aria-expanded="true">所有设备</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="jules/js/jquery.min.js?v=2.1.4"></script>
<script src="jules/js/bootstrap.min.js?v=3.3.6"></script>
<script src="jules/js/content.min.js?v=1.0.0"></script>
<script src="jules/js/jquery.dataTables.js"></script>
<script src="jules/js/dataTables.bootstrap.js"></script>
<script src="js/dialog.js"></script>
<script type="text/javascript">
    var url = window.location;
    function getUrlParam(url, name) {
        var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
        var matcher = pattern.exec(url);
        var items = null;
        if (matcher != null) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
    }
    var deleteid = getUrlParam(url, 'deviceId');
</script>
<div class="tab-content">
    <div id="tab-1" class="tab-pane active">
        <div class="panel-body">
            <table class="table table-bordered" id="datatable1">
                <thead>
                <tr>
                    <th>选择</th>
                    <th>设备编号</th>
                    <th>设备分类编号</th>
                    <th>设备名称</th>
                    <th>设备价格</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
            //获取url中的参数
            function getUrlParam(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
                var r = window.location.search.substr(1).match(reg); //匹配目标参数
                if (r != null) return unescape(r[2]);
                return null; //返回参数值
            }
            //接收URL中的参数Id
            var id = getUrlParam('deviceClassId');
            console.log('id:' + id);

        $('#datatable1').DataTable({
            destory : true,
            searching : true,
            bLengthChange : true,
            ordering : false,
            bScrollInfinite : true,
            bScrollCollapse : true,
            ajax : "findDeviceByDeviceClassId.action?deviceClassId="+id,
            aoColumns : [
                {
                    "data" : null,
                    //data : 当前单元格的数据，type: 当前列的类型,row: 当前行完整的数据对象
                    "render" : function(
                        data, type,
                        row) {
                        var html = "<input name='checkBox1' type='checkbox' value='"+data['deviceId']+"'></input>"
                        return html;
                    }
                }, {
                "data" : "DeviceID"
            }, {
                "data" : "DeviceClassId"
            },{
                "data" : "DeviceName"
            },{
                "data" : "DevicePrice"
            },{
                "data" : "DeviceID",
                "render" : function(
                    data, type,full,callback) {
                    var html = "<div style=\'margin-top:5px;\' ><a  href='findDeviceById?deviceId="+data+" ' class=\'btn btn-success btn-xs\' ><i class=\'fa fa-arrow-up\'></i>修改</a><div>"
                    html += "<div style='margin-top:5px' ><a href='deleteDevice?deleteDeviceId="+data+" ' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-close'></i>删除</a></div>";
                    return html;
                }
            }],
            "dom":"<'row'<'col-xs-2'l><'#mytool'><f>r>"+"t"+"<'row'<i><p>>",
        });
        $(select).appendTo('#mytool');

        $('#datatable1 tbody').on(
            'click',
            'tr',
            function() {
                $(this).toggleClass('selected');
                //  获得选中行的id
                var data = $('#datatable1').DataTable()
                    .row(this).data();
                //alert("选中id：" + data['id']);

                $.post("findDeviceById", {
                    deviceId : data['deviceId'],
                })
            });
    });
</script>

<script src="matrixadmin/js/bootstrap.min.js"></script>
<script src="matrixadmin/js/jquery.dataTables.js"></script>
<script src="matrixadmin/js/dataTables.bootstrap.js"></script>

</body>
</html>

