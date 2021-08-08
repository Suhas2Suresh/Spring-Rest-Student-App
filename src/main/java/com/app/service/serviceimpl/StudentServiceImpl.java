package com.app.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Student;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student)  {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(int id)  {
		studentRepository.deleteById(id);;
	}

	@Override
	public Student updateStudent(Student student)  {
		return studentRepository.save(student);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public List<Student> getAllStudents()  {
		667fa4922a04969abacdd81017f2d49f50d8ac68
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public Boolean isStudentRecordExists(int id) {

		return studentRepository.existsById(id);
	}

	@Override
	public Optional<Student> findStudentById(int id) {
		
		return studentRepository.findById(id);
	}

}
