package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Candiadatedaoclass;
import Dao.Userdaoclass;
import pojo.Candidate;
import pojo.Users;

@WebServlet("/candidate_list")
public class candidate_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}
	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			// Cookie c[] = request.getCookies();
//			if (c != null) {
//				for (Cookie a : c) {
//					pw.write("<h1> User List "+a.getValue()+"<h1><br><br><a href='index.html'>GoToLogin</a>");
//				}
//				pw.print("<h5> Validated user email </h5>");
//			}else
//			{
//				pw.print("<h5> session track has failed</h5>");
//			}
			HttpSession hs=request.getSession();
			System.out.println(hs.isNew());
			Users user=(Users)hs.getAttribute("udata");
			Userdaoclass voter=(Userdaoclass)hs.getAttribute("userdao");
			if(user!=null)
			{
				pw.print("<h1 align='center'>Welcome "+user.getFirst_name()+user.getLast_name()+" To Voting System</h1>");
				Candiadatedaoclass candidate=(Candiadatedaoclass)hs.getAttribute("Candidate");
				ArrayList<Candidate>list=candidate.GetCandidatelist();
				if(list!=null)
				{
					pw.print("<form action='Logout'>");
					for(Candidate c:list)
					{
						pw.print("<h2 align='center'><input type='radio' name='cid' value="+c.getId()+">"+c.getName()+" ("+c.getParty()+") </h2>");
					}
					pw.print("<h2 align='center'><input type='submit' value='Cast A Vote'></h2>");
					pw.print("</form>");
				}else
				{
					pw.print("<h1>Session Tracking Failed In Candidate</h1>");
				}
			}
		} 
		catch (Exception e) {
			throw new ServletException("error occur"+e);
		}
	}

}
