package com.example.studentrestservice.service;

import com.example.studentrestservice.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

	private List<Student> studentData;
	
	public StudentService() {
		StudentFileReader fileReader = new StudentFileReader();
		this.studentData = fileReader.readStudentFromFile("C:/Users/maira/Downloads/student.txt");
	}
	
	public List<Student> findStudentsByName(String name) {
		return studentData.stream()
				.filter(student -> student.getFirstName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}
	
	public List<Student> findStudentsByGpaAndGender(double minGpa, double maxGpa, String gender) {
		return studentData.stream()
				.filter(student -> student.getGpa() >= minGpa && student.getGpa() <= maxGpa && student.getGender().equalsIgnoreCase(gender))
				.sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
				.collect(Collectors.toList());
	}
	
	public double calculateAverageGpaByGender(String gender) {
		return studentData.stream()
				.filter(student -> student.getGender().equalsIgnoreCase(gender))
				.mapToDouble(Student::getGpa)
				.average()
				.orElse(0.0);
	}
}
