<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./js.jsp"></jsp:include>
<jsp:include page="./header.jsp"></jsp:include>
<script src="resources/js/console/index.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1"><span></span></div>
			<div class="col-sm-3"><label>文件路径：</label><input id="path" name="path" type="text" /></div>
			<button class="btn btn-default" id="send">发送</button>
		</div>
	<!-- 	<form class="form-inline">
			<div class="col-sm-3 form-group"><label>文件路径：</label><input id="path" name="path" type="text" /></div>
			<button class="btn btn-default form-group"  id="send">发送</button>

		</form> -->

	</div>


	<div class="panel panel-default">

		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<span>舒适性</span> <select id="comfortable">
						<option>所有</option>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select>
				</div>
				<div class="col-md-2">
					<span>速度性</span> <select id="speed">
						<option>所有</option>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select>
				</div>
				<div class="col-md-2">
					<span class="">平稳性</span> <select id="locus">
						<option>所有</option>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select>
				</div>
				<div class="col-md-2">
					<span>总体评价</span> <select id="all" class="span2">
						<option>所有</option>
						<option>A</option>
						<option>B</option>
						<option>C</option>
					</select>
				</div>

				<button type="submit" class="btn btn-inverse">Search</button>
			</div>
		</div>

		<!-- Table -->

	</div>
	<!-- Single button -->
	<div class="btn-group">
		<button type="button" class="btn btn-default dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			Action <span class="caret"></span>
		</button>
		<ul class="dropdown-menu">
			<li><a href="#">10</a></li>
			<li><a href="#">20</a></li>
			<li><a href="#">50</a></li>
			<li><a href="#">100</a></li>
		</ul>
	</div>
	<nav>
	<ul class="pagination">
		<li><a href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
	</nav>



</body>
</html>