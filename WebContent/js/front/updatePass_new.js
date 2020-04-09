function update() {
			var email = $("#email").val();
			var password = $("#password").val();
			var password2 = $("#password2").val();
			if(email === "" || password === "" || password2 === ""){
				alert("欄位請勿空白");
				return;
			}
			if(password != password2){
				alert("兩次密碼輸入不一樣");
				return;
			}
			$.post("./ForgetPassServlet_b1",{ "email" : email , "password" : password} , function(data){
				if(data === "已成功變更密碼"){
					alert(data);
					window.location.href="jsp/front/login_b1.jsp";
				}else{
					alert(data);
				}
			});
		}

		$(document).ready(function(){

			$(window).resize(function () {
				resize();
			});
			
		    function resize(){
				var bodyh = $(".content").height();
				windowResize(bodyh);
			}

			function windowResize(bodyh){
				var vh = $(window).height();
				var headerh = $(".header").height();
				var banner = $(".banner").height();
				var banner2 = $(".banner2").height();
				var footerh = $(".footer").height();
				var otherh = vh - headerh - footerh - banner - banner2;
				if (bodyh < otherh) {
					$(".footer").addClass("positionFix");
					$(".bodyRight").add(".bgImg").height(otherh);
				}else{
					$(".footer").removeClass("positionFix");
					$(".bodyRight").add(".bgImg").height(bodyh);
				}
			}
			});