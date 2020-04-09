<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import="model.User_b1"
     %>

<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/add_b1.css" type="text/css"/>
<script src="../../js/front/add_b1.js"></script>
    <div class="body content">
	  		<img src="../../imgs/common/WebPhoto/photo-06.jpg" style="width:100%">
			<div class="bgImg16 bgImg">
			<div class="content">
					<div class="label">
					
					
						<font face="微軟正黑體">
							<p style="color:#A11E4A;font-size:1.5vw"><b>新會員註冊</b></p>
							<label>會員帳號(E-mail)<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="text" name="email"  id="email" placeholder="xxx@xxx.xxx"required="required"/></br></br>
							<label>姓名<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="text" name="name" id="name" required="required"/></br></br>
							<label>密碼<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="password" name="password"  id="password"　required="required"/></br></br>
							<label>確認密碼<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="password" name="password2"   id="password2"required="required"/></br></br>
							<label>生日(須年滿十八歲)<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="text" name="bday" id="bday"  placeholder="xxxx-xx-xx"required="required"/></br></br>
							<label>聯絡電話<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="text" name="mobile"   id="mobile" pattern="^09\d{2}-?\d{3}-?\d{3}$" placeholder="09xx-xxx-xxx" required="required"/></br></br>
							<label>通訊地址<span class="star">&nbsp;*</span></label></br>
							<input style="width:100%;height:1.7vw" type="text" name="address"  id="address" required="required"/></br></br>
							
							<div><input type="button" class="btn" onclick="add()" value="加入會員" /><a style="color:#E79550;margin-left:14.5vw" href="login_b1.jsp">已經是會員了?</a></div>
							
							
							
						</font>	
					
				</div>
			</div>
		 </div>
	 </div>
	
      <jsp:include page="footer.jsp"/>
      