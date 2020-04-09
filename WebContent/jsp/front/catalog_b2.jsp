<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<jsp:include page="header.jsp">
	<jsp:param value="true" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="./css/front/wine_b2.css"/>
<script src="./js/front/wine_b2.js"></script>

<div class="content">
	<div class="filter">
		<div class="select" id="selType">
			<p>酒種</p>
			<div class="option">
				<ul>
					<label><li><input type="checkbox" class="check" name="wine[]" value="紅酒" />紅酒</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="白酒" />白酒</li></label>
				</ul>
			</div>
		</div>
		<p style="width: 10%; float: left"></p>
		<div class="select" id="selPlace">
			<p>產地</p>
			<div class="option">
				<ul>
					<label><li><input type="checkbox" class="check" name="wine[]" value="France" />法國</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="USA" />美國</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="Chlie" />智利</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="Australia" />澳洲</li></label>
				</ul>
			</div>
		</div>
		<p style="width: 10%; float: left"></p>
		<div class="select" id="seltPrice">
			<p>價格</p>
			<div class="option">
				<ul>
					<label><li><input type="checkbox" class="check" name="wine[]" value="1000down" />1000以下</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="between1000and2000" />1000 ~ 2000</li></label>
					<label><li><input type="checkbox" class="check" name="wine[]" value="2000up" />2000以上</li></label>
				</ul>
			</div>
		</div>
	</div>
	
	
	<div id="winelist" class="showWine">
	<hr>
	<div class="showCheckBoxRS"></div>
		<table>
				<tr>
				<c:forEach var="li" items="${list}" varStatus="vs">
					<td width="25%">
						<a href="wineshop_b2?id=${li.id}">
							<img src="${li.imgPath }" width=150px><br>
							<p>${li.enName}</p>
							<p>${li.chName}</p> 
						</a>
						<hr>
						<!--EL語法,市價取整數-->
						<fmt:formatNumber var="na" type="number" value="${li.price/0.8}" maxFractionDigits="0"/>						
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
		<div class="s">
			<button id="showall" onclick="showall()" style="background-color:#A11E4A;color:white;font-size:16pt;">顯示更多</button>
		</div>
	</div>
</div>
<script>
	$(document).on("click", "[name=del]", function() {
		var qSpan = $(this).parent().find(".quantity");
		var q = parseInt(qSpan.text());
		if(q > 1){
			qSpan.text(q - 1);
		}
	});
	$(document).on("click", "[name=add]", function() {
		var qSpan = $(this).parent().find(".quantity");
		var q = parseInt(qSpan.text());
		qSpan.text(q + 1);
	});

    function showall(){
        var row=document.getElementsByName("divx"); 
        for(var i=0;i<row.length;i++)       
           row[i].style.display='';
        $("#showall").css("display","none");
    }
	$(document).on("click", "[name=addcart]", function() {
		var pid = $(this).parents("td").find("input").val();
		var q = $(this).parents("td").find(".quantity").text();
		$.post("AddCart", {
			"id" : pid,
			"quantity" : q,
			action:"addItems"
		}, function(data) {
			$("#BuyCount").text("("+data+")");
			alert("成功加入購物車");
		});
	});
	$(document).on("click", ".delCheckBoxRS", function() {
		var thisVal = $(this).attr("val");
		$(this).parent().remove();
		$("[name='wine[]']:checkbox:checked").each(function(){
			if(thisVal == $(this).val()){
			    $(this).prop('checked',false);
			}
   		});
		checkboxQuery();
	});
	$(".check").change(function() {
		checkboxQuery();
	});

	function checkboxQuery(){
		var selected=[];
		var t="Hello";
		var checkboxRS = '';
		$("[name='wine[]']:checkbox:checked").each(function(){
			checkboxRS += "<span>"+$(this).parent().text()+"&nbsp;<img src='./imgs/common/x.png' width='10px' val='"+$(this).val()+"' class='delCheckBoxRS'>&emsp;</span>";
			selected.push($(this).val());
   		});
 		var str = JSON.stringify(selected);
 		//alert(str);
 		$.ajax({
				url : "checkboxQuery",
				type : "post",
				dataType : "text",
				data : {"checkboxList" : str },
				cache : false,
				async : false,
				success : function(response){
					$("#winelist").html(response);
			 		$(".showCheckBoxRS").html(checkboxRS);
				},
				error : function(data){
					alert("POST ERROR!");
				}
 	 		})


		var bodyh = $(".content").height();
		var vh = $(window).height();
		var headerh = $(".header").height();
		var banner = $(".banner").height();
		var banner2 = $(".banner2").height();
		var footerh = $(".footer").height();
		var otherh = vh - headerh - footerh - banner - banner2;
		if (bodyh < otherh) {
			$(".footer").addClass("positionFix");
		}else{
			$(".footer").removeClass("positionFix");
		}
 	}
</script>
<jsp:include page="footer.jsp" />