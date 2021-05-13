$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
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
	var t = ($("#hideitem").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ItemAPI",
		type : t,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
}); 

function onItemSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hideitem").val("");
	$("#formItem")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	  
	$("#itemcode").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#itemcategory").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#itemspecific").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#price").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnDelete", function(event){
	$.ajax(
	{
		url : "ItemAPI",
		type : "DELETE",
		data : "itemcode=" + $(this).data("itemcode"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeletedComplete(response.responseText, status);
		}
	});
});

function onItemDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateItemForm() {  
	// Itemcode  
	if ($("#itemcode").val().trim() == "")  {   
		return "Insert itemcode.";  
		
	} 
	
	 // Itemcategory  
	if ($("#itemcategory").val().trim() == "")  {   
		return "Insert itemcategory.";  
		
	} 
	 	 
	 // Itemspecific 
	if ($("#itemspecific").val().trim() == "")  {   
		return "Insert itemspecific.";  
		
	} 
		 
	// is numerical value  
	var tempprice = $("#price").val().trim();  
	if (!$.isNumeric(tempprice))  {   
		return "Insert a numerical value for price.";  
		
	} 
	 return true; 
	 
}
