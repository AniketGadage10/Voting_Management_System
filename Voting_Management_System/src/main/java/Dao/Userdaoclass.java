package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import utils.*;
import pojo.Users;

public class Userdaoclass implements UserdaoI {

	ArrayList<Users> list = new ArrayList<Users>();
	public static Connection cn;
	public static Statement s;
	public static PreparedStatement p1;
	public static PreparedStatement p2;
	public static PreparedStatement p3;
	public static PreparedStatement p4;
	public static CallableStatement p5;
	public static CallableStatement p6;

	public Userdaoclass() throws SQLException {

		cn = dbutils.getconnection();

		s = cn.createStatement();

		p1 = cn.prepareStatement("select * from users where email=? and password=?");

		p2 = cn.prepareStatement("update users set status=1 where id=?");

		p3 = cn.prepareStatement("insert into users value(default,?,?,?,?,?,?,?)");

//		p4 = cn.prepareStatement("delete from users where id=?");
//		p5 = cn.prepareCall("{?=call login(?,?)}");
//		p5.registerOutParameter(1, Types.INTEGER);
//		p6 = cn.prepareCall("{call getage(?,?)}");
//		p6.registerOutParameter(2, Types.INTEGER);
	}

	@Override
	public ArrayList<Users> display() throws SQLException {
		ResultSet r = s.executeQuery("select * from users");
		while (r.next()) {
			System.out.println(" ID :" + r.getInt(1) + "First-Name :" + r.getString(2) + "Last-Name :" + r.getString(3)
					+ "Email :" + r.getString(4) + "Password :" + r.getString(5) + "Date :" + r.getDate(6) + " Status "
					+ r.getBoolean(7) + "Role :" + r.getString(8));
			list.add(new Users(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),
					r.getDate(6).toLocalDate(), r.getBoolean(7), r.getString(8)));
		}
		return list;
	}

	@Override
	public Users authenticateUser(String email, String password) throws SQLException {
		p1.setString(1, email);
		p1.setString(2, password);
		ResultSet r = p1.executeQuery();
		while (r.next()) {
			return new Users(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),
					r.getDate(6).toLocalDate(), r.getBoolean(7), r.getString(8));
		}
		return null;
	}

	@Override
	public boolean UpdateVoterStatue(int id) throws SQLException {
		p2.setInt(1, id);
		int i = p2.executeUpdate();
		if (i == 1)
			return true;
		return false;
	}

	@Override
	public boolean insertuser(Users u) throws SQLException {
		// p3.setInt(1, u.getId());
		p3.setString(1, u.getFirst_name());
		p3.setString(2, u.getLast_name());
		p3.setString(3, u.getEmail());
		p3.setString(4, u.getPassword());
		p3.setDate(5, Date.valueOf(u.getDob().toString()));
		p3.setInt(6, u.getStatus() ? 1 : 0);
		p3.setString(7, u.getRole());
		int a = p3.executeUpdate();
		if (a == 1) {
			return true;
		}
		return false;
	}

	public void close() throws SQLException {
		cn.close();
		if (s != null) {
			s.close();
		}
		if (p1 != null) {
			p1.close();
		}
		if (p2 != null) {
			p2.close();
		}
	}

	@Override
	public void deleteuser(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginuser(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getagebyid(int id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}

//	@Override
//	public void deleteuser(int id) throws SQLException {
//		p4.setInt(1, id);
//		int a = p4.executeUpdate();
//		if (a == 1) {
//			System.out.println("Delete Sucess");
//		} else {
//			System.out.println(" Delete Fail");
//		}
//	}
//
//	@Override
//	public void loginuser(String email, String password) throws SQLException {
//		p5.setString(2, email);
//		p5.setString(3, password);
//		boolean b = p5.execute();
//		if (!b) {
//			System.out.println("Login Sucess" + p5.getInt(1));
//		} else {
//			System.out.println("Login Fail");
//		}
//	}
//
//	@Override
//	public int getagebyid(int id) throws SQLException {
//		p6.setInt(1, id);
//		boolean b = p6.execute();
//		if (!b) {
//			System.out.println("Getage Sucess");
//			return p6.getInt(2);
//		}
//		System.out.println("Getage Fail");
//
//		return 0;
//	}
//	
