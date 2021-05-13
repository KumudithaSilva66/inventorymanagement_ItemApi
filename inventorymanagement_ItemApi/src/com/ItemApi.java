package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;

/**
 * Servlet implementation class ItemApi
 */
@WebServlet("/ItemApi")
public class ItemApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Item appObj = new Item();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		String result = appObj.insertItem(request.getParameter("itemcode"),
				request.getParameter("itemcategory"),
				request.getParameter("itemspecific"),
				request.getParameter("price")); 
		        response.getWriter().write(result);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> param = getParasMap(request);
		
		String result = appObj.updateItem(
				param.get("itemcode").toString(),     
		 		param.get("itemcategory").toString(),        
		 		param.get("itemspecific").toString(),        
		 		param.get("price").toString());
		 		
		
		response.getWriter().write(result);
	}	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> param = getParasMap(request);
		
		String result = appObj.deleteItem(param.get("itemcode").toString());
		
		response.getWriter().write(result);
	}

}
