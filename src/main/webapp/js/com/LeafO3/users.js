/*
 "message": "Success",
 "id": "a0cdf468-e690-4ed1-b77a-4d25cedb0d71",
 "success": true,
 errores : error, texto

 leafo3.app@gmail.com
 */



$(document).ready(function(){
	//http://localhost:8080/rest/account/create?nickname=Albertoruvel&email=albertoruvel@gmail.com&password=password&location=location&interest=Biology
	//10.101.31.72:8080
	$("#formAccount").validate({
		submitHandler: function (form) {
			try{
				var username = $("#userName").val();
				var email = $("#email").val();
				var interest = $("#interest").val();
				var country = $("#country").val();
				var pass = $("#password").val();
				toastr.success("No fue posible registrar al usuario.", "Precaución");
				$.ajax({
					cache: false,
					async: true,
					type: "POST",
					processData: false,
					dataType: "json",
					contentType: "application/json",
					url: "/rest/account/create?nickname=" + escape(username) + "&email=" + escape(email) + "&password=" + escape(pass) + "&location=" + escape(country) + "&interest=" + escape(interest) + "",
					success: function (data) {
						if (data != null) {
							if (data.success == true) {
								localStorage.setItem("idUser", data.id);
								var protocolo = window.location.protocol;
								var host = window.location.host;
								window.location.href = protocolo + "//" + host + "/index.html";
							} else {
								toastr.warning("No fue posible registrar al usuario.", "Precaución");
							}
						} else {

						}
					},
					fail: function (data) {
						console.log("Error - al registrar usuario: " + data);
					}
				});
			} catch(ex){
				console.log(ex)
			}
		}
	});


	$("#userName").rules("add", {
		required: true
	});


	$("#email").rules("add", {
		required: true
	});


	$("#interest").rules("add", {
		required: true
	});


	$("#password").rules("add", {
		password: false,
		required: true
	});

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