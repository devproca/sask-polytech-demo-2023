package ca.devpro.saskpolytech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
	/*
		GET students
		GET students/2
		POST students

	 */

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable Long studentId) {
		return studentRepository.findById(studentId)
				.orElse(null);
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
}
