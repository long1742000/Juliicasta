function changeAccountStatus(param) {
	var id = param;
	swal({
		title: "Bạn có chắc?",
		text: "Bạn muốn thay đổi trạng thái của tài khoản này?",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				$.ajax({
					url: "/admin/Juliicasta/Manager/editStatus",
					type: 'get',
					data: {
						id: id
					},
					success: function(value) {
						window.location.reload(".content");
					},
					error: function(xhr) {

					}
				})
			} else {
				swal("Cancel thành công!");
			}
		});
}

function changeContactStatus(param) {
	var id = param;
	$.ajax({
		url: "/admin/Juliicasta/Manager/editContactStatus",
		type: 'get',
		data: {
			id: id
		},
		success: function(value) {
			window.location.reload(".content");
		},
		error: function(xhr) {

		}
	})
}

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