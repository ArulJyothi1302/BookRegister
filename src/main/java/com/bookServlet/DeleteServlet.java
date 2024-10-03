package com.bookServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletedata")
public class DeleteServlet extends HttpServlet {

	
	private static final String query= "delete FROM BOOKDATA WHERE bookid=?";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
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
				int count = st.executeUpdate();
				if(count ==1) {
					pw.println("<h1>Record is Deleted</h1>");
					res.sendRedirect("booklist");
				}
				else {
					pw.println("<h1>Not Deleted</h1>");
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
