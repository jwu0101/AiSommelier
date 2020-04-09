<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>AI侍酒師sommelier_AGEGATE</title>
<script src="../../js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>

<style>
 body {
 	background-image: url(../../imgs/common/WebPhoto/age.jpg);
    background-size: cover;
    background-attachment: fixed;
  }
 
.age_gate{
 margin:auto;
 margin-top: 12vw;
 background-color:#E79550;
 width:450px;
 height:300px;
 text-align:center; 
 font-family:Arial, Microsoft JhengHei;
 font-weight:bold;
 opacity:.85;
 border-radius:20px;
 box-shadow:0px 0px 30px 1px #000000;
 }
 
.age_gate .askAge{
 margin:auto;
 text-align:center; 
 font-family:Arial, Microsoft JhengHei;
 }
.btnY , .btnN {
	cursor:pointer;
	width:100px;
}
 
</style>
</head>
<script>
function ageWarn()
{
    alert("提醒您，中華民國法規規定，未滿十八歲請勿飲酒！");
}
$(function(){
	$(".btnY").hover(function(){
		$(this).attr("src","../../imgs/common/webImg-35.png");
	},function(){ 
		$(this).attr("src","../../imgs/common/webImg-06.png");
	});
	$(".btnN").hover(function(){
		$(this).attr("src","../../imgs/common/webImg-36.png");
	},function(){ 
		$(this).attr("src","../../imgs/common/webImg-07.png");
	});
})
</script>
<body >

<div class="age_gate"  >
	<img src="../../imgs/common/webImg-01.png" style="width:350px;margin-top:2vh;">
	<div class="askAge" style="line-height:30px;">
	<p>您是否已滿十八歲合法飲酒年齡？<br/>
	ARE YOU OF LEGAL DRINLING AGE?</p>
	<div class="btns">
		<img class="button btnY" src="../../imgs/common/webImg-06.png" onclick="location.href='images_b2.jsp'" >&emsp;&emsp;&emsp;
		<img class="button btnN" src="../../imgs/common/webImg-07.png" onclick="ageWarn()" ></div>	                 
	</div>
</div>


</body>
</html>