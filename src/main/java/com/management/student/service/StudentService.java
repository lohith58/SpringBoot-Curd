package com.management.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.student.entity.Student;
import com.management.student.repository.StudentRepository;



@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;
	public void addStud(Student student) {
		
		repo.save(student);
	}
	
	public List<Student> getAllStudents(){
		
		return repo.findAll();
		
	}
	
	
	public Student getStudentById(int id) {
		
		Optional<Student> student= repo.findById(id);
		if(student.isPresent()) {
			return student.get();
		}
		return null;
	}
	
	
	public void deleteStud(int id) {
		repo.deleteById(id);
	}
	
	
	public Page<Student> getStudentByPagination(int currentpage,int size){
		Pageable pageable=PageRequest.of(currentpage, size);
		return repo.findAll(pageable);
	}
}


