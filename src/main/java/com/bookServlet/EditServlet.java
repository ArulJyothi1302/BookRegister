package com.bookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editdata")
public class EditServlet extends HttpServlet {

	private static final String query= "SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA WHERE bookid=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
		rd.include(req, res);
		
		int id = Integer.parseInt(req.getParameter("id"));
		// LOAD THE DB
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Generate the connection
		try(Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookreg","root","Arul1302@7598");
				PreparedStatement st = con.prepareStatement(query);){
				st.setInt(1, id);
				ResultSet rs= st.executeQuery();
				rs.next();
				
				pw.println("<head> <link rel='stylesheet' href='css/bootstrap.css'> </head>");
				
				pw.println("<body class='bg-secondary'>");
				
				pw.println("<form class='m-4 p-4'  action='editurl?id="+id+"' method='post'>");
				pw.println("<table class='table-hover border border-white' align='center'>");
				
				pw.println("<tr>");
				pw.println("<td class='text-white p-4'>Book Name</td>");
				pw.println("<td class='p-4'><input class='rounded-pill p-2' type='text' name='bookName' value='"+rs.getString(1)+"'</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td class='text-white  p-4'> Book Edition</td>");
				pw.println("<td class='p-4'><input class='rounded-pill p-2'  type='text' name='bookEdition' value='"+rs.getString(2)+"'</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td class='text-white  p-4'>Book Price</td>");
				pw.println("<td class='p-4'><input class='rounded-pill p-2'  type='text' name='bookPrice' value='"+rs.getString(3)+"'</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td class='m-2'><input  class='btn btn-primary text-white my-2' type='submit' value='Edit'</td>");
				pw.println("<td class='float-right mx-2'><input class='btn btn-danger text-white my-2' type='reset' value='Cancel'</td>");
				pw.println("</tr>");
				
				pw.println("</table>");
				pw.println("</form>");
				pw.println("<body/>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			pw.println("<h1>"+ex.getMessage()+"</h1>");
		}
		pw.println("<div class='d-flex justify-content-center mt-4'>");
		pw.println("<a class='btn btn-success text-white-50 fs-1' href='home.jsp'>Home</a>");
		pw.println("</div>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
}
