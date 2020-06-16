function addExpenses() {
    var dataInsert = $('#text-input').val();
    var name = $('#text-input1').val();
    var expensesType = $('#select').val();
    var amount = $('#text-input2').val();
    var attachment = $('#file-input').val();
    var data = {
        dataInsert: dataInsert,
        name: name,
        expensesType: expensesType,
        amount: amount,
        attachment: attachment
    };
    $.ajax({
        url: 'es?action=addExpenses1',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response = 'success') {
                alert('Expenses has been successfully added');
                window.location.href='/hotel/es?action=getExpensesList';
            } else {
                alert('Problem! Expenses has not been successfully added!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}

function updateExpenses(expensesId) {
    var dataInsert = $('#text-inputU').val();
    var name = $('#text-input1U').val();
    var expensesType = $('#selectU').val();
    var amount = $('#text-input2U').val();
    var attachment = $('#file-inputU').val();
    var data = {
        dataInsert: dataInsert,
        name: name,
        expensesType: expensesType,
        amount: amount,
        attachment: attachment,
        expensesId: expensesId
    };
    $.ajax({
        url: 'es?action=updateExpenses',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response == 'success') {
                alert('Expenses  has been successfully updated');
                window.location.href='/hotel/es?action=getExpensesList';
            } else {
                alert('Problem! Expenses  has not been successfully updated!');
            }
        },
        error: function () {
            alert('Error');
        }
    });
}
function deleteExpenses(expensesId,name) {
    var isDelete = confirm("Are you sure to delete " + name + " ?");
    if (isDelete) {
        $.ajax({
            url: 'es?action=deleteExpenses',
            type: 'POST',
            data: 'expensesId=' + expensesId,
            dataType: 'text',
            success: function (response) {
                if (response = 'success') {
                    alert('Expenses  has been successfully deleted!');
                    window.location.href='/hotel/es?action=getExpensesList';
                } else {
                    alert('Problem! Expenses  has not been successfully deleted!');
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
    $('#expensesSubmitId').click(function () {
        addExpenses();
    });
    $('#expensesSubmitIdU').click(function () {
        updateExpenses(expensesId);
    });

});