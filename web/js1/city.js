function addCity() {
    var country = $('#select').val();
    var name = $('#nameId').val();
    var data = {
        country: country,
        name: name
    };
    $.ajax({
        url: 'cis?action=addCity1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('City has been successfully added');
                window.location.href='/hotel/cis?action=getCityList';
            } else {
                alert('Problem! City has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateCity(cityId) {
    var country = $('#selectU').val();
    var name = $('#nameIdU').val();
    var data = {
        country: country,
        name: name,
        cityId: cityId
    };
    $.ajax({
        url: 'cis?action=updateCity',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('City has been successfully updated');
                window.location.href='/hotel/cis?action=getCityList';
            } else {
                alert('Problem! City has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteCity(cityId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'cis?action=deleteCity',
            type: 'POST',
            data: 'cityId=' + cityId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('City has been successfully deleted');
                    window.location.href='/hotel/cis?action=getCityList';
                } else {
                    alert('Problem! City has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    // $("[data-toggle=tooltip]").tooltip();
    $('#closeId').click(function () {
        $('#successMessage').dialog("close");
    });
    $('#closeIdE').click(function () {
        $('#errorMessage').dialog("close");
    });
    $('#citySubmitId').click(function () {
        addCity();
    });


});