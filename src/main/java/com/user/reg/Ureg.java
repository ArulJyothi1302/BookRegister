package com.user.reg;

import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class Ureg
 */
@WebServlet("/ureg")
public class Ureg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regName= request.getParameter("rname");
		
		String regpwd = request.getParameter("rpwd");
		
		String regemail =request.getParameter("remail");
		
		String db_url = "jdbc:mysql://localhost:3306/bookreg";
		String db_pas ="Arul1302@7598";
		String db_user ="root";
		int regmob= Integer.parseInt(request.getParameter("rmob"));
		
		String query ="insert into login (uname,pass,email,mobile) values(?,?,?,?)";
		Connection con = null;
		
		RequestDispatcher rd= null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(db_url,db_user,db_pas);
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, regName);
			pst.setString(2, regpwd);
			pst.setString(3, regemail);
			pst.setInt(4, regmob);
			
			int count = pst.executeUpdate();
			
			rd=request.getRequestDispatcher("signup.jsp");
			
			if(count >0) {
				request.setAttribute("status", "success");
			}
			else {
				request.setAttribute("status", "failed");
			}
			
			rd.forward(request, response);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/* response.sendRedirect("Login.jsp"); */
	}

}
