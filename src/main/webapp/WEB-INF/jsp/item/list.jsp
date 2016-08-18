<%--
  Created by IntelliJ IDEA.
  User: cartoonface
  Date: 2016/6/15
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width">
    <title>Title</title>
    <jsp:include page="../js.jsp"></jsp:include>
    <jsp:include page="../header.jsp"></jsp:include>
    <link href="/track/resources/css/bootstrap-table.css" rel="stylesheet"/>
    <script src="/track/resources/js/bootstrap-table.js"></script>
    <%--国际化，表格汉化--%>
    <script src="/track/resources/js/bootstrap-table-zh-CN.js"></script>
    <script src="/track/resources/js/console/item.js"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row col-sm-10 col-sm-push-1">
        <div class="col-md-2 col-sm-2">
            <span>舒适性</span> <select id="comfortable">
            <option>所有</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
        </select>
        </div>
        <div class="col-md-2 col-sm-2">
            <span>速度性</span> <select id="speed">
            <option>所有</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
        </select>
        </div>
        <div class="col-md-2 col-sm-2">
            <span class="">平稳性</span> <select id="locus" >
            <option>所有</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
        </select>
        </div>
        <div class="col-md-2 col-sm-2">
            <span>总体评价</span> <select id="all" class="span2">
            <option>所有</option>
            <option>A</option>
            <option>B</option>
            <option>C</option>
        </select>
        </div>
    <div class="col-sm-2">
        <button id="search" type="submit" class="btn btn-inverse">查询</button>
    </div>

    </div>
    <div class="row col-sm-10 col-sm-push-1">
    <table id="table" class="table table-responsive table-bordered">
        </table>
    </div>
</div>
</body>
</html>
