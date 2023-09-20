package ca.devpro.saskpolytech;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void testHello() {
		String response = restTemplate.getForObject("/api/hello", String.class);

		assertEquals("Hello world!", response);
	}

}
