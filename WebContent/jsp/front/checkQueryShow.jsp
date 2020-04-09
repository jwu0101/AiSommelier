<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<hr>
<div class="showCheckBoxRS"></div>
<table id="blocks">
	<tr>
		<c:forEach var="li" items="${qlist}" varStatus="vs">
			<td class="trw" width="25%">
				<a href="wineshop_b2?id=${li.id}">
					<img src="${li.imgPath }" width=150px><br>
					<p>${li.enName}</p>
					<p>${li.chName}</p>
				</a> 
				<hr> 
				<!--EL語法,市價取整數--> 
				<fmt:formatNumber var="na" type="number" value="${li.price/0.8}" maxFractionDigits="0" /> 
				<p1>市價:$${na}</p1>
				<fmt:formatNumber var="mo" type="number" value="${li.price}" maxFractionDigits="0"/>
				<p>會員價:<p2>$${mo}</p2></p>
				
				<input type="hidden" name="id" value="${li.id}">
				<input type="button" value="-" name="del" />
				<span class="quantity">1</span>
				<input type="button" value="+" name="add" />
				<input type="button" name="addcart" style="background-color:#A11E4A;color:white;" value="加入詢問單">
			</td>
			
			${vs.count%4==0 && vs.count<=7 ? '</tr><tr>':''}
			${vs.count%4==0 && vs.count>7 ? '</tr><tr name="divx" style=display:none>':''}
			
		</c:forEach>
	</tr>
</table>