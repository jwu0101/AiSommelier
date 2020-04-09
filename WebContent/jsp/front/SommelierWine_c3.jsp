<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.ResultSet"
    import="dao.AiSommelierDAO_b1"
    %>
<%request.setCharacterEncoding("UTF-8"); %>

<% 
	//抓上一個客戶端選擇的情境、餐點、價格的值
	String Purpose=request.getParameter("purpose");
	String Dish=request.getParameter("dish");
	String Price=request.getParameter("price"); 
	int PRICE=Integer.parseInt(Price);
	//System.out.println(Purpose);
	//System.out.println(Dish);
	//System.out.println(Price);
%>

<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>

<link rel="stylesheet" href="../../css/front/Sommelier_c3.css"/>
<script src="../../js/front/Sommelier_c3.js"></script>

		<!-- body -->
        <div class="body content">
        	<p>   </p>
			
			<!-- Sommerlier推薦酒的開頭詞 -->
        	<div class="welcome" style="display:block"> 
				<img src="../../imgs/common/webImg-08.png" style="width: 100% ; top:10px"> 
				<span class="QuesWord">
					感謝您的回答，本次為您推薦的酒款如下方顯示<br>
					希望您還滿意這項服務並期待您再度光臨～
				</span> 
			</div> 

			<p>   </p>
			
			<!-- Sommerlier推薦酒的簡介 -->
			<div style="position: relative; width: 100%; margin: auto; background: white"> 
				<img src="../../imgs/common/webImg-16.png" style="width: 100%"> 
				<div style="position: absolute; top: 2vw; left: 39vw; width: 20%">
					<div style="top: 1vw; left: 39vw; width: 100%; border-style:solid; border-color:#D4D4D4">
						<p>   </p>
						
						
						<%
							//顯示圖片、英文名、中文名、價格
							ResultSet rs=new AiSommelierDAO_b1().query(Purpose, Dish, PRICE);
							//ResultSet rs=new AiSommelierDAO().query("Gift/Sad", "Beef", 1000);
							//System.out.println(rs.next());
							
							int dataNum = 0;
							while(rs.next()){
								dataNum++;
							
								out.println(
										"<table>"+
											"<tr>"+
												"<td>"+
													"<img class='wineImg' src='../../"+rs.getString("imgPath")+"' onclick='clicktoWine()'>"+
													"<p>"+rs.getString("enName")+
													"<p>"+rs.getString("chName")+
													"<hr>"+
													"<p style='text-decoration:line-through'>市價 : $"+(int)(rs.getInt("price")/0.8)+
													"<p style='color:#A11E4A'>會員價 : $"+rs.getInt("price")+
													"<p>    </p>"+
													"<input type='hidden' id='SessionID' value='"+rs.getString("id")+ "'>"+
													"<input type='hidden' id='SessionPrice' value='"+rs.getInt("price")+ "'>"+
												"</td>"+
											"</tr>"+
										"</table>"	
												
								);
							}
							if(dataNum == 0){
								response.sendRedirect("nullsqlPage_c3.jsp");
							}
							 
						%>
						
						
						
						<!-- 上面的小div是數量選擇鈕，下面的button是傳到購物車 -->						
						<div style="width:60%; margin:0% 25%;text-align:center">
							<div style="float: left;">
								<input type="button" value="-" id="del" onclick="del()"/>
								<span id="quantity">1</span>
								<input type="button" value="+" id="add" onclick="add()"/>
							</div>&emsp;
							
							
							<button class="CartButtonSet" onclick="clicktoShop()">加入詢問單</button>							
						</div>
						<p>    </p>
					</div>
					
					<!-- 讓JS能抓到id的隱藏欄位 -->
					
					<p>    </p>
					
					<!-- 回到AiSommelier頁面 -->
					<div style="width: 25%; margin: auto;">
						<button class="SommeButtonSet" onclick="location.href='AiSommelier_c3.jsp'">再次選擇</button>
					</div>
				</div>
			</div>
        </div>
        
<jsp:include page="footer.jsp"/>
