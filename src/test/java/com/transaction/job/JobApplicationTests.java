package com.transaction.job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.transaction.job.model.Employee;
import com.transaction.job.service.EmployeeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private EmployeeService employeeService;  // If you have a service layer

	@Test
	void testPostWithJsonBody() throws JSONException {
		// Given
		String url = "http://localhost:" + "8080" + "/employee/add";

		// Create JSON request body
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "John Doe");
		
		// Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Create http entity with headers and body
		HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

		// When
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.POST,
				request,
				String.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void testPostWithMap() {
		// Given
		String url = "http://localhost:" + "8080" + "/employee/add";

		// Create request body using Map
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("name", "John Doe1");
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

		// When
		ResponseEntity<String> response = restTemplate.postForEntity(
				url,
				request,
				String.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void testPostWithObject() {
		// Given
		String url = "http://localhost:" + "8080" + "/employee/add";

		Employee employee = new Employee();
		employee.setName("John Doe2");
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Employee> request = new HttpEntity<>(employee, headers);

		// When
		ResponseEntity<Employee> response = restTemplate.exchange(
				url,
				HttpMethod.POST,
				request,
				Employee.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		
	}
}
   