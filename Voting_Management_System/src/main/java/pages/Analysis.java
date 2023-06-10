package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.Candiadatedaoclass;
import pojo.Candidate;


@WebServlet("/Analysis")
public class Analysis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Analysis() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter pw = response.getWriter()) {
			HttpSession hs = request.getSession();
			Candiadatedaoclass candidate = (Candiadatedaoclass) hs.getAttribute("Candidate");
			HashMap<Integer, String> list = candidate.GetAnalysis();
			pw.write("<h1 align='center'>Analysis</h1>");
			pw.write(
					"<table style=\"background-color: lightgrey; margin: auto\" border=1><tr><th>ID</th><th>Party</th><tr>");
			for(Map.Entry<Integer,String> U:list.entrySet())
			{
				pw.write("<tr><th>"+ U.getKey() +"</th><th>" + U.getValue() +"</th><tr>");
			}
			
//			list.forEach((K,U)=>{pw.write("<tr><th>" + U.getId() + "</th><th>" + U.getName() + "</th><th>" + U.getParty() + "</th><th>"
//						+ U.getVotes() + "</th><tr>")});
//			for (Candidate U : list.values()) {
//				pw.write("<tr><th>" + U.getId() + "</th><th>" + U.getName() + "</th><th>" + U.getParty() + "</th><th>"
//						+ U.getVotes() + "</th><tr>");
//			}
			pw.write("<tr colspan=2><td><a href='AdminLogout'>Logout</a></td></tr>");
			pw.write("</table>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//select party,sum(votes) from candidates group by party;

}
