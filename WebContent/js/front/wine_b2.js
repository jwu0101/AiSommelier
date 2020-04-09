
$(document).ready(function(){
	var slideSpeed=50;
	var selects=$(".select");
	var selectsP=$(".select p");
	selects.hover(function(){
		$(this).find(".option").slideDown(slideSpeed);
		$(this).find("p").css("background","#F9E7AA");
	},function(){
		$(this).find(".option").slideUp(slideSpeed);
		$(this).find("p").css("background","transparent");
	});
})