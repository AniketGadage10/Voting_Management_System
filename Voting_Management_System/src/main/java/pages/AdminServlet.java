package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Userdaoclass;
import pojo.Users;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    ArrayList<Users> Voter_list=new ArrayList<Users>();
    private Userdaoclass d;
 
	public void init(ServletConfig config) throws ServletException {
		try {
			d=new Userdaoclass();
		} catch (Exception e) {
			throw new ServletException("connection error",e);
		}
	}

	public void destroy() {
		try {
			d.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter pw=response.getWriter()){
			Voter_list=d.display();
			if(Voter_list!=null) {
			pw.write("<h1 align='center'>Voter List</h1>");
			pw.write("<table style=\"background-color: lightgrey; margin: auto\" border=1><tr><th>ID</th><th>First_Name</th><th>Last_Name</th><th>Email</th><th>Password</th><th>Dob</th><th>Status</th><th>Role</th><tr>");
			for(Users U:Voter_list)
			{
				pw.write("<tr><th>"+U.getId()+"</th><th>"+U.getFirst_name()+"</th><th>"+U.getLast_name()+"</th><th>"+U.getEmail()+"</th><th>"+U.getPassword()+"</th><th>"+U.getDob()+"</th><th>"+U.getStatus()+"</th><th>"+U.getRole()+"<tr>");
			}
			pw.write("<tr><td><a href='Result'>Result</a></td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<td><a href='Analysis'>Analysis</a></td></tr>");
			pw.write("</table>");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
