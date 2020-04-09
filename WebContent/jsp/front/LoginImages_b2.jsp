<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    
<jsp:include page="header.jsp" >
	<jsp:param value="false" name="isServlet" />
</jsp:include>
<link rel="stylesheet" href="../../css/front/wine_b2.css"/>
<style>
 body {
 	background-image: url(../../imgs/common/WebPhoto/signIn.jpg);
	background-size: cover;
    background-attachment: fixed;
  }
  
 table{
	font-family:Arial, Microsoft JhengHei;
	font-weight:bold;
	font-size:14pt;
	color:#F9E7AA;
	width:60%;
	margin:auto;
	margin-top: 20vh;
	line-height:30px;
	letter-spacing:3px;
	text-shadow: 0.1em 0.1em 0.2em black
}

.button{
	text-align:center;
   background-color: rgba(0%,0%,0%,0.6);
   border-style:solid;
   border-width:.1px;
   color:white;
   width:12vw;
   height:6vh;
   font-size:14pt;
   font-weight:bold;
   opacity:.85;
   font-family:"Microsoft JhengHei","Arial";
   cursor:pointer;
 }
</style>

<script>

$(function(){
	$(".button").hover(function(){
		$(this).css({"color":"#F9E7AA"});
	},function(){ 
		$(this).css({"color":"#FFF"});
	});
})
$(function(){
	$(".button").hover(function(){
		$(this).css({"box-shadow":"0px 0px 10px 2px #F9E7AA"});
	},function(){ 
		$(this).css({"box-shadow":"0px 0px 0px 0px #F9E7AA"});
	});
})
</script>

<div class="body content">
	<table width="700" height="100" border=0 align="center" valign="center">
　<tr>
     <td colspan="6" align="center">"奔放的水果香氣伴隨濃濃的香料味，加上厚重的酒體，你很難忽略它的存在。<br>黑莓藍莓等漿果、以及巧克力、咖啡，都是常在席拉(Syrah)中常見的香氣"</td>
     </tr>
   		<tr>
			<td width="60" height="90">
			<td id=ai colspan="2" align="center">
				<input class="button" type="button" value="進入AI侍酒師" onclick="location.href='AiSommelier_c3.jsp'">
			</td>
			<td id=shop colspan="2" align="center">
				<input class="button" type="button" value="直接逛酒窖" onclick="location.href='../../wineshop_b2'">
			</td>
			<td width="60" height="90">
		</tr>
</table>

</div>
<jsp:include page="footer.jsp"/>