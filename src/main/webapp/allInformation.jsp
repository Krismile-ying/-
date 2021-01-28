<%@page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<title>所有咨询</title>
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
                                          aria-expanded="true">咨询信息</a></li>
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
                    <th>咨询编号</th>
                    <th>咨询内容</th>
                    <th>咨询图片</th>
                    <th>咨询建立时间</th>
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
            ajax : "findAllInformation.action",
            aoColumns : [ {
                "data" : "InformationID"
            }, {
                "data" : "InformationContent"
            } ,{
                "data" : "InformationImage"
            } ,{
                "data" : "InformationCreateTime"
            }, {
                "data": "InformationID",
                "render": function (
                    data, type, full, callback) {
                    var html = "<div style=\'margin-top:5px;\' ><a  href='showInformationByIdFromWebPortol?infoId=" + data + " ' class=\'btn btn-success btn-xs\' ><i class=\'fa fa-arrow-up\'></i>查看具体咨询</a><div>"
                    html += "<div style='margin-top:5px' ><a href='deleteInformation?deleteInformationID=" + data + " ' class='btn btn-success btn-xs' style='padding-top:5px'><i class='fa fa-close'></i>删除</a></div>";
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


