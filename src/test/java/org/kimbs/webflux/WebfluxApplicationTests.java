package org.kimbs.webflux;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebfluxApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Before
	public void setup() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	/* GET Test-case */
	@Test
	public void tc1_getAllCustomers() throws Exception {
		ListBodySpec<Customer> list = this.webClient
			.get()
			.uri("/api/customer")
			.exchange()
			.expectBodyList(Customer.class);

		// list.contains(new Customer(1L, "byungsoo", "Kim", 26));
	}

	@Test
	public void tc2_getCustomerById() throws Exception {
		this.webClient
			.get()
			.uri("/api/customer/{id}", 2)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class);
			//.isEqualTo(new Customer(1L, "byungsoo", "Kim", 26));
	}

	@Test 
	public void tc3_postCustomer() throws Exception {
		Customer created = new Customer(null, "Kent", "Beck", 57);
		EntityExchangeResult<Customer> returnResult = this.webClient
			.post()
			.uri("/api/customer/post")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.returnResult();

		assertThat(returnResult.getResponseBody()).isEqualTo(created);
	}

	@Test
	public void tc4_putCustomer() throws Exception {
		Customer updated = new Customer(4L, "@@@@", "@@@@", 99);
		EntityExchangeResult<Customer> returnResult = this.webClient
			.put()
			.uri("/api/customer/put/{id}", 4)
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(updated))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.returnResult();

		assertThat(returnResult.getResponseBody()).isEqualTo(updated);
	}

	@Test 
	public void tc5_deleteCustomer() throws Exception {
		this.webClient
			.delete()
			.uri("/api/customer/delete/{id}", 16)
			.exchange()
			.expectStatus().isAccepted()
			.expectBody()
			.isEmpty();
	}
}

