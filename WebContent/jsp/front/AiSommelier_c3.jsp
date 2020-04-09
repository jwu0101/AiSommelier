<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.ResultSet"
    import="dao.AiSommelierDAO_b1"
    import="model.User_b1"
%>

<jsp:include page="header.jsp">
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/Sommelier_c3.css"/>
<script src="../../js/front/Sommelier_c3.js"></script>

		<!-- body -->
        <div class="body">
        	<div class="content">
	        	<p>  </p>
	
				<!-- 傳值給SommelierWine.jsp -->
				<form id="myform" action="SommelierWine_c3.jsp" method="post">
					<!-- 四個div，依序為Ai Sommelier的四段台詞，先是歡迎詞 -->
		        	<div class="welcome" style="display:block"> 
						<img src="../../imgs/common/webImg-08.png" style="width: 100%"> 
						<span class="QuesWord">
							歡迎來到AI侍酒室，我是侍酒師D.B。我將提供最專業的服務！<br>
							您只需回答簡單的問題，讓我為您挑選本次最適合的酒款～
						</span> 
					</div> 
					<!-- 第一個問題 -->
					<div class="question1" style="display:block"> 
						<img src="../../imgs/common/webImg-09.png" style="width: 100%"> 
						<span class="QuesWord">
							請問您本次的預算是？
						</span> <br>
						<div class="QuesRadio">
							<label><input type="radio" name="price" value="1">1000元以下</label>
							<label><input type="radio" name="price" value="1000">1000-2000元</label>
							<label><input type="radio" name="price" value="2000">2000元以上</label>
						</div>
					</div>
					<!-- 第二個問題 -->
					<div class="question2"> 
						<img src="../../imgs/common/webImg-11.png" style="width: 100%"> 
						<span class="QuesWord">
							請問您買酒的目的是？<br>
						</span> 
						<div class="QuesRadio">
							<label><input type="radio" id="Gift/Sad" name="purpose" value="Gift/Sad">長輩上司送禮</label>
							<label><input type="radio" id="Party/Romance" name="purpose" value="Party/Romance">好友群聚狂歡</label>
							<label><input type="radio" id="Party/Romance" name="purpose" value="Party/Romance">浪漫戀情升溫</label>
							<label><input type="radio" id="Gift/Sad" name="purpose" value="Gift/Sad">失意安慰自己</label>
						</div>
					</div> 
					<!-- 第三個問題，主要是用id分流，依據上面的情境選項跳出紅酒或白酒 -->
					<div class="question3"> 
						<img src="../../imgs/common/webImg-12.png" style="width: 100%"> 
						<span class="QuesWord">
							請問您的酒將搭配餐點飲用嗎？<br>
						</span> 
						<div class="QuesRadio">
							<label><input type="radio" onClick="submitform()" id="dish_Red" name="dish" value="牛肉">牛肉</label>
							<label><input type="radio" onClick="submitform()" id="dish_Red" name="dish" value="豬肉">豬肉</label>
							<label><input type="radio" onClick="submitform()" id="dish_Red" name="dish" value="羊肉">羊肉</label>
						</div>
					</div> 
		  			
					<div class="question4"> 
						<img src="../../imgs/common/webImg-12.png" style="width: 100%"> 
						<span class="QuesWord">
							請問您的酒將搭配餐點飲用嗎？<br>
						</span> 
						<div class="QuesRadio">
							<label><input type="radio" onClick="submitform()" id="dish_White" name="dish" value="雞肉">雞肉</label>
							<label><input type="radio" onClick="submitform()" id="dish_White" name="dish" value="海鮮">海鮮</label>
							<label><input type="radio" onClick="submitform()" id="dish_White" name="dish" value="蔬食">蔬食</label>
						</div>
					</div> 
				</form>
	
	        	<p>  </p>
        	</div>
        </div>
		<!-- /body -->

<jsp:include page="footer.jsp"/>