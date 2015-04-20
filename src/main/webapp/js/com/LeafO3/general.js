/*
user_id = id
 leafo3.app@gmail.com
 */



$(document).ready(function(){
	//http://localhost:8080/rest/account/create?nickname=Albertoruvel&email=albertoruvel@gmail.com&password=password&location=location&interest=Biology
	//10.101.31.72:8080

	toastr.options = {
		"closeButton": true,
		"debug": false,
		"positionClass": "toast-top-right",
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "5000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
});

$(window).load(function () {
	// run code
	//init();
});

function init(){

	var idUser = localStorage.getItem("idUser");
	var test = localStorage.getItem("test");

	if(idUser !=  null){
		$.ajax({
			cache: false,
			async: true,
			type: "GET",
			processData: false,
			dataType: "json",
			contentType: "application/json",
			url: "/rest/account/find_user?user_id=" + escape(idUser),
			success: function (data) {
				if (data != null) {
					if (data.success == true) {
						localStorage.setItem("user", data.data);
						$("#nameUser").append(data.data.nickname);
						$("#btnSignin").append("Sign out");
						$("#btnSignin").parent().removeStyle("float");
					} else {
						//toastr.warning("No fue posible registrar al usuario.", "Precaución");
						localStorage.setItem("user", null);
						localStorage.setItem("idUser", null);
						$("#nameUser").append("");
						$("#btnSignin").append("Sign in");
						console.log("Error - no existe el usuario: " + data);
					}
				} else {

				}
			},
			fail: function (data) {
				console.log("Error - al registrar usuario: " + data);
			}
		});
	}
}