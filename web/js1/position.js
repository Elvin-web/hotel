function addPosition() {
    var name = $('#text-input').val();
    var data = {
        name: name
    };
    $.ajax({
        url: 'ps?action=addPosition1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Position has been successfully added.');
                window.location.href='/hotel/ps?action=getPositionList';
            } else {
                alert('Problem! Position has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updatePosition(positionId) {
    var name = $('#text-inputU').val();
    var data = {
        name: name,
        positionId:positionId
    };
    $.ajax({
        url: 'ps?action=updatePosition',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Position has been successfully update.');
                window.location.href='/hotel/ps?action=getPositionList';
            } else {
                alert('Problem! Position has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deletePosition(positionId, positionValue) {
    var isDelete = confirm("Are you sure to delete " + positionValue + " ?");
    if (isDelete) {
        $.ajax({
            url: 'ps?action=deletePosition',
            type: 'POST',
            data: 'positionId=' + positionId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Position has been successfully deleted.');
                    window.location.href='/hotel/ps?action=getPositionList';
                } else {
                    alert('Problem! Position has not been successfully deleted!');
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
    $('#positionSubmitId').click(function () {
        addPosition();
    });
   /* $('#positionSubmitIdU').click(function () {
        updatePosition(positionId);
    });*/

});