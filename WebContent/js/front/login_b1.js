
$(document).ready(function() {
	// E-MAIL格式檢查
	$("body").on("change", "#email", function() {
		if ($("#email").val() != 'admin') {
			$Emailchecking = IsEmail($("#email").val());
			if ($Emailchecking == false) {
				alert("請填寫正確的E-MAIL格式");
				$("#email").blur();
			}
		}
	})

	function IsEmail(email) {
		var regex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})*$/;
		if (!regex.test(email)) {
			return false;
		} else {
			return true;
		}
	}
});

function forgetpass() {
	var email = prompt("請輸入會員帳號 ( Email )", "");
	var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	if (email === "") {
		alert("請勿空白");
		return;
	}
	if (email.search(emailRule) == -1) {
		alert("Email格式錯誤");
		return;
	}

	$.post("../../CheckMemberServlet_b1", {
		"email" : email
	}, function(data) {
		if (data === "false") {
			alert("查無此帳號");
			return;
		}
		alert("重新設定密碼驗證連結已寄送至" + email + "，請至信箱收取信件");
	});
	$.post("../../SendEmailServlet_b1?act=forgetpass_b1", {
		"email" : email
	});
}