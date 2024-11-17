package com.example.studentrestservice.service;

import com.example.studentrestservice.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileReader {
	
	public List<Student> readStudentFromFile(String filePath) {
		List<Student> students = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields.length == 5) {
					try {
						Long id = Long.parseLong(fields[0].trim());
						String firstName = fields[1].trim();
						double gpa = Double.parseDouble(fields[2].trim());
						String email = fields[3].trim();
						String gender = fields[4].trim();
						students.add(new Student(id, firstName, gpa, email, gender));
					} catch (NumberFormatException e){
						System.err.println("Invalid data format: " + line);
					}
				} else {
					System.err.println("Invalid line format: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}
}
