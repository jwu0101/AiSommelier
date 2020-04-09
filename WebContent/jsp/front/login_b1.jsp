<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   import="model.User_b1"
    %>
      
<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/login_b1.css" type="text/css"/>
<script src="../../js/front/login_b1.js"></script>
	<div class="body">
 		<img src="../../imgs/common/WebPhoto/photo-02.jpg" style="width:100%" class="banner2">
		<div class="bgImg16 bgImg">
			<div class="content">
				<div class="label" style="font-family: Arial, Microsoft JhengHei;">
					<p style="color:#A11E4A;font-size:1.5vw"><b>會員登入</b></p>
					<label>會員帳號(E-mail)<span class="star">&nbsp;*</span></label><br/>
					<input style="width:25vw;height:1.7vw" type="text" id="email" name="email" placeholder="xxx@xxx.xxx"required="required"/>
					<br/><br/>
					<label>密碼<span class="star">&nbsp;*</span></label><br>
					<input style="width:25vw;height:1.7vw" type="password" id="password"required="required"/><br/><br/>
					<div><input type="checkbox" name="autologin" value="auto">記住我</div>
					<div style="position: relative; overflow: auto;margin-top: 2vh;">
						<div class="a" style="float:right">
							<a style="color:#E79550" href="#" onclick="forgetpass()">忘記密碼?</a><br/>
							<a style="color:#E79550" href="add_b1.jsp">新會員註冊</a>
						</div>
						<div style="position: absolute; bottom: 0; float: left;"><input type="button" class="btn" onclick="login()" value="登入" style="cursor:pointer" /></div>
					</div>
				</div>
			</div>
		 </div>
	 </div>
	 <script>
	  function login() {
			var email = $("#email").val();
			var password = $("#password").val();
			var auto = $("input[name=autologin]:checked").val();
			if(email === "" || password === ""){
				alert("欄位請勿空白");
				return;
			}
			$.post("../../Userlogin_b1" , { "email" : email , "password" : password , "auto" : auto} , function(data){
				if(data === "帳號或密碼錯誤"){
					alert(data);
					return;
				}else{
					if(data == '管理者'){
						location.href="../back/PO.jsp";
					}else{
						location.href="${empty param.nextPage ? 'LoginImages_b2.jsp' : param.nextPage}";
					}
				}
			});
		}
	  </script>
<jsp:include page="footer.jsp"/>