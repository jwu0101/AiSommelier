<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dao.PODao,model.PO,model.User_b1,java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/PO.css"/>
<script src="../../js/front/PO.js"></script>
<div class="body">
	<div class="banner2"><img src="../../imgs/common/WebPhoto/photo-03.jpg" style="width:100%"></div>
	<div class="bgImg">
		<div class="content">
			<p>詢問單</p>
			<table>
				<tr>
					<th>詢問單號</th>
					<th>下單時間</th>
					<th>總金額</th>
					<th>詢問單狀態</th>
					<th></th>
				</tr>
				<% 
					User_b1 user = session.getAttribute("Users") != null ? (User_b1) session.getAttribute("Users") : null;
					String owner = user != null ? user.getEmail() : "";
					PODao dao = new PODao();
					PO po = new PO();
					po.setOwner(owner);
					ArrayList<PO> poList = dao.query(po);
				%>
				<c:set value="<%=poList %>" var="poList"/>
				<c:forEach items="${poList }" var="po">
					<tr>
						<td><a href="PODetail.jsp?poId=${po.id }&total=${po.total }">${po.id }</a></td>
						<td>${po.createTime }</td>
						<td>${po.total }</td>
						<td><a href="POStatus.jsp?poId=${po.id }&freightId=${po.freightId }&freightName=${po.freightName }">${po.status }</a></td>
						<td>
							<c:if test="${po.status ne '已取消'}">
								<img src="../../imgs/common/x.png" style="width: 10px" name="del" uuid="${po.id }" owner="<%=owner%>">
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty poList }">
					<tr>
						<td colspan="5" style="color:#A11E4A"> 
							<p style="text-align:center">詢問單是空的，請先選購商品</p>
							<p style="text-align:center"><input type="button" class="button" onclick="location.href='../../wineshop_b2'" value="進入酒窖選購商品"></p>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"/>