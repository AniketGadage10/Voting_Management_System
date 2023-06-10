package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import pojo.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public Userdaoclass d;
	public Candiadatedaoclass c;

	public void init(ServletConfig config) throws ServletException {
		try {
			d = new Userdaoclass();
			c = new Candiadatedaoclass();
		} catch (Exception e) {
			throw new ServletException(" error in connection", e);
		}
	}

	public void destroy() {
		try {
			d.close();
			c.cleanup();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			Users user = d.authenticateUser(email, password);
			HttpSession hs = request.getSession();
			System.out.println(hs.isNew());
			hs.setAttribute("udata", user);
			hs.setAttribute("userdao", d);
			hs.setAttribute("Candidate", c);
			if (user == null) {
				pw.write("<h1>No User Found <h1><br><br><a href='index.html'>GoToLogin</a>");

			} else {
				if (user.getRole().equalsIgnoreCase("admin")) {
					response.sendRedirect("Admin");
				} else {
					if (user.getStatus()) {
						response.sendRedirect("Logout");
					} else {
//						Cookie c = new Cookie("data", user.toString());
//						response.addCookie(c);			
						response.sendRedirect("candidate_list");
					}
				}
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post" + getClass(), e);
		}
	}

}
