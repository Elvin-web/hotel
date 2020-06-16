function addServices() {
    var name = $('#text-input').val();
   // var roomType = $('#multiple-select').val();
    var priceType = $('#select').val();
    var price = $('#text-input1').val();
    var description = $('#textarea-input').val();
    var action1 = $('#checkbox1').val();
    var data = {
        name: name,
     //   roomType: roomType,
        priceType: priceType,
        price: price,
        description: description,
        action1: action1
    };
    $.ajax({
        url: 'ses?action=addServices1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Services has been successfully added');
                window.location.href='/hotel/ses?action=getServicesList';
            } else {
                alert('Problem! Services has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateServices(servicesId) {
    var name = $('#text-inputU').val();
  //  var roomType = $('#multiple-selectU').val();
    var priceType = $('#selectU').val();
    var price = $('#text-input1U').val();
    var description = $('#textarea-inputU').val();
    var action1 = $('#checkbox1U').val();
    var data = {
        name: name,
      //  roomType: roomType,
        priceType: priceType,
        price: price,
        description: description,
        action1: action1,
        servicesId:servicesId
    };
    $.ajax({
        url: 'ses?action=updateServices',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Services has been successfully updated');
                window.location.href='/hotel/ses?action=getServicesList';
            } else {
                alert('Problem! Services has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function deleteServices(servicesId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'ses?action=deleteServices',
            type: 'POST',
            data: 'servicesId=' + servicesId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Services has been successfully deleted');
                    window.location.href='/hotel/ses?action=getServicesList';
                } else {
                    alert('Problem! Services has not been successfully deleted!');
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
    $('#servicesSubmitId').click(function () {
        addServices();
    });
    $('#servicesSubmitIdU').click(function () {
        updateServices();
    });

});