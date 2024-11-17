package com.example.studentrestservice.controller;

import com.example.studentrestservice.model.Student;
import com.example.studentrestservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/name/{name}")
	public List<Student> getStudentsByName(@PathVariable String name) {
		return studentService.findStudentsByName(name);
	}
	
	@GetMapping
	public List<Student> getStudentsByGpaAndGender(
			@RequestParam double minGpa,
			@RequestParam double maxGpa,
			@RequestParam String gender) {
		return studentService.findStudentsByGpaAndGender(minGpa, maxGpa, gender);
	}
	
	@GetMapping("/gpa")
	public double getAverageGpaByGender(@PathVariable String gender) {
		return studentService.calculateAverageGpaByGender(gender);
	}
}
