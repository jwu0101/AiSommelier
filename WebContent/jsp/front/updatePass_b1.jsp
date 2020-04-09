<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
	<jsp:param value="true" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="./css/front/updatePass_b1.css" type="text/css"/>
<link rel="stylesheet" href="./css/front/common_b1.css"/>
<script src="./js/front/updatePass_new.js"></script>

<div class="body">
	<img src="./imgs/common/WebPhoto/photo-02.jpg" style="width:100%">
	<div class="bgImg16 bgImg">
		<div class="content">
			<div class="label">

				<font face="微軟正黑體">
					<p style="color:#A11E4A;font-size:1.5vw;margin-top:5vw"><b>忘記密碼</b></p>
					<label>會員帳號(E-mail)*</label></br>
					<input style="width:25vw;height:2vw" type="text" disabled="disabled" id="email" name="email" value="${email }"  placeholder="xxx@xxx.xxx"required="required"/></br></br>
					<label>請輸入新密碼*</label></br>
					<input style="width:25vw;height:2vw" type="password" name="password"  id="password"　 required="required"/></br></br>
					<label>確認密碼*</label></br>
					<input style="width:25vw;height:2vw" type="password" name="password2"   id="password2"  required="required"/></br></br>
					
					<div><input type="button" class="btn" onclick="update()" value="確認更改" /></div>

				</font>	
			</div>
		</div>
 	</div>
 </div> 
        
<jsp:include page="footer.jsp"/>