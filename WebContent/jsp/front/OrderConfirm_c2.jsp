<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" >
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/OrderConfirmCSS_c2.css"  type="text/css" />
	<!-- /header -->
	<div class="content">
		<div class="title">
	     <p><strong>詢問單已送出，將由客服人員與您電話聯繫提供服務</strong></p>
	    </div>
	      <div class="text">
	       	<p>依據本國菸酒管理法第31條規定，酒之販賣，不得以自動販賣機、郵購、電子購物或其他無法辨識購買者年齡等方式為之。 </p>
	       	<P>菸酒逾有效日期或期限者，不得販賣。菸之販賣，依菸害防制法相關規定辦理。故本公司網站僅提供商品資訊，不提供網路 </P>
	       	<P>上回覆或以任何電子郵件、簡訊方式回覆所有相關資訊與問題，所有回覆皆以電話方式聯繫，恕不提供網路線上交易服務、</P>
	       	<P>網站上亦不接受訂單或完成訂單確認，且所提供之服務範圍僅限台灣(包含電話回覆)。</P>
	       	<br>
			<P>本網站並非線上交易型網站，所列商品及其相關資訊均僅供介紹參考之用，本公司收到您的訊息後將有門市人員以電話與您</P>
			<p>聯繫並提供服務。所有交易細節請均以我們服務人員與您確認訂單當時的內容與說明為準，如有造成不便及困擾之處，敬請</p>
			<p>見諒。</p>
		 </div> 
		<div class="buttonGP">
			<input class="button1" type="button" value="查看詢問單" onclick="location.href='PO.jsp'">
			<input class="button2" type="button" value="繼續逛酒窖" onclick="location.href='../../wineshop_b2'">
		</div>
	</div>
	
<jsp:include page="footer.jsp" />