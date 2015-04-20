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

	initMap();

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

function initMap(){
	var mapProp = {
		center:new google.maps.LatLng(29.395450,-111.7386465),
		zoom: 4,
		mapTypeId:google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

	$.ajax({
		cache: false,
		async: true,
		type: "GET",
		processData: false,
		dataType: "json",
		contentType: "application/json",
		url: "/rest/leafs/leaf_list",
		success: function (data) {
				if (data.success == true) {
					for(var i = 0; i < data.data.length; i++){
						var publish = data.data[i];
						var latLng = publish.location;
						var comaIndex =  latLng.indexOf(",");
						var lat = latLng.substring(0, comaIndex);
						var lng = latLng.substring(comaIndex + 1);
						var infowindow = new google.maps.InfoWindow();
						//var marker = new google.maps.Marker({
						//	map: map,
						//	position: results[0].geometry.location
						//});
						var marker = new google.maps.Marker({
							map: map,
							position:  new google.maps.LatLng(lat,lng)
						});

						google.maps.event.addListener(marker, 'click', function() {
							infowindow.setContent("" +
							"  <div id='directions'>"
							+ "<p>" + publish.title +"</p>"
							+ "<form>"
							+ " <img src='" + publish.leafPicUrl + "' alt='no image' width='200' height='120'>"
							+ " <div class='form-group'>"
							+ " <label class='contol-label'>Damage Percent</label>"
							+ " <label class='contol-label'>" + publish.damagePercent + "%</label>"
							+ " </div>"
							+ "</form>"
							+ "</div>");
							infowindow.open(map,marker);
						});
					}

				} else {

				}
		},
		fail: function (data) {
			console.log("Error - al registrar usuario: " + data);
		}
	});
}