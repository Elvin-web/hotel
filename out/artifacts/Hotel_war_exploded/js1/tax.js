function addTax() {
    var name = $('#text-input').val();
    var code = $('#text-input1').val();
    var taxType = $('#select').val();
    var rate = $('#text-input2').val();
    var data = {
        name: name,
        code: code,
        taxType: taxType,
        rate: rate
    };
    $.ajax({
        url: 'ts?action=addTax1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Tax has been successfully added');
                window.location.href='/hotel/ts?action=getTaxList';
            } else {
                alert('Problem! Tax has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateTax(taxId) {
    var name = $('#text-inputU').val();
    var code = $('#text-input1U').val();
    var taxType = $('#selectU').val();
    var rate = $('#text-input2U').val();
    var data = {
        name: name,
        code: code,
        taxType: taxType,
        rate: rate,
        taxId: taxId
    };
    $.ajax({
        url: 'ts?action=updateTax',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Tax has been successfully update');
                window.location.href='/hotel/ts?action=getTaxList';
            } else {
                alert('Problem! Tax has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteTax(taxId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'ts?action=deleteTax',
            type: 'POST',
            data: 'taxId=' + taxId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Tax has been successfully deleted!');
                    window.location.href='/hotel/ts?action=getTaxList';
                } else {
                    alert('Problem! Tax has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    $('#taxSubmitId').click(function () {
        addTax();
    });
   /* $('#taxSubmitIdU').click(function () {
        updateTax(taxId);
    });*/
});