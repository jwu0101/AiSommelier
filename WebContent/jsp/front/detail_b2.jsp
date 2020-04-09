<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<jsp:include page="header.jsp">
	<jsp:param value="true" name="isServlet" />
</jsp:include>
<div class="content">
        <link rel="stylesheet" href="./css/front/wine_b2.css"/>
		<link rel="stylesheet" href="./css/front/shop_b2.css"/>
	<!-- <div id="div2">show</div> -->
    <div class="Detailbody"> 
			<div class="showDetail" >
				<!-- <hr> -->
				<table  align=center border=0 >
				<c:forEach var="i" items="${list }">
				<tr>
				<th width=20% rowspan="2"><img alt="${i.enName}" src="${i.imgPath }" width=400px>
				<td width=80% align=left><b>${i.enName}<br>${i.chName}</b>
				<hr>
				<p>${i.feature}</p>
				<br>
				
				<!--EL語法,市價取整數-->
				<fmt:formatNumber var="na" type="number" value="${i.price/0.8}" maxFractionDigits="0"/>
				<fmt:formatNumber var="mo" type="number" value="${i.price}" maxFractionDigits="0"/>
				<p1>市價:$${na}</p1> &ensp;&ensp;會員價:<p2>$${mo }</p2>
				<hr>
				<p>酒種:${i.type }&ensp;&ensp;產地:${i.place}</p>
				<hr>
				<p>酒精度:${i.percent }%&ensp;&ensp;容量:${i.ml }ml&ensp;&ensp;品種:${i.grape }</p>
				<input type="hidden" id="id" value="${i.id}">
				</c:forEach>
				<hr>
				
				<input type="button" value="-" id="del" />
				<span id="quantity">1</span>
				<input type="button" value="+" id="add" />
				<input type="button" id="addcart" style="background-color:#A11E4A;color:white;" value="加入詢問單" >
				
			    <h6>*以客服人員確認的金額與數量為準</h6>
			    </tr>
			    <td><input type="button" class="back" onclick="history.back()" style="background-color:#A11E4A;color:white;" value="回上一頁" >
			 </table>
			 
			</div>
      </div> 
</div>
<script>

$(function(){


	$("#del").click(function() {
		var q = parseInt($("#quantity").text());
		if(q > 1){
			$("#quantity").text(q - 1);
		}
	});
	$("#add").click(function() {
		var q = parseInt($("#quantity").text());
		$("#quantity").text(q + 1);
	});

	$("#addcart").click(function() {
		var wineID=$("#id").val();
		var Quantity=$("#quantity").text(); 
		$.post("AddCart",{id:wineID,quantity:Quantity,action:"addItems"},function(data){
			$("#BuyCount").text("("+data+")");
	        alert("加入購物車成功");
		});
	});


	
	resize();
	$(window).resize(function () {
		resize();
	});
	
    function resize(){
		var bodyh = $(".content").height();
		windowResize(bodyh);
	}

	function windowResize(bodyh){
		var vh = $(window).height();
		var headerh = $(".header").height();
		var banner = $(".banner").height();
		var banner2 = $(".banner2").height();
		var footerh = $(".footer").height();
		var otherh = vh - headerh - footerh - banner - banner2;
		if (bodyh < otherh) {
			$(".footer").addClass("positionFix");
			$(".bodyRight").add(".bgImg").height(otherh);
		}else{
			$(".footer").removeClass("positionFix");
			$(".bodyRight").add(".bgImg").height(bodyh);
		}
	}
});

</script>
     
     
 <jsp:include page="footer.jsp"/>  
    