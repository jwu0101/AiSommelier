<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員明細</title>
<!-- 連結待確認 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/back/members_c1.css" />
<!-- 連結待確認 -->
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
				<td>姓名 : ${ requestScope.members['name'] }</td>
			</tr>
			<tr>
				<td>聯絡電話 : ${ requestScope.members['mobile'] }</td>
			</tr>
			<tr>
				<td>通訊地址 : ${ requestScope.members['address'] }</td>
			</tr>
			<tr>
				<td>會員類型 : ${ requestScope.members['type'] }</td>
			</tr>
			<tr>
				<td>會員狀態 : ${ requestScope.members['status'] }</td>
			</tr>
			<tr>
				<c:set var="crTtime" value="${ requestScope.members['createTime'] }" />
				<c:set var="crtime" value="${fn:replace(crTtime,'T',' ')}" />
				<td>建立者 : ${ requestScope.members['createUser'] }</td>
			</tr>
			<tr>
				<td>建立時間 : ${ crtime }</td>
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
				<td><button class="pinkbtn" onclick="javascript:location.href='./showMembers_c1'" >回查詢頁</button></td>
			</tr>
			

		</table>
	</div>
	<div class="righttitle">
		<span>會員明細&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	</div>
</body>
</html>