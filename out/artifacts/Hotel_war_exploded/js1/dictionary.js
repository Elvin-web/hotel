function addDictionary() {
    var roomType = $('#select1').val();
    var room = $('#text-input').val();
    var floor = $('#select').val();
    var amenities = $('#multiple-select').val();
    /*var amenitiesUnselect =[$('#multiple-select option:not(:selected)').val()];
    var amenitiesUnselect = [$('#multiple-select option').not(':selected').val()];
   $('#multiple-select option:not(:selected)').each(function() {
        var amenitiesUnselect = $('#multiple-select').val();
    });
   var items  = [];
    $('#multiple-select option :not(:selected)').each(function(){ items.push($(this).val()); });
    var amenitiesUnselect = items.join(', ');*/

    if ($("#multiple-select").find('option').not(':selected').length > 0) { var notSelected = $("#multiple-select").find('option').not(':selected');
    var amenitiesUnselect = notSelected.map(function () { return this.value; }).get(); }
    var data = {
        room: room,
        floor: floor,
        amenities: amenities,
        roomType: roomType,
        amenitiesUnselect: amenitiesUnselect
    };
    $.ajax({
        url: 'ds?action=addDictionary1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Room has been successfully added');
                window.location.href = '/hotel/ds?action=getDictionaryList';
            } else {
                alert('Problem! Room has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateDictionary() {
    var roomId = $('#text-input5U').val();
    var roomType = $('#select1U').val();
    var room = $('#text-inputU').val();
    var floor = $('#selectU').val();
    var amenities = $('#multiple-selectU').val();
    if ($("#multiple-selectU").find('option').not(':selected').length > 0) { var notSelected = $("#multiple-selectU").find('option').not(':selected');
        var amenitiesUnselect = notSelected.map(function () { return this.value; }).get(); }
    var data = {
        roomId: roomId,
        room: room,
        floor: floor,
        amenities: amenities,
        roomType: roomType,
        amenitiesUnselect: amenitiesUnselect

    };
    $.ajax({
        url: 'ds?action=updateDictionary',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Room has been successfully updated');
                window.location.href = '/hotel/ds?action=getDictionaryList';
            } else {
                alert('Problem! Room has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

/*function updateDictionary(dictionaryId) {
    var roomType = $('#select1U').val();
    var room = $('#text-inputU').val();
    var roomId = $('#text-input5U').val();
    var floor = $('#selectU').val();
    var amenities = $('#multiple-selectU').val();
    var data = {
        room: room,
        floor: floor,
        amenities: amenities,
        roomType:roomType,
        roomId:roomId,
        dictionaryId:dictionaryId
    };
    $.ajax({
        url: 'ds?action=updateDictionary',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Room has been successfully updated');
                window.location.href='/hotel/ds?action=getDictionaryList';
            } else {
                alert('Problem! Room has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}*/
function deleteDictionary(dictionaryId, name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'ds?action=deleteDictionary',
            type: 'POST',
            data: 'dictionaryId=' + dictionaryId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Room has been successfully deleted!');
                    window.location.href = '/hotel/ds?action=getDictionaryList';
                } else {
                    alert('Problem! Room has not been successfully deleted!');
                }
            }
        });
    }
}

$(function () {

    $('#dictionarySubmitId').click(function () {
        addDictionary();
    });
    $('#dictionarySubmitIdU').click(function () {
        updateDictionary();
    });

});