package com.bookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query= "INSERT INTO BOOKDATA (BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES (?,?,?)";
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			// LOAD THE DB
			
			String bookName= req.getParameter("bookName");
			String bookEdition =req.getParameter("bookEdition");
			float bookPrice =Float.parseFloat(req.getParameter("bookPrice")); 
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Generate the connection
			try(Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookreg","root","Arul1302@7598");
					PreparedStatement ps=con.prepareStatement(query);){
				
				ps.setString(1, bookName);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookPrice);
				int count =ps.executeUpdate();
				
				if(count ==1) {
					pw.println("<h2> Record Updated Successfully</h2>");
					res.sendRedirect("booklist");
				}
				else {
					pw.println("<h2>Record Not Updated</h2>");
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
