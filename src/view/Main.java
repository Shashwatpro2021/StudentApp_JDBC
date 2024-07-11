package view;

import model.Student;

import java.sql.SQLException;
import java.util.List;

import dao.IStudentDAO;
import dao.StudentDAOImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IStudentDAO dao=new StudentDAOImpl();
		/*
		Student student=new Student();
		student.setSid(1010);
		student.setSname("Sajal");
		student.setSaddress("Chennai");
		*/
		
   try {
    	//System.out.println(dao.createStudent(student));
    	//System.out.println(dao.updateStudent(1010,"Hyderabad"));
    	//System.out.println(dao.getStudentAddressById(1002));
    	/*
    	Student st=dao.searchStudentById(1001);
    	System.out.println(st.getSid());
    	System.out.println(st.getSaddress());
    	System.out.println(st.getSname());
    	
    	*/
    	List<Student> list=dao.getAllStudents();
    	list.forEach(student->{
    		System.out.println(student.getSid());
    		System.out.println(student.getSname());
    		System.out.println(student.getSaddress());
    	});
    	
    }catch(SQLException e) {
    	e.printStackTrace();
    }
		
	}

}
