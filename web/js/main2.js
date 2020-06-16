function getAmenitiesList() {
    $.ajax({
        url: 'cs?action=getAmenitiesList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#amenitiesBtnId').click(function () {
    getAmenitiesList();
});

function getCityList() {
    $.ajax({
        url: 'cs?action=getCityList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#cityBtnId').click(function () {
    getCityList();
});

function getCountryList() {
    $.ajax({
        url: 'cs?action=getCountryList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#countryBtnId').click(function () {
    getCountryList();
});


function getDictionaryList() {
    $.ajax({
        url: 'cs?action=getDictionaryList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}

$('#roomsBtnId').click(function () {
    getDictionaryList();
});

function getExpensesCategoryList() {
    $.ajax({
        url: 'cs?action=getExpensesCategoryList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}

$('#expensesCategoryBtnId').click(function () {
    getExpensesCategoryList();
});

function getExpensesList() {
    $.ajax({
        url: 'cs?action=getExpensesList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#expensesBtnId').click(function () {
    getExpensesList();
});

function getFloorList() {
    $.ajax({
        url: 'cs?action=getFloorList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#floorsBtnId').click(function () {
    getFloorList();
});

function getGuestList() {
    $.ajax({
        url: 'cs?action=getGuestList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}


function getHotelList() {
    $.ajax({
        url: 'cs?action=getHotelList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}

$('#hotelDataBtnId').click(function () {
    getHotelList();
});

function getHousekeepingStatusList() {
    $.ajax({
        url: 'cs?action=getHousekeepingStatusList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#housekeepingSatusBtnId').click(function () {
    getHousekeepingStatusList();
});

function getPositionList() {
    $.ajax({
        url: 'cs?action=getPositionList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#positionBtnId').click(function () {
    getPositionList();
});

function getPriceList() {
    $.ajax({
        url: 'cs?action=getPriceList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#priceManagerBtnId').click(function () {
    getPriceList();
});

function getReservationList() {
    $.ajax({
        url: 'cs?action=getReservationList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}

$('#reservationBtnId').click(function () {
    getReservationList();
});

function getRoomTypeList() {
    $.ajax({
        url: 'cs?action=getRoomTypeList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#roomTypesBtnId').click(function () {
    getRoomTypeList();
});

function getServicesList() {
    $.ajax({
        url: 'cs?action=getServicesList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#paidServicesBtnId').click(function () {
    getServicesList();
});

function getStaffList() {
    $.ajax({
        url: 'cs?action=getStaffList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#employeesBtnId').click(function () {
    getStaffList();
});

function getTaxList() {
    $.ajax({
        url: 'cs?action=getTaxList',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        },
        error: function () {
            alert('Error');
        }
    });
}

$('#taxManagerBtnId').click(function () {
    getTaxList();
});

function getGuestList1() {
    $.ajax({
        url: 'cs?action=getGuestList1',
        type: 'GET',
        dataType: 'html',
        success: function (response) {
            $('#content').html(response);
        },

        error: function () {
            alert('Error');
        }
    });
}

function addGuest() {
    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    var data = {
        'name=': name,
        'surname=': surname,
    };
    $.ajax({
        url: 'cs?action=addGuest',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert('Guest has been successfully added!')
                getGuestList();
            } else {
                alert('Problem! Guest has not been successfully added!')
            }
        }
    })
}


function addHotel() {
    var name = $('#nameId').val();
    var address = $('#addressId').val();
    var star = $('#starId').val();
    var phone = $('#phoneId').val();
    var email = $('#emailId').val();
    var data = {
        'name=': name,
        'address=': address,
        'star=': star,
        'phone=': phone,
        'email=': email,
    };
    $.ajax({
        url: 'cs?action=addHotel',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert('Hotel has been successfully added!')
            } else {
                alert('Problem! Hotel has not been successfully added!')
            }
        }
    })
}


$(function () {
    $('body').layout({applyDemoStyles: true});
    $('.ui-layout-center').css('backgroundColor', 'rgba(185,255,240,0.19)');
    $('.ui-layout-west').css('backgroundColor', '#333');
    $('.ui-layout-north').css('backgroundColor', '#198cc5');
    $('#newGuestDialogId').dialog({
        title: 'New Guest',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addGuest();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });


    $('#newBtnId').click(function () {
        $('#newGuestDialogId').load('views/newGuest.jsp', function () {
            $(this).dialog('open');
        });
    });
});


$(function () {
    $('body').layout({applyDemoStyles: true});
    $('.ui-layout-center').css('backgroundColor', 'aqua');
    $('#newHotelDialogId').dialog({
        title: 'New Hotel',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addHotel();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });
    $('#newBtnId').click(function () {
        $('#newHotelDialogId').load('views/newHotel.jsp', function () {
            $(this).dialog('open');
        });
    });

    $('#guestDataBtnId').click(function () {
        getGuestList();
    });
});

$(function () {
    $('body').layout({applyDemoStyles: true});
    $('.ui-layout-center').css('backgroundColor', 'aqua');
    $('#newExpensesCategoryDialogId').dialog({
        title: 'New Expenses Category',
        height: 400,
        width: 400,
        autoOpen: false,
        modal: true,
        buttons: {
            "Save": function () {
                addExpensesCategory();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });


    $('#newBtnId').click(function () {

    });
});


function addAmenities() {
    var name = $('#text-input').val();
    var description = $('#textarea-input').val();
    var action1=$('#checkbox1').val;
    var image=$('#file-input').val();
    var data = {
        'name=': name,
        'description=': description,
        'action1=': action1
    };
    $.ajax({
        url: 'cs?action=addAmenities',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                $('#successMessage').load('views/successMessage.jsp').dialog('open');
            } else {
                $('#errorMessage').load('views/errorMessage.jsp').dialog('open');
            }
        }
    })
}

$(function () {
    $("[data-toggle=tooltip]").tooltip();
    $('#amenitiesSubmitId').click(function () {
        addAmenities();
    });
    $('#successMessage').dialog({
        title: 'Information Message',
        height: 200,
        width: 550,
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
    $('#errorMessage').dialog({
        title: 'Information Message',
        height: 200,
        width: 550,
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
});


$(function () {
    $("[data-toggle=tooltip]").tooltip();
    $('#amenitiesSubmitId').click(function () {
        addAmenities();
    });
    $('#successMessage').dialog({
        title: 'Information Message',
        height: 200,
        width: 550,
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
    $('#errorMessage').dialog({
        title: 'Information Message',
        height: 200,
        width: 550,
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
});
function addAmenities() {
    var name = $('#text-input').val();
    var description = $('#textarea-input').val();
    var action1=$('#checkbox1').val;
    var image=$('#file-input').val();
    var data = {
        'name=': name,
        'description=': description,
        'action1=': action1
    };
    $.ajax({
        url: 'cs?action=addAmenities',
        type: 'POST',
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                $('#successMessage').load('views/successMessage.jsp').dialog('open');
            } else {
                $('#errorMessage').load('views/errorMessage.jsp').dialog('open');
            }
        }
    })
}