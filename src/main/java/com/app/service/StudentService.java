package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Student;

public interface StudentService {

	public Student createStudent(Student student) ;
	
	public void deleteStudent(int id) ;
	
	public Student updateStudent(Student student);
	
	public List<Student> getAllStudents();
	
	public Boolean isStudentRecordExists(int id);
	
	public Optional<Student> findStudentById(int id);
	
}
