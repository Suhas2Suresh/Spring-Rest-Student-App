package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
