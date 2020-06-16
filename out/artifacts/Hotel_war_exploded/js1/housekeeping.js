function addHousekeeping() {
    var housekeepingStatus = $('#select').val();
    var roomId = $('#roomId').val();
    var remark = $('#text-input').val();
    var staff = $('#select1').val();
    var cleanDate = $('#text-input4').val();
    var data = {
        housekeepingStatus: housekeepingStatus,
        remark: remark,
        staff: staff,
        roomId:roomId,
        cleanDate:cleanDate
    };
    $.ajax({
        url: 'hos?action=addHousekeeping1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Housekeeping  has been successfully added!');
                window.location.href='/hotel/ds?action=getDictionaryList';
            } else {
                alert('Problem! Housekeeping  has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateHousekeeping(housekeepingId) {
    var housekeepingStatus = $('#selectU').val();
    var roomId = $('#roomId').val();
    var remark = $('#text-inputU').val();
    var room = $('#text-input2U').val();
    var staff = $('#select1U').val();
    var cleanDate = $('#text-input4U').val();
    var data = {
        housekeepingStatus: housekeepingStatus,
        remark: remark,
        room:room,
        staff: staff,
        cleanDate:cleanDate,
        roomId:roomId,
        housekeepingId:housekeepingId
    };
    $.ajax({
        url: 'hos?action=updateHousekeeping',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Housekeeping  has been successfully update!');
                window.location.href='/hotel/ds?action=getDictionaryList';
            } else {
                alert('Problem! Housekeeping  has not been successfully update!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteHousekeeping(housekeepingId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'hos?action=deleteHousekeeping',
            type: 'POST',
            data: 'housekeepingId=' + housekeepingId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Housekeeping  has been successfully deleted!');
                    window.location.href='/hotel/ds?action=getDictionaryList';
                } else {
                    alert('Problem! Housekeeping  has not been successfully deleted!');
                }
            }
        });
    }
}
$(function () {
    $('#housekeepingSubmitId').click(function () {
        addHousekeeping();
    });
});