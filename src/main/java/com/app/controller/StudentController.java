package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping(value = "/Student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	public ResponseEntity<Student> addStudent(@RequestBody Student requeststudent) {

		Student student = studentService.createStudent(requeststudent);

		if (student != null) {
			return new ResponseEntity<Student>(student, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Student>(student, HttpStatus.BAD_REQUEST);

		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	public ResponseEntity<Student> upadateStudent(@RequestBody Student requeststudent) {
		Boolean isStudentExists = studentService.isStudentRecordExists(requeststudent.getId());

		if (isStudentExists) {
			Student student = studentService.updateStudent(requeststudent);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
		
		Boolean isStudentExists = studentService.isStudentRecordExists(id);

		if (isStudentExists) {
			studentService.deleteStudent(id);
			return new ResponseEntity("Student record " + id + " Deleted Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity("Student record " + id + " Not Found!", HttpStatus.NOT_FOUND);
		}

		
	}

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
	public List<Student> getStudent() {
		List<Student> studentList = studentService.getAllStudents();
		return studentList;
	}

	@RequestMapping(value = "/getStudentByid/{id}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Optional<Student> student = studentService.findStudentById(id);
		Student studentObj = student.get();
		if (studentObj != null) {
			return new ResponseEntity<Student>(studentObj, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Student>(studentObj, HttpStatus.NOT_FOUND);

		}

	}
}
