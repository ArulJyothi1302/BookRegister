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

@WebServlet("/editurl")
public class EditUpdateServlet extends HttpServlet {
	
	private static final String query= "UPDATE BOOKDATA set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? WHERE bookid=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		RequestDispatcher rd = req.getRequestDispatcher("header.jsp");
		rd.include(req, res);
		
		int id = Integer.parseInt(req.getParameter("id"));
		String bookName =req.getParameter("bookName");
		String bookEdition =req.getParameter("bookEdition");
		Float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
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
				
				st.setString(1,bookName);
				st.setString(2, bookEdition);
				st.setFloat(3, bookPrice);
				st.setInt(4, id);
				int count = st.executeUpdate();
				if(count ==1) {
					pw.println("<h1>Record is Updated and Edited</h1>");
					res.sendRedirect("booklist");
				}
				else {
					pw.println("<h1>Not Updated</h1>");
				}
				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			pw.println("<h1>"+ex.getMessage()+"</h1>");
		}
		pw.println("<a href='home.jsp'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='booklist'>Book List</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
