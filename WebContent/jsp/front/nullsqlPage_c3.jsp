<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.ResultSet"
    import="dao.AiSommelierDAO_b1"
    %>
<%request.setCharacterEncoding("UTF-8"); %>

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
				<div style="position: absolute; top: 3vw; left: 33vw; width: 33%">
					<p>    </p>
					<p>    </p>
					
					<img src="../../imgs/common/WebPhoto/meme-02.jpg" style="width: 100%">
					
					<p>    </p>
					<p>    </p>

					<button class="SommeButtonSet" style="width: 100px; position: absolute; top: 28vw; left: 12vw" onclick="location.href='AiSommelier_c3.jsp'">請再次選擇</button>

					
				</div>
				
			</div>
			
        </div>
        
<jsp:include page="footer.jsp"/>
