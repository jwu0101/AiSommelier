$(document).ready(function() {

	resize();
	$(window).resize(function () {
		resize();
	});
	
    function resize(){
		var bodyh = $(".content").height();
		windowResize(bodyh);
	}

    // 確定新增
	$("#add").click(function() {
		if(validate($("#enName").val(),"英文名稱") && validate($("#chName").val(),"中文名稱")
		 && validate($("#grape").val(),"品種") && validate($("#type").val(),"酒種")
		  && validate($("#place").val(),"產地") && validate($("#percent").val(),"酒精濃度")
		   && validate($("#ml").val(),"容量") && validate($("#unit").val(),"銷售單位")
			&& validate($("#price").val(),"價格") && validate($("#status").val(),"商品狀態")
			 && validate($("#feature").val(),"特色")){
			$("#form").submit();
		}
    });
	
	// 取消
	$("#cancel").click(function(){
		location.href="Wine.jsp";
	})

    // 驗証欄位是否必填
    function validate(val,name){
        if(val.trim().length > 0){
            return true;
        }else{
            alert(name+"不可為空");
            return false;
        }
    }
});