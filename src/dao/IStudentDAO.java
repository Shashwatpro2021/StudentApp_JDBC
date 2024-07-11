package dao;

import java.sql.SQLException;
import java.util.List;

import model.Student;
public interface IStudentDAO {
 
	boolean createStudent(Student student)throws SQLException;// Insert Query
	boolean updateStudent(int sid,String address)throws SQLException;//Update Query
	boolean deleteStudent(int sid)throws SQLException;//delete Query
	Student searchStudentById(int sid)throws SQLException;//select
	List<Student> getAllStudents()throws SQLException;//select
}
