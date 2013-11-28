if (!jQuery) {
    throw new Error("FEOP Default requires jQuery")
}

// Category Section
function retrieveCategories(categoriesUrl) {
    $.getJSON(categoriesUrl, {
        ajax: 'true'
    }, function (data) {
        var html = '<option value="">Choose...</option>';
        var len = data.length;
        var val = $('#category-value').val();

        for (var i = 0; i < len; i++) {
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


// sery section
function retrieveSeries(seryUrl) {

    $.getJSON(seryUrl, {
        ajax: 'true',
        c: $('#category-value').val()
    }, function (data) {
        var html = '<option value="">Choose...</option>';
        var len = data.length;
        var val = $('#sery-value').val();

        for (var i = 0; i < len; i++) {
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

// label section

// blog modal section
function openCommentModal(commentType, identity) {
    if (commentType == 'blog') {
        $('#blog-id-holder').val(identity);
    } else if (commentType == 'section') {
        $('#section-id-holder').val(identity);
    } else if (commentType == 'comment') {
        $('#comment-id-holer').val(identity);
    }
    $('#new-comment').modal("show");
}
