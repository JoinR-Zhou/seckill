//存放主要交互逻辑js代码
var seckill={
		//封装秒杀相关ajax的URL
		URL : {
			
		},
		//验证手机
		validatePhone : function(phone){
			if(phone && phone.lenght==11 && !isNaN(phone)){
				return true;
			} else{
				return false;
			}
		},
		//详情页秒杀逻辑
		detail : {
			//详情页初始化
			inti : function(params){
				//手机验证和登陆，计时交互
				
				//在cookie中查找手机号
				var killPhone=$.cookie("userPhone");
				var seckillId =params['seckillId'];
				var startTime =params['startTime'];
				var endTime =params['endTime'];
				//验证手机
				if(!seckill.validatePhone(killPhone)){
					//绑定phone
					//控制输出
					var killPhoneModal=$("#killPhoneModal");
					killPhoneModal.modal({
						show : true,//显示弹出层
						backdrop : 'static',//禁止位置关闭
						keyboard: false//关闭键盘事件
						
					});
					$("#killPhoneBtn").click(function(){
						var inputPhone=$("#killPhoneKey").val();
						if(seckill.validatePhone(inputPhone)){
							$.cookie("userPhone",inputPhone,{expires: 7, path:"/seckill"});
							window.location.reload();
						}else{
							$("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
						}
					});
				}
				
			}
		}
}







