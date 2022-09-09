function changeImage(param) {
	var idImg = param;
	$.ajax({
		url: "/user/product/changeImage",
		type: 'get',
		data: {
			imgItem: idImg
		},
		success: function(data) {
			var row = document.getElementById("mainImg");
			row.innerHTML = data;
		},
		error: function(xhr) {
			// Hmmm
		}
	})
}

function changeMainImage(param) {
	var idImg = param;
	$.ajax({
		url: "/user/product/changeMainImage",
		type: 'get',
		data: {
			imgItem: idImg
		},
		success: function(data) {
			var row = document.getElementById("mainImg");
			row.innerHTML = data;
		},
		error: function(xhr) {
			// Hmmm
		}
	})
}

function deleteItem(param) {
	var id = param;
	$.ajax({
		url: "/user/cart/remove",
		type: 'get',
		data: {
			id: id
		},
		success: function(data) {
			
		},
		error: function(xhr) {
			// Hmmm
		}
	})
}

function updateQuantity(id, quantity) {
	var id = id;
	var quantity = quantity;
	$.ajax({
		url: "/user/cart/updateQuantity",
		type: 'get',
		data: {
			id: id,
			quantity: quantity
		},
		success: function(data) {
			
		},
		error: function(xhr) {
			// Hmmm
		}
	})
}

function sendEmail(param) {
	var id = param;
	$.ajax({
		url: "/user/sendEmailToChangePassword",
		type: 'get',
		data: {
			id: id
		},
		success: function(data) {
			
		},
		error: function(xhr) {
			// Hmmm
		}
	})
}

function sendOTP(){
	var email = document.getElementById("email").value;
	if(email==""){
		alert("Vui lòng nhập email mới vào")
	}
	else{
		$.ajax({
			url: "/user/sendEmailToEditInfor",
			type: 'get',
			data: {
				email: email
			},
			success: function(data) {
				
			},
			error: function(xhr) {
				// Hmmm
			}
		})
	}
}