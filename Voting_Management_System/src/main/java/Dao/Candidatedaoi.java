package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import pojo.*;
public interface Candidatedaoi {

	ArrayList<Candidate> GetCandidatelist() throws SQLException;
	
	boolean UpdateCandidateCount(int id)throws SQLException;
	
	ArrayList<Candidate> GetResult() throws SQLException;
	
	HashMap<Integer, String> GetAnalysis() throws SQLException;
}
