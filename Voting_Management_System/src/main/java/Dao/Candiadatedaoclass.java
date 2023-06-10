package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import utils.dbutils;
import pojo.Candidate;

public class Candiadatedaoclass implements Candidatedaoi {

	private Connection cn;
	private PreparedStatement pst1, pst2, pst3,pst4;

	public Candiadatedaoclass() throws SQLException {
		cn = dbutils.getconnection();
		pst1 = cn.prepareStatement(" select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3 = cn.prepareStatement("select * from candidates order by votes desc limit ?");
		pst4=cn.prepareStatement("select party,sum(votes) from candidates group by party");
	}

	@Override
	public ArrayList<Candidate> GetCandidatelist() throws SQLException {
		ArrayList<Candidate> list = new ArrayList<Candidate>();
		try (ResultSet rs = pst1.executeQuery()) {
			while (rs.next()) {
				list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void cleanup() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
	}

	@Override
	public boolean UpdateCandidateCount(int id) throws SQLException {
		pst2.setInt(1, id);
		int n = pst2.executeUpdate();
		if (n == 1) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Candidate> GetResult() throws SQLException {
		ArrayList<Candidate> list = new ArrayList<Candidate>();
		pst3.setInt(1, 3);
		try (ResultSet rs = pst3.executeQuery()) {
			while (rs.next()) {
				list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return list;
		}
	}

	@Override
	public HashMap<Integer, String> GetAnalysis() throws SQLException {
		//ArrayList<Candidate> list = new ArrayList<Candidate>();
		HashMap<Integer, String> list=new HashMap<Integer, String>();
		try (ResultSet rs = pst4.executeQuery()) {
			while (rs.next()) {
				list.put(rs.getInt(2), rs.getString(1));
			}
			return list;
		}
	}
}
