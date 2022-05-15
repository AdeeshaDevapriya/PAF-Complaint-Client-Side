package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/ComplainAPI")
public class ComplainAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Complain complainObj = new Complain();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
 // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try
     {
     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
     String queryString = scanner.hasNext() ?
     scanner.useDelimiter("\\A").next() : "";
     scanner.close();
     String[] params = queryString.split("&");
     for (String param : params)
     { 
    	 String[] p = param.split("=");
    	 map.put(p[0], p[1]);
    	 }
    	 }
    	catch (Exception e)
    	 {
    	 }
    	return map;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 String output = complainObj.insertComplain(request.getParameter("name"),
			 request.getParameter("address"),
			request.getParameter("email"),
			request.getParameter("contact_no"),
			request.getParameter("account_no "),
			request.getParameter("complain"));
			response.getWriter().write(output);
			}
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = complainObj.updateComplain(paras.get("hidComplainIDSave").toString(),
			 paras.get("name").toString(),
			 paras.get("address").toString(),
			 paras.get("email").toString(),
			 paras.get("contact_no").toString(),
			 paras.get("account_no").toString(),
			 paras.get("complain").toString());
			 response.getWriter().write(output);
			}
			protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			{
			 Map paras = getParasMap(request);
			 String output = complainObj.deleteComplain(paras.get("complain_id").toString());
			response.getWriter().write(output);
			}

}
