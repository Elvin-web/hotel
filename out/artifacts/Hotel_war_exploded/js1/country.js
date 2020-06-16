function addCountry() {
    var name = $('#text-input').val();
    var sortName = $('#text-input1').val();
    var data = {
        name: name,
        sortName: sortName
    };
    $.ajax({
        url: 'cos?action=addCountry1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Country has been successfully added');
                window.location.href='/hotel/cos?action=getCountryList';
            } else {
                alert('Problem! Country has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateCountry(countryId) {
    var name = $('#text-inputU').val();
    var sortName = $('#text-input1U').val();
    var data = {
        name: name,
        sortName: sortName,
        countryId:countryId
    };
    $.ajax({
        url: 'cos?action=updateCountry',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Country has been successfully updated');
                window.location.href='/hotel/cos?action=getCountryList';
            } else {
                alert('Problem! Country has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteCountry(countryId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'cos?action=deleteCountry',
            type: 'POST',
            data: 'countryId=' + countryId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Country has been successfully deleted');
                    window.location.href='/hotel/cos?action=getCountryList';
                } else {
                    alert('Problem! Country has not been successfully deleted!');
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
    $('#countrySubmitId').click(function () {
        addCountry();
    });
    $('#countrySubmitIdU').click(function () {
        updateCountry();
    });

});