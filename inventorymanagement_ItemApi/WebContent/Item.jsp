<%@ page import="model.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory management system</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Item management</h1>        
				
				<form id="formitem" name="formitem" method="post" action="Item.jsp">  
					ItemCode :  
					<input id="itemcode" name="itemcode" type="text" class="form-control form-control-sm">  
					
					<br> 
					ItemCategory :  
					<input id="itemcategory" name="itemcategory" type="text" class="form-control form-control-sm">  
					
					<br>
					 ItemSpecific :  
					 <input id=" itemspecific" name="itemspecific" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Price :  
					 <input id="price" name="price" type="text" class="form-control form-control-sm">  
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					 <input type="hidden" id="hideitem" name="hideitem" value="">  
					 
					 
				</form> 
				
				<br>
				<br>
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				
				<br>
				<br>  
				<div id="divItemsGrid">   
					<%    
						Item appObj = new Item();
						out.print(appObj.readItem());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>
</html>