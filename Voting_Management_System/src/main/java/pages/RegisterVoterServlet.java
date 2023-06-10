package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Userdaoclass;
import pojo.Users;

@WebServlet("/RegisterVoter")
public class RegisterVoterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Userdaoclass d;
  
	public void init(ServletConfig config) throws ServletException {
		 try {
	        	d=new Userdaoclass();
	        }catch(Exception e){
	        	throw new ServletException(" error in connection",e);
	        }
	}


	public void destroy() {
		try {
			d.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int id=Integer.parseInt(request.getParameter("id"));
		String Fname=request.getParameter("fnm");
		String Lname=request.getParameter("lnm");
		String Email=request.getParameter("enm");
		LocalDate Dob=LocalDate.parse(request.getParameter("dnm"));
		String Pass=request.getParameter("pass");
		String Cpass=request.getParameter("cpass");
		String Role=request.getParameter("role");
		try(PrintWriter pw=response.getWriter()) {
			if(Cpass.equalsIgnoreCase(Pass)) {
			boolean b=d.insertuser(new Users(0,Fname,Lname,Email,Pass,Dob,false,Role));
			if(b)
			{
				pw.write("<h1>DATA INSERT SUCESSFULLY</h1>");
				pw.write("<a href='index.html'>GoToLogin</a>");
			}else
			{
				pw.write("<h1>DATA INSERT UNSUCESSFULLY </h1>");
				pw.write("<a href='index.html'>GoToLogin</a>");
			}
			}else
			{
				pw.write("<h1>Password Doesn,t Match</h1>");
				pw.write("<a href='Register.html'>GoToRegistration</a>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
