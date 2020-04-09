<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<jsp:include page="header.jsp">
	<jsp:param value="true" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/back/members_c1.css" />
<script src="${pageContext.request.contextPath}/js/back/members_c1.js"></script>

<script>
	window.onload = function() {
		//頁面標籤變數
		blockTable = document.getElementById("blocks");
		preSpan = document.getElementById("spanPre");
		firstSpan = document.getElementById("spanFirst");
		nextSpan = document.getElementById("spanNext");
		lastSpan = document.getElementById("spanLast");
		pageNumSpan = document.getElementById("spanTotalPage");
		currPageSpan = document.getElementById("spanPageNum");

		numCount = document.getElementById("blocks").rows.length - 1; //取table的行數作為資料總數量（減去標題行1）
		columnsCounts = blockTable.rows[0].cells.length;
		pageCount = 3;//控制每頁顯示條數
		pageNum = parseInt(numCount / pageCount);
		if (0 != numCount % pageCount) {
			pageNum += 1;
		}
		
		firstPage();
	};
</script>
	<div class="content">
		<div id="showbody">
			<div class="querytable">
				<div style="padding-left:6vw;display: inline-block;float:left;">
				<form>
					<table class="qt" align="center" border="0"  style="padding-top: 2vh;height:20vh">
						<tr >
							<td style="white-space: nowrap;">會員帳號(E-mail)：<input type="text" id="qemail" value="" style="width:200px"/></td>
							<td style="white-space: nowrap;">姓名：<input type="text" id="qname" value="" style="width:142px"/></td>
						</tr>
						<tr>
							<td style="white-space: nowrap;">通訊地址：<input type="text" id="qaddress" value="" style="width:255px"/></td>
							<td style="white-space: nowrap;">聯絡電話：<input type="tel" id="qmobile" value="" style="width:110px"/></td>
						</tr>
						<tr>
							<td style="white-space: nowrap;">會員狀態：<select id="qstatus">
									<option value="" selected>請選擇</option>
									<option value="active">active</option>
									<option value="inactive">inactive</option>
							</select>
							</td>
							<td style="white-space: nowrap;">生日：<input id="qbday" type="text" value="" placeholder="2001-03-11" style="width:142px"/></td>
						</tr>
					</table>
					
					<button class="pinkbtn" onclick="query()">查詢</button>
					<button class="pinkbtn" type="reset">清除</button>
				</form>
				 <c:set var="isnull" value="${ requestScope.isNull }"/>
				 <c:if test="${ isnull=='notnull' }" >
						<script>
							$("#qemail").val("${ showform[0] }"); 
							$("#qname").val("${ showform[1] }");
							$("#qaddress").val("${ showform[2] }");
							$("#qmobile").val("${ showform[3] }");
							$("#qstatus").val("${ showform[4] }");
							$("#qbday").val("${ showform[5] }");
						</script>
						
					</c:if>
				
				</div>
				<div class="righttitle">
					<span>會員查詢&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</div>
			</div>
			
			<hr>

			<div id="showrs" class="showresult">
				<table id="blocks" style="width: 80vw" align="center" border="1"
					rules=rows>
					<tr align="center" style="background-color: #393332;color: #FDEBE0;">
						<th>會員帳號(E-mail)</th>
						<th>會員姓名</th>
						<th>基本資料</th>
						<th>類型</th>
						<th>會員狀態</th>
						<th>建立資訊</th>
						<th></th>
					</tr>
					<c:forEach var="members" items="${requestScope.list }" varStatus="vs">
						<c:set var="crTtime" value="${members.createTime}" />
						<c:set var="crtime"
							value="${fn:replace(crTtime, 
                                'T', ' ')}" />
						<c:set var="upTtime" value="${members.updateTime}" />
						<c:set var="uptime"
							value="${fn:replace(upTtime, 
                                'T', ' ')}" />
						<tr align="center">
							<td><a href="javascript: return false;" class="ahref" id="detailbutton"><span width=100%>${members.email}</span></a></td>
							<td><span>${members.name}</span></td>
							<td class="sp" style="text-align: left;padding-left:5vw">
								<span>聯絡電話 : ${members.mobile}</span><br>
								<span>通訊地址 : ${members.address}</span><br> 
								<span>生日 : ${members.bday}</span>
							</td>
							<td><span>${members.type}</span></td>
							<td><span>${members.status}</span></td>
							<td class="sp" style="text-align: left;padding-left:5vw">
								<span>建立人 : ${members.createUser}</span><br>
								<span>建立時間 : ${crtime}</span><br> 
								<span>最後修改人 :  ${members.updateUser}</span><br>
								<span>最後修改時間 : ${uptime}</span>
							</td>
							<td>
								<input type="button" class="pinkbtn" id="upbutton" value="修改" /> <br> 
								<input type="button" class="pinkbtn" id="delbutton" value="刪除" />
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="chgPage" style="margin-top:3px;">
					<button disabled="disabled" onclick="firstPage()" id="spanFirst">|&lt;</button>&nbsp;&nbsp; 
					<button disabled="disabled" onclick="prePage()" id="spanPre">&lt;</button>&nbsp;&nbsp;
					<span align="center" id="spanPageNum"></span>&nbsp;&nbsp; 
					<button disabled="disabled" onclick="nextPage()" id="spanNext">&gt;</button>&nbsp;&nbsp; 
					<button disabled="disabled" onclick="lastPage()" id="spanLast">&gt;|</button>&nbsp;&nbsp;
					<input type="hidden" id="spanTotalPage" value="">
					
				</div>
			</div>
		</div>
	</div>


	<script>
		$(document).on("click", "#detailbutton", function() {
			var uemail = $(this).parents("tr").find("td").eq(0).text();
			$.post("updateShow_c1", {
				"uemail" : uemail,
				"who" : "detail"
			}, function(data) {
				$("#showbody").html(data);
			});

		});
		$(document).on("click", "#upbutton", function() {
			var uemail = $(this).parents("tr").find("td").eq(0).text();
			$.post("updateShow_c1", {
				"uemail" : uemail,
				"who" : "update"
			}, function(data) {
				$("#showbody").html(data);
			});

		});
		$(document).on("click", "#delbutton", function() {
			var demail = $(this).parents("tr").find("td").eq(0).text();
			var msg=$(this).parents("tr").find("td").eq(4).text();
			$.ajax({
				url : "delServlet_c1",
				type : "POST",
				data : {
					"demail" : demail
				},
				cache : false,
				async : false,
				success : function(response) {
					msg="inactive";
				},
				error : function(xhr) {
					alert("Del Error!!");
					
				}
			});
			$(this).parents("tr").find("td").eq(4).text(msg);
			
		});
		function query() {
			var email = $("#qemail").val();
			var name = $("#qname").val();
			var address = $("#qaddress").val();
			var mobile = $("#qmobile").val();
			var status = $("#qstatus").val();
			var bady = $("#qbday").val();
			$.ajax({
				url : "queryShow_c1",
				type : "POST",
				dataType : "text",
				data : {
					"qemail" : $("#qemail").val(),
					"qname" : $("#qname").val(),
					"qaddress" : $("#qaddress").val(),
					"qmobile" : $("#qmobile").val(),
					"qstatus" : $("#qstatus").val(),
					"qbday" : $("#qbday").val()
				}, // 輸入的資料
				cache : false,
				async : false,
				success : function(response) {
					//$("#showrs").html(response);
					//$("#qname").val("你好"); 	
					
				},
				error : function(xhr) {
					alert("查詢內容有誤!!");
				}
			});
			
		}
</script>
		<script>
	var y="${param.x}";
	if (y=1){
		$("#b3").css("background-color","#898989");
	}
</script>

<jsp:include page="footer.jsp"/>

