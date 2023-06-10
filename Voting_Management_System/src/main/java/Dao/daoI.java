package Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Users;

public interface daoI {
	
	ArrayList<Users> display()throws SQLException;
	
	Users authenticateUser(String email,String password)throws SQLException;
   
	boolean insertuser(Users u)throws SQLException;
	
   
	
	
	
	
	
	String updatebyid(String fname,String lname,String pass,String email,int id)throws SQLException;
    
 
  
  void deleteuser(int id)throws SQLException;
  
  void loginuser(String email,String password)throws SQLException;
  
  int getagebyid(int id)throws SQLException;
}
