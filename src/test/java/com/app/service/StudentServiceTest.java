package com.app.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.entity.Student;
import com.app.repository.StudentRepository;

@SpringBootTest
public class StudentServiceTest {

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository mockedStudentRepository;

	@Test
	@DisplayName("Test to save student data")
	public void createStudentTest() {

		// Setup our mock repository
		Student student = new Student(1, "Tony", 85040.80F);
		doReturn(student).when(mockedStudentRepository).save(student);

		// Execute the service call
		Student returnedStudent = studentService.createStudent(student);

		// Assert the response
		// The message added bellow will be displayed if the test fails
		Assertions.assertSame(returnedStudent, student, "The Returned Student is not same as the mock student");

	}

	@Test
	@DisplayName("Test to delete student")
	public void deleteStudentTest() {

		// Setup our mock repository
		int id = 1;
		doNothing().when(mockedStudentRepository).deleteById(id);

		// Execute the service call
		studentService.deleteStudent(id);

		// Assert the response
		verify(mockedStudentRepository, times(1)).deleteById(id);

	}

	@Test
	@DisplayName("Test to update student data")
	public void updateStudentTest() {

		// Setup our mock repository
		Student student = new Student(1, "Tony", 50300.90F);
		doReturn(student).when(mockedStudentRepository).save(student);

		// Execute the service call
		Student returnedUpdatedStudent = studentService.updateStudent(student);

		// Assert the response
		Assertions.assertSame(returnedUpdatedStudent, student, "The Returned Student is not same as the mock student");

	}

	@Test
	@DisplayName("Test getting all Students")
	void getAllStudentsTest() {
		// Setup our mock repository
		Student student1 = new Student(1, "Tony", 50300.90F);
		Student student2 = new Student(2, "Chandler", 23300.10F);
		doReturn(Arrays.asList(student1, student2)).when(mockedStudentRepository).findAll();

		// Execute the service call
		List<Student> students = studentService.getAllStudents();

		// Assert the response
		Assertions.assertEquals(2, students.size(), "getAllStudents should return 2 students");
	}
	
	@Test
    @DisplayName("Test find student by id Success")
    void findStudentByIdTest() {
        // Setup our mock repository
		Student student = new Student(2, "Chandler", 23300.10F);
		doReturn(Optional.of(student)).when(mockedStudentRepository).findById(student.getId());

        // Execute the service call
        Optional<Student> returnedStudent = studentService.findStudentById(2);

        // Assert the response
        Assertions.assertSame(returnedStudent.get(), student, "The student returned was not the same as the mock");
    }
	
	@Test
    @DisplayName("Test find student by id Not Found")
    void findStudentByIdNotFoundTest() {
        // Setup our mock repository
		doReturn(Optional.empty()).when(mockedStudentRepository).findById(3);

        // Execute the service call
        Optional<Student> returnedStudent = studentService.findStudentById(3);

        // Assert the response
        Assertions.assertFalse(returnedStudent.isPresent(), "Widget should not be found");
    }
	
	@Test
    @DisplayName("Test is student record exists success")
    void isStudentRecordExistsTest() {
        // Setup our mock repository
		doReturn(true).when(mockedStudentRepository).existsById(2);

        // Execute the service call
        Boolean isStudentExists = studentService.isStudentRecordExists(2);

        // Assert the response
        Assertions.assertTrue(isStudentExists, "The student record should exist and return true");
    }
	
	@Test
    @DisplayName("Test find student by id not found")
    void isStudentRecordExistsNotFoundTest() {
        // Setup our mock repository
		doReturn(false).when(mockedStudentRepository).existsById(3);

        // Execute the service call
        Boolean isStudentExists = studentService.isStudentRecordExists(3);

        // Assert the response
        Assertions.assertFalse(isStudentExists, "The student record should not exist and return false");
    }

}
