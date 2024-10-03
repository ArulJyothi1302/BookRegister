package com.login;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
public class LoginValidation extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uEmail= req.getParameter("uemail");
		String pass= req.getParameter("up");
		
		String db_url = "jdbc:mysql://localhost:3306/bookreg";
		String db_pas ="Arul1302@7598";
		String db_user ="root";
		String query = "select * from login where email=? and pass=?";
		HttpSession ses = req.getSession();
		ses.setAttribute("usermail", uEmail);
		Connection con = null;
		RequestDispatcher rd= null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(db_url,db_user,db_pas);
			
			PreparedStatement pst =con.prepareStatement(query);
			pst.setString(1, uEmail);
			pst.setString(2, pass);
			
			ResultSet rs =pst.executeQuery();
			if(rs.next()) {
				ses.setAttribute("user", rs.getString("uname"));
				rd=req.getRequestDispatcher("home.jsp");
			}
			else {
				req.setAttribute("status", "failed");
				
				rd=req.getRequestDispatcher("Login.jsp");
			}
			rd.forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
