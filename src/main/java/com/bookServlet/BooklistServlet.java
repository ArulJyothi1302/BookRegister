package com.bookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class BooklistServlet extends HttpServlet {
	private static final String query= "SELECT * FROM BOOKDATA";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		//Header 
		
		RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
		rd.include(req, res);
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
			pw.println("<head> <link rel='stylesheet' href='css/bootstrap.css'> </head>");
			
			pw.println("<body class='bg-secondary'>");
			
			
			
			pw.println("<table class ='table table-dark my-4' border=1 align='center'>");
			
			pw.println("<tr>");
			pw.println("<th>Book Id</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Book Edition</th>");
			pw.println("<th>Book Book Price</th>");
			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			
			ResultSet rs= st.executeQuery(query);
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td><a href='editdata?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td><a href='deletedata?id="+rs.getInt(1)+"'>Delete</a></td>");
			
				pw.println("</tr>");
				
			}
			pw.println("</table>");
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
