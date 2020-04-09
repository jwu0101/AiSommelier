<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>會員修改</title>
<!-- 連結待確認 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/members_c1.css" />
<!-- 連結待確認 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>

	<div class="upANDdetail">
		<table cellpadding="8">
			<tr>
				<td>會員帳號(E-mail) : ${ requestScope.members["email"] }</td>
			</tr>
			<tr>
				<td>生日 : ${ requestScope.members["bday"] }</td>
			</tr>
			<tr>
				<td>姓名 : <input type="text" id="name" style="width:200px"
					value="${ requestScope.members['name'] }" /></td>
			</tr>
			<tr>
				<td>聯絡電話 : <input type="text" id="mobile"
					value="${ requestScope.members['mobile'] }" /></td>
			</tr>
			<tr>
				<td>通訊地址 : <input type="text" id="address" style="width:255px"
					value="${ requestScope.members['address'] }" /></td>
			</tr>
			<tr>
				<td>會員類型 : <select id="type">
						<option value="一般會員">一般會員</option>
						<option value="管理者">管理者</option>
				</select></td>
			</tr>
			<tr>
				<td>會員狀態 : <select id="status">
						<option value="active">active</option>
						<option value="inactive">inactive</option>
				</select></td>
			</tr>
			<tr>
				<c:set var="upTtime" value="${ requestScope.members['updateTime'] }" />
				<c:set var="uptime"
					value="${fn:replace(upTtime, 
                                'T', ' ')}" />
				<td>最後修改者 : ${ requestScope.members['updateUser'] }</td>
			</tr>
			<tr>
				<td>最後修改時間 : ${ uptime }</td>
			</tr>
			<tr>
				<td><button class="pinkbtn" onclick="update()" >確認修改</button>
				<button class="pinkbtn" onclick="javascript:location.href='./showMembers_c1'" >回查詢頁</button></td>
			</tr>
		</table>
		
		
	</div>
	<div class="righttitle">
		<span>會員修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</div>
	<script>
		var ty = "${requestScope.members['type']}";
		var st = "${requestScope.members['status']}";
		var uemail = "${ requestScope.members['email']}";
		if (ty=="normal")
			$('#type option[value=normal]').attr('selected','selected');
		if (ty=="管理者")
			$('#type option[value=管理者]').attr('selected','selected');
		if (st=="active")
			$('#status option[value=active]').attr('selected', 'selected');
		if (st=="inactive")
			$('#status option[value=inactive]').attr('selected','selected');
		
		function update(){
			$.post("updateMembers_c1",{"uemail":uemail,
				"uname":$("#name").val(),
				"umobile":$("#mobile").val(),
				"uaddress":$("#address").val(),
				"utype":$("#type").val(),
				"ustatus":$("#status").val(),
				"uupdateUser":"admin"},function(data){
					alert("修改完成");
					});
		}
		
	</script>


</body>
</html>