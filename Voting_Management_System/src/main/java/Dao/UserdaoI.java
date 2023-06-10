package Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Users;

public interface UserdaoI {
	
	ArrayList<Users> display()throws SQLException;
	
	Users authenticateUser(String email,String password)throws SQLException;
   
	boolean insertuser(Users u)throws SQLException;

	boolean UpdateVoterStatue(int id)throws SQLException;
    
 
  
	
  void deleteuser(int id)throws SQLException;
  
  void loginuser(String email,String password)throws SQLException;
  
  int getagebyid(int id)throws SQLException;
}
