$(document).ready(function() { 

/*點選價格，出現下一個問題*/
 
    $("input:radio[name='price']").change(function() { 
 
     $('.question2').show(); 

     resize();
 
    }); 
 


/*點選禮物跟傷心的情境，出現紅酒*/

 
    $("input:radio[id='Gift/Sad']").change(function() { 
     $(".question4").hide(); 
     $(".question3").show(); 
     resize();
    }); 

    
 


/*點選派對跟浪漫的情境，出現白酒*/
 
    $("input:radio[id='Party/Romance']").change(function() { 
     $(".question3").hide(); 
     $(".question4").show(); 
    resize();
    }); 

    
 




//Used to control header, footer and .content

	resize();
	$(window).resize(function () {
		resize();
	});
	
    function resize(){
		var bodyh = $(".content").height();
		windowResize(bodyh);
	}

	function windowResize(bodyh){
		var vh = $(window).height();
		var headerh = $(".header").height();
		var banner = $(".banner").height();
		var footerh = $(".footer").height();
		console.log("vh"+vh);
		console.log("headerh"+headerh);
		console.log("banner"+banner);
		console.log("footerh"+footerh);
		console.log("bodyh"+bodyh);

		var otherh = vh - headerh - footerh - banner;
		console.log("otherh"+otherh);

		if (bodyh < otherh) {
			$(".footer").addClass("positionFix");
		}else{
			$(".footer").removeClass("positionFix");
		}
	}
});

/*點選配餐，連結到下一個頁面*/
function submitform()
{
    document.forms["myform"].submit();
}


/*推薦酒類，購買數量調整鈕*/
function del(){
var num=parseInt($('#quantity').text())-1;
if(num<1){
	$('#quantity').text(1);
	}else{
	$('#quantity').text(num);
    }
}

function add(){
	var num=parseInt($('#quantity').text())+1;
	$('#quantity').text(num);
}

/*Conntect to Nico's Shopping Cart */
function clicktoShop(){
    var q = $("#quantity").text();
    var SessionID = $("#SessionID").val();
    $.post("../../AddCart",{quantity:q,id:SessionID,action:"addItems"},function(){
    	location.href="cart.jsp";
    });
//    window.location="TranSession_b2.jsp?quantity="+q+"&id="+SessionID;

}

/*Conntect to Vannessa's Wine info page */
function clicktoWine(){
    var SessionID = $("#SessionID").val();
    window.location="../../wineshop_b2?id="+SessionID;

}


/*千分位符號*/
$(function(){
    var SessionPrice = $("#SessionPrice").val();
    //alert(SessionPrice);

    if(SessionPrice > 1000){
        
        $("p").text(function(i,v){
            return v.replace(/(?!^)(?=(\d{3})+($|\.))/g,",");
        });
    };
});








/*function clicktoWine(){
    var SessionID = $("#SessionID").text();
    window.location="wineServlet?id="+SessionID;

}*/



/*無論選擇哪種餐點，都會出現確認鈕*/
/*
$(document).ready(function() { 
 
    $("input:radio[id='dish_Red']").change(function() { 
 
     $(".check").show(); 
 
    }); 
 
});

$(document).ready(function() { 
 
    $("input:radio[id='dish_White']").change(function() { 
 
     $(".check").show(); 
 
    }); 
 
});
*/
