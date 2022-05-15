$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE 
function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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
 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "UserAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidItemIDSave").val($(this).data("itemid")); 

 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#userAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#phoneNo").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#userType").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#userName").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#password").val($(this).closest("tr").find('td:eq(6)').text()); 
}); 

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "UserAPI", 
 type : "DELETE", 
 data : "userID=" + $(this).data("itemid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
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


// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// Time Period
if ($("#name").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// Total Hours
if ($("#userAddress").val().trim() == "") 
 { 
 return "Insert User Address."; 
 } 
 
// From Time
if ($("#phoneNo").val().trim() == "") 
 { 
 return "Insert Phone Number."; 
 } 
 
// To Time
if ($("#email").val().trim() == "") 
 { 
 return "Insert email ID."; 
 } 
if ($("#userType").val().trim() == "") 
 { 
 return "Insert User Type."; 
 } 
// Sub-Staions
if ($("#userName").val().trim() == "") 
 { 
 return "Insert UserName."; 
 } 
 
// Province
if ($("#password").val().trim() == "") 
 { 
 return "Insert Password."; 
 } 
 
return true; 
}