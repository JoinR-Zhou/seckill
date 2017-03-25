<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="comment/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<%@ include file="comment/head.jsp" %>
<title>秒杀详情页</title>

</head>
<body>
<div class="container">
	<div class="panel panel-default text-center">
		
		<div class="panel-heading">
			<h1>${seckill.seckillName}</h1>
		</div>
		
		<div class="panel-body">
			<h2 class="text-danger">
				<!-- 显示time图标 -->
				<span class="glyphicon glyphicon-time"></span>
				<!-- 展示倒计时 -->
				<span class="glyphicon" id="seckill-box"></span>
			</h2>
		</div>
	</div>
</div>
<!-- 登陆弹出层，输入电话 -->
<div id="killPhoneModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-centent">
			<div class="modal-header">
				<h3 class="modal-title text-center">
					<span class="glyphicon glyphicon-phone"></span>秒杀电话:
				</h3>
			</div>
			
			<div class="modal-body">
				<div>
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号^o^" class="form-control" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<!-- 验证信息 -->
				<span id="killPhoneMessage" class="glyphicon"></span>
				<button id="killPhoneBtn" class="btn btn-default" type="button">
					<span class="glyphicon glyphicon-phone"></span>
					Submit
				</button>
				
			</div>
			
		</div>
	</div>
</div>

</body>

<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery CountDown插件 -->
<script src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<!-- jQuery Cookie插件 -->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="/resources/script/seckill.js"></script>
<script type="text/javascript">
	$(function(){
	//使用El的表达式传入参数
		seckill.detail.inti({
			seckillId : ${seckill.seckillId},
			startTime : ${seckill.startTime.time},//毫秒数
			endTime : ${seckill.endTime.time}
		});
	
	});

</script>
</html>
