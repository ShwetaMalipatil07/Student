package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;

@RestController
public class HomeCotroller {
	
	@Autowired
	private StudentRepo studentrepo;
	
	
	@GetMapping("/")
	public String index() {
		return "welcome to spring boot curd operation";
		
	}
	
	//creating new record in DB
	@PostMapping("/saveStudent")
	public Student saveData(@RequestBody Student student)
	{
		studentrepo.save(student);
		return student;
	}
	
	//fetch single data from DB
	@GetMapping("/getstudent/{rollNo}")
	public Student getStudentData(@PathVariable int rollNo)
	{
		Optional<Student> student= null;
		Student student1 = null;
		try {
			student =  studentrepo.findById(rollNo); 
			student1=student.get();
			
			System.out.println("watch " + student1);
			
		} 
		catch (Exception e) 
		{
			System.out.println("no such records are there "+e );
			student1 = new Student(-1,"no such roll number "+rollNo, "");
			}
		
		return student1;
				
	}
	
	//fetch all the data from DB
	@GetMapping("/getAllstudent")
	public List<Student> getAll()
	{
		List<Student> studentList=studentrepo.findAll();
		return studentList;
	}
	
	//delete a perticular record from DB
	@DeleteMapping("/deletestudent/{rollNo}")
	public String deleteStudent(@PathVariable int rollNo)
	{
		Student student=studentrepo.findById(rollNo).get();
		if (student!=null) 
		studentrepo.delete(student);		
		return "deleted sucsessfully";
	}
	
	//update a record
	@PutMapping("/updatedata/{id}")
	public Student updateStudentData(@RequestBody Student student)
	{
		studentrepo.save(student);
		return student;
	}

}
