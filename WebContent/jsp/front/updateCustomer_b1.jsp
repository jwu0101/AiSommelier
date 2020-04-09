<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp">
	<jsp:param value="true" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="./css/front/common_b1.css" />
<link rel="stylesheet" href="./css/front/updateCustomer_b1.css" />
<script src="./js/front/updateCustomer_new.js"></script>
<!-- /banner -->
<div class="body">
	<img class="banner2" src="./imgs/common/WebPhoto/photo-05.jpg" style="width: 100%">
	<div class="bgImg16 bgImg">
		<div class="content">
			<div class="label">

				<font face="微軟正黑體">
					<p style="color: #A11E4A; font-size: 1.5vw">
						<b>會員管理</b>
					</p> <!-- 會員帳號 --> <!-- 更改 --> <span style="color: #8DA8A6;"><input
						style="width:100%; height: 1.7vw" type="hidden"
						value="${user.email}">${user.email} |</span> <!-- 生日 --> <!-- 更改 -->
					<span style="color: #8DA8A6;"><input
						style="width:100%; height: 1.7vw;" type="hidden"
						value="${user.bday}">${user.bday}</span><br>
				<br> <label>姓名*</label><br> <input style="width:100%; height:1.7vw"
					type="text" name="name" id="name" value="${user.name}"
					required="required" /><br>
				<br> <label>密碼*</label><br> <input style="width:100%; height: 1.7vw"
					type="password" name="password" id="password"
					　value="${user.password}" required="required" /><br>
				<br> <label>確認密碼*</label><br> <input style="width:100%; height: 1.7vw"
					type="password" name="password2" id="password2"
					value="${user.password}" required="required" /><br>
				<br> <label>聯絡電話*</label><br> <input style="width:100%; height: 1.7vw"
					type="text" name="mobile" id="mobile" value="${user.mobile}"
					placeholder="09xx-xxx-xxx" required="required" /><br>
				<br> <label>通訊地址*</label><br> <input style="width:100%; height:1.7vw"
					type="text" name="address" id="address" value="${user.address}"
					required="required" /><br>
				<br/>

					<div>
						<input type="button" class="btn" onclick="updateCustomer()" value="確認修改" />
					</div>
				</font>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />