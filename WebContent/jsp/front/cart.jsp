<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="dao.WineDao,dao.PODao,
			model.PO,
			util.CommonUtil,
			java.util.ArrayList,
			java.util.Map,
			java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/PO.css"/>
<script src="../../js/front/PO.js"></script>
<%
	CommonUtil comm = new CommonUtil();
	PODao dao = new PODao();
	String poId = dao.getId();
	ArrayList<Map<String,String>> buylist = session.getAttribute("Buylist") != null ? (ArrayList<Map<String,String>>) session.getAttribute("Buylist") : new ArrayList<Map<String,String>>();
	System.out.println(buylist);
%>
<c:set value="${Users.email}" var="owner" />
<script>
if(${empty owner}){
	location.href="login_b1.jsp?nextPage=cart.jsp";
}
</script>
<div class="body">
	<div class="bgImg18 bgImg">
		<div class="content">
			<p><span>詢問單</span>&emsp;<span><%=poId %></span></p>
			<table>
				<tr>
					<th>產品</th>
					<th>資訊</th>
					<th>數量</th>
					<th>單位</th>
					<th>金額</th>
					<th>小計</th>
					<th></th>
				</tr>
				<c:forEach items='<%=new WineDao().getCartDetail(buylist) %>' var="PODetailV">
				<c:set value="${PODetailV.subtotal + total }" var="total" />
					<tr>
						<td width="10%"><img src="../../${PODetailV.imgPath }" style="height:8vh"></td>
						<td width="30%" class="wineDetail">
							<div id="chName">${PODetailV.wineChName }</div>
							<div>產地：${PODetailV.place }</div>
							<div>品種：${PODetailV.grape }</div>
						</td>
						<td width="15%" class="quantityTD">
							<div class="quantityDiv">
								<div><input type="button" name="minus" value="-"></div>
								<div class="showQuantityNum">&nbsp; <span class="quantity">${PODetailV.quantity }</span>&nbsp; </div>
								<div><input type="button" name="plus" value="+"></div>
							</div>
						</td>
						<td width="5%">${PODetailV.unit }</td>
						<td width="15%"><fmt:formatNumber value="${PODetailV.price}" type="number" pattern="$###,###" /></td>
						<td width="20%"><span class="subtotal"><fmt:formatNumber value="${PODetailV.subtotal}" type="number" pattern="$###,###" /></span></td>
						<td width="5%" class="sendVal">
							<img src="../../imgs/common/x.png" style="width: 10px" name="delTR">
							<input type="hidden" name="wineId" value="${PODetailV.wineId }">
							<input type="hidden" name="price" value="${PODetailV.price }">
							<input type="hidden" name="quantity" value="${PODetailV.quantity }">
							<input type="hidden" name="unit" value="${PODetailV.unit }">
							<input type="hidden" name="subtotal" value=${PODetailV.subtotal }>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${total gt 0 }">
					<tr>
						<td colspan="7" id="totalTD">
							總金額&emsp;<span><fmt:formatNumber value="${total}" type="number" pattern="$###,###" /></span>
						</td>
					</tr>
					<tr>
						<td colspan="7" id="sendTD" style="text-align: right">
							<input type="hidden" id="action" name="action" value="add">
							<input type="hidden" id="poId" name="poId" value="<%=poId %>" >
							<input type="hidden" id="total" name="total" value="${total}">
							<input type="hidden" id="owner" name="owner" value="${owner}">
							<input type="hidden" id="status" name="status" value="等待專員聯繫">
	 						<input type="button" class="button" onclick="location.href='../../wineshop_b2'" value="還想選購其它商品">
							<input type="button" id="send" name="send" value="送出詢問單">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty total }">
					<tr>
						<td colspan="7" style="color:#A11E4A">
							<p style="text-align:center"> 購物車是空的，請先選購商品</p>
							<p style="text-align:center"><input type="button" class="button" onclick="location.href='../../wineshop_b2'" value="進入酒窖選購商品"></p>
						</td>
					</tr>
				</c:if>
				<tr style="display:none;color:#A11E4A;" id="emptyCart">
					<td colspan="7" style="border:0"> 
						您已清空購物車 
						<p style="text-align:center"><input type="button" class="button" onclick="location.href='../../wineshop_b2'" value="進入酒窖選購商品"></p>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"/>