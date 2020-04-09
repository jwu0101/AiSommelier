 function add() {
 			$.post("../../addCustomer_b1", {
 				"email" : $("#email").val(),
 				"name" : $("#name").val(),
 				"password" : $("#password").val(),
 				"mobile" : $("#mobile").val(),
 				"address" : $("#address").val(),
 				"bday" : $("#bday").val()
 			}, function(data) {
 				alert(data);
 				window.location.href="login_b1.jsp";
 			});
 		}
 		
 		
 		$(document).ready(function(){
 			
	
 			//E-MAIL格式檢查
 			$("body").on("change", "#email", function (){
 			$Emailchecking=IsEmail($("#email").val());
 			if($Emailchecking==false){
 			alert("請填寫正確的E-MAIL格式");
 			$("#email").blur();
 			}
 			})
 			function IsEmail(email) {
 			var regex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})*$/;
 			if(!regex.test(email)) {
 			return false;
 			}else{
 			return true;}
 			}
 			
 			//電話格式檢查
 			$("body").on("change", "#mobile", function (){
 				$Mobilechecking=Ismobile($("#mobile").val());
 				if($Mobilechecking==false){
 				alert("請填寫正確的手機格式");
 				$("#mobile").blur();
 				}
 				})
 				function Ismobile(mobile) {
 				var regex = /^09\d{2}-?\d{3}-?\d{3}$/;
 				if(!regex.test(mobile)) {
 				return false;
 				}else{
 				return true;}
 				}
 			
 			//密碼再次確認檢查
 			$("body").on("change", "#password2", function (){
 				$Password2checking=Ispassword2($("#password2").val());
 			})
 				function Ispassword2(password2) {
 				var  password =document.getElementById("password").value;
 				var password2 =document.getElementById("password2").value;
 				if( password!= password2){
 					alert("兩次輸入的密碼不一致！");
 					return false;
 					}
 					}

 			//生日檢查
 			$("body").on("change", "#bday", function (){
 				$Bdaychecking=Isbday($("#bday").val());
 				})
 				
 				function Isbday(bday) {
 				var regex = /^\d{4}-\d{2}-\d{2}$/;
 				if(!regex.test(bday)) {
 					alert("請填寫正確的生日格式");
 					return false;}}
 				
 				

 			});
 		