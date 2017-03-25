<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="comment/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@ include file="comment/head.jsp" %>
<title>秒杀列表页</title>

</head>
<body>

<div class="container">
	<div class="panel  panel-deflaut">
		<div class="panel-heading text-center"> 
			<h2>秒杀列表页</h2>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>名称</th>
						<th>库存</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>创建时间</th>
						<th>详情页</th>
					</tr>
				</thead>
				<tbody>
				<cc:forEach items="${list}" var="seckill" >
					<tr>
						<td>${seckill.seckillName}</td>
						<td>${seckill.number}</td>
						<td>
							<fmt:formatDate value="${seckill.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${seckill.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${seckill.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<a class="btn btn-info" href="/seckill/${seckill.seckillId}/detail" target="_blank">link</a>
						</td>
					</tr>
					</cc:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>

<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
