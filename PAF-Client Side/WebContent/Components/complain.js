$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

//UPDATE==========================================
//$(document).on("click", ".btnUpdate", function(event)
//{
// $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
// $("#itemCode").val($(this).closest("tr").find('td:eq(0)').text());
// $("#itemName").val($(this).closest("tr").find('td:eq(1)').text());
// $("#itemPrice").val($(this).closest("tr").find('td:eq(2)').text());
// $("#itemDesc").val($(this).closest("tr").find('td:eq(3)').text());
//});

$(document).on("click", ".btnUpdate", function(event)
{
$("#hidComplainIDSave").val($(this).data("complain_id"));
$("#name").val($(this).closest("tr").find('td:eq(0)').text());
$("#address").val($(this).closest("tr").find('td:eq(1)').text());
$("#email").val($(this).closest("tr").find('td:eq(2)').text());
$("#contact_no").val($(this).closest("tr").find('td:eq(3)').text());
$("#account_no").val($(this).closest("tr").find('td:eq(4)').text());
$("#complain").val($(this).closest("tr").find('td:eq(5)').text());
});
////CLIENT-MODEL================================================================
function validateComplainForm()
{
// NAME--------------------------------
if ($("#name").val().trim() == "")
{
return "Insert Complainer's Full Name.";
}
// ADDRESS--------------------------------
if ($("#address").val().trim() == "")
{
return "Insert Complain Address.";
}
9
// PRICE-------------------------------
// if ($("#itemPrice").val().trim() == "")
// {
// return "Insert Item Price.";
// }
// is numerical value
// var tmpPrice = $("#itemPrice").val().trim();
// if (!$.isNumeric(tmpPrice))
// {
// return "Insert a numerical value for Item Price.";
// }
// convert to decimal price
// $("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));

// EMAIL-------------------------------
if ($("#email").val().trim() == "")
{
return "Insert Email.";
}

// CONTACT NUMBER-------------------------------
if ($("#contact_no").val().trim() == "")
{
return "Insert Contact Number.";
}

// USERNAME-------------------------------
if ($("#account_no").val().trim() == "")
{
return "Insert Account Number.";
}

// Password------------------------
if ($("#complain").val().trim() == "")
{
return "Insert Complain.";
}
return true;
}



$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateComplainForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#hidComplainIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "ComplainAPI",
type : type,
data : $("#formComplain").serialize(),
dataType : "text",
complete : function(response, status)
{
onUserSaveComplete(response.responseText, status);
}
});
});



function onComplainSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divComplainsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}



$("#hidComplainIDSave").val("");
$("#formComplain")[0].reset();
}


$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "ComplainAPI",
type : "DELETE",
data : "complain_id=" + $(this).data("itemid"),
dataType : "text",
complete : function(response, status)
{
onUserDeleteComplete(response.responseText, status);
}
});
});


function onUserDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divComplainsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}