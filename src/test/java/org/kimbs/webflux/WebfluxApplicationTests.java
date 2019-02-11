package org.kimbs.webflux;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.kimbs.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebfluxApplicationTests {

	@Autowired
	private WebTestClient webClient;

	private Map<Long, Customer> customerMap = null;

	@Before
	public void setup() throws Exception {
		customerMap = new HashMap<>();
		customerMap.put(1L, new Customer(1L, "Amy", "Taylor", 20));
		customerMap.put(2L, new Customer(2L, "Peter", "Johnson", 12));
		customerMap.put(3L, new Customer(3L, "Kim", "ByungSoo", 26));
		customerMap.put(4L, new Customer(4L, "Martin", "Fowler", 56));
	}

	@After
	public void tearDown() throws Exception {

	}

	/* GET Test-case */
	@Test
	public void tc1_getAllCustomers() throws Exception {
		this.webClient
			.get()
			.uri("/api/customer")
			.exchange()
			.expectBodyList(Customer.class)
			.hasSize(4)
			.contains(customerMap.get(1L), customerMap.get(2L), customerMap.get(3L), customerMap.get(4L));
	}

	@Test
	public void tc2_getCustomerById() throws Exception {
		this.webClient
			.get()
			.uri("/api/customer/{id}", 3)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.isEqualTo(customerMap.get(3L));
	}

	@Test 
	public void tc3_postCustomer() throws Exception {
		Customer created = new Customer(5L, "Kent", "Beck", 57);
		this.webClient
			.post()
			.uri("/api/customer/post")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.isEqualTo(created);
	}

	@Test
	public void tc4_putCustomer() throws Exception {
		Customer updated = new Customer(4L, "put", "test", 99);
		this.webClient
			.put()
			.uri("/api/customer/put/{id}", 4)
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(updated))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.isEqualTo(updated);
	}

	@Test 
	public void tc5_deleteCustomer() throws Exception {
		this.webClient
			.delete()
			.uri("/api/customer/delete/{id}", 1)
			.exchange()
			.expectStatus().isAccepted()
			.expectBody()
			.isEmpty();
	}
}

