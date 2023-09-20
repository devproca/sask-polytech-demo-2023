package ca.devpro.saskpolytech;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testFindAll() {
		Student[] students = restTemplate.getForObject("/api/students", Student[].class);
		assertEquals(4, students.length);
	}

	@Test
	public void testFindEveryoneWhoGraduatedIn2009() {
		Student[] students = restTemplate.getForObject("/api/students?graduationYear=2009", Student[].class);
		assertEquals(1, students.length);
		assertEquals("Justin", students[0].getName());
	}

	@Test
	public void testFindAlex() {
		Student student = restTemplate.getForObject("/api/students/2", Student.class);
		assertEquals("Alex", student.getName());
	}

	@Test
	public void testCreateBob() {
		Student bob = new Student()
				.setName("Bob")
				.setGraduationYear(2023);

		Student createdStudent = restTemplate.postForObject("/api/students", bob, Student.class);

		Student foundStudent = restTemplate.getForObject("/api/students/" + createdStudent.getStudentId(), Student.class);
		assertEquals("Bob", foundStudent.getName());

		Student[] students = restTemplate.getForObject("/api/students", Student[].class);
		assertEquals(5, students.length);
	}

	@Test
	public void testUpdateBobToGraduateNextYear() {
		Student bob = new Student()
				.setName("Bob")
				.setGraduationYear(2023);
		bob = restTemplate.postForObject("/api/students", bob, Student.class);
		bob.setGraduationYear(2024);

		restTemplate.put("/api/students/" + bob.getStudentId(), bob);
//		ResponseEntity<Void> response = restTemplate.exchange("/api/students/" + bob.getStudentId(), HttpMethod.PUT, new HttpEntity<>(bob), Void.class);

		Student newBob = restTemplate.getForObject("/api/students/" + bob.getStudentId(), Student.class);
		assertEquals(2024, newBob.getGraduationYear());
	}

	@Test
	public void testDeleteBob() {
		Student bob = new Student()
				.setName("Bob")
				.setGraduationYear(2023);
		bob = restTemplate.postForObject("/api/students", bob, Student.class);

		restTemplate.delete("/api/students/" + bob.getStudentId());

		ResponseEntity<Map> response = restTemplate.getForEntity("/api/students/" + bob.getStudentId(), Map.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}


}

