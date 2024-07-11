package dao;

import java.util.ArrayList;
import java.util.List;

import model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements IStudentDAO{
@Override
	public boolean createStudent(Student student)throws SQLException{
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
		if(conn!=null) {
			String insertQuery="Insert into student values(?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(insertQuery);
			psmt.setInt(1,student.getSid());
			psmt.setString(2,student.getSname());
			psmt.setString(3,student.getSaddress());
			int k=psmt.executeUpdate();
			if(k>0) {
				return true;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
		return false;
	
	}
	
	public boolean updateStudent(int sid,String saddress)throws SQLException {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
		if(conn!=null) {
			String updateQuery="Update student set saddress=? where sid=?";
			PreparedStatement psmt=conn.prepareStatement(updateQuery);
		
			psmt.setString(1,saddress);
			psmt.setInt(2, sid);
			int k=psmt.executeUpdate();
			if(k>0) {
				return true ;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
		return false;
		
	}
	
	public boolean deleteStudent(int sid)throws SQLException {
		
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
		if(conn!=null) {
			String deleteQuery="delete from student where sid=?";
			PreparedStatement psmt=conn.prepareStatement(deleteQuery);
		    psmt.setInt(1, sid);
			int k=psmt.executeUpdate();
			if(k>0) {
				return true ;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
		return false;
		
	}
	
	public Student searchStudentById(int sid)throws SQLException {
		Connection conn=null;
		Student student=new Student();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
		if(conn!=null) {
			String searchQuery="select * from student where sid=? ";
			PreparedStatement psmt=conn.prepareStatement(searchQuery);
			psmt.setInt(1,sid);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				student.setSid(rs.getInt(1));
				student.setSname(rs.getString("Sname"));
				student.setSaddress(rs.getString("saddress"));
				
			}
		
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				conn.close();
			}
		}
		return student;
		
	}
	
	
	public List<Student> getAllStudents(){
		Connection conn=null;
		List<Student> studentlist=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
		if(conn!=null) {
			String searchQuery="select * from student";
			PreparedStatement psmt=conn.prepareStatement(searchQuery);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				Student student=new Student();
			
				student.setSid(rs.getInt(1));
				student.setSname(rs.getString("Sname"));
				student.setSaddress(rs.getString("saddress"));
				studentlist.add(student);
			}
		
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return studentlist;
	}
		public void getStudentAddressById(int sid) {
			Connection conn=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentappdb","root","root");
			if(conn!=null) {
				CallableStatement csmt=conn.prepareCall("{call GetaddrById(?,?)}");
				csmt.setInt(1, sid);
				csmt.registerOutParameter(2, java.sql.Types.VARCHAR);
				csmt.execute();
				System.out.println(csmt.getString(2));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}	

	