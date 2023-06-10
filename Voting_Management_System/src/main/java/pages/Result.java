package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Candiadatedaoclass;
import pojo.Candidate;
import pojo.Users;

@WebServlet("/Result")
public class Result extends HttpServlet {

    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw=response.getWriter()){
			HttpSession hs=request.getSession();
			Candiadatedaoclass candidate=(Candiadatedaoclass)hs.getAttribute("Candidate");
			ArrayList<Candidate> list=candidate.GetResult();
			pw.write("<h1 align='center'>Result</h1>");
			pw.write("<table style=\"background-color: lightgrey; margin: auto\" border=1><tr><th>ID</th><th>Name</th><th>Party</th><th>Vote</th><tr>");
			for(Candidate U:list)
			{
				pw.write("<tr><th>"+U.getId()+"</th><th>"+U.getName()+"</th><th>"+U.getParty()+"</th><th>"+U.getVotes()+"</th><tr>");
			}
			pw.write("<tr colspan=2><td><a href='AdminLogout'>Logout</a></td></tr>");
			pw.write("</table>");
			
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
	}

}
