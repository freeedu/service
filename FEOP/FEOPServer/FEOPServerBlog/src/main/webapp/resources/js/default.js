if (!jQuery) {
	throw new Error("FEOP Default requires jQuery")
}

// Category Section
function retrieveCategories(categoriesUrl) {
	$.getJSON(categoriesUrl, {
		ajax : 'true'
	}, function(data) {
		var html = '<option value="">Choose...</option>';
		var len = data.length;
		var val = $('#category-value').val();

		for ( var i = 0; i < len; i++) {
			if (data[i].id == val) {
				html += '<option value="' + data[i].id + '"'
						+ 'selected="selected">' + data[i].categoryName
						+ '</option>';
			} else {
				html += '<option value="' + data[i].id + '">'
						+ data[i].categoryName + '</option>';
			}
		}
		$('#category').html(html);
	});
}

function changeCategory() {
	$('#category-value').val($('#category').val());
}


//sery section
function retrieveSeries(seryUrl) {
	
	$.getJSON(seryUrl, {
		ajax : 'true',
		c : $('#category-value').val()
	}, function(data) {
		var html = '<option value="">Choose...</option>';
		var len = data.length;
		var val = $('#sery-value').val();

		for ( var i = 0; i < len; i++) {
			if (data[i].id == val) {
				html += '<option value="' + data[i].id + '"'
						+ 'selected="selected">' + data[i].seriesName
						+ '</option>';
			} else {
				html += '<option value="' + data[i].id + '">'
						+ data[i].seriesName + '</option>';
			}
		}
		$('#sery').html(html);
	});
}

function changeSery() {
	$('#sery-value').val($('#sery').val());
}
//label section

