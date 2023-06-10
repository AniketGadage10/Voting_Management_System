package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Candiadatedaoclass;
import Dao.Userdaoclass;
import pojo.Users;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Users> list = new ArrayList<Users>();

	public void init() throws ServletException {
	}

	public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter pw =response.getWriter()){
			HttpSession hs=request.getSession();
			Users user=(Users)hs.getAttribute("udata");
			if(user.getStatus())
			{
				pw.print("<h1 align='center'>Hello ,"+user.getFirst_name().toUpperCase()+"  "+user.getLast_name().toUpperCase()+"</h1><br>");
				pw.print("<h1 align='center'> (^_^) <b><u> Already Voted</u></b> (^_^)</h1>");
			}else
			{
				Userdaoclass voter=(Userdaoclass)hs.getAttribute("userdao");
				Candiadatedaoclass candidate=(Candiadatedaoclass)hs.getAttribute("Candidate");
				int cid=Integer.parseInt(request.getParameter("cid"));
				if(candidate.UpdateCandidateCount(cid)){
					if(voter.UpdateVoterStatue(user.getId())){
					pw.print("<h1 align='center'>Hello ,"+user.getFirst_name()+" "+user.getLast_name()+"</h1><br>");
					pw.print("<h1 align='center'>(^_^) YOU HAVE CAST VOTE SUCESSFULL (^_^)</h1>");
					}else
					{
						pw.print("<h1 align='center'>(^_^) Problem In Voter Status Update (^_^)</h1>");
					}
				}else
				{
					pw.print("<h1 align='center'>(^_^) Problem In Candidate Count Update (^_^)</h1>");
					hs.invalidate();
				}
				
			}
		} catch (Exception e){
			
			e.printStackTrace();
		}
		
	}

}
