package org.kimbs.webflux;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kimbs.webflux.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WebfluxApplicationTests {

    @Autowired
    private WebTestClient webClient;

	/* GET Method TEST */
	@Test
	public void shouldFindNoCustomersIfrepositoryIsEmpty() throws Exception {
		this.webClient
			.get()
			.uri("/api/customer")
			.exchange()
            .expectBodyList(Customer.class)
            .hasSize(0);
    }
	
	/* POST Method TEST */
    @Test 
	public void shouldStoreACustomer() throws Exception {
        /* arrange */
        Customer created = new Customer(10L, "Byungsoo", "Kim", 26);
        
        /* act & assert */
		EntityExchangeResult<Customer> returnResult = this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.returnResult();

		assertThat(returnResult.getResponseBody()).isEqualTo(created);
    }
	
	/* GET Method TEST */
    @Test
    public void shouldFindAllCustomers() throws Exception {
        /* arrange */
        Customer created1 = new Customer(1000L, "Byungsoo", "Kim", 26);
        Customer created2 = new Customer(1001L, "test", "test", 57);

        this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
            .expectStatus().isOk();
            
        this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created2))
			.exchange()
			.expectStatus().isOk();

        /* act & assert */
		this.webClient
			.get()
			.uri("/api/customer")
			.exchange()
            .expectBodyList(Customer.class)
            .hasSize(2)
            .contains(created1, created2);
    }

	/* GET Method TEST */
    @Test
	public void shouldFindCustomerById() throws Exception {
        /* arrange */
        Customer created1 = new Customer(999L, "Byungsoo", "Kim", 26);

		this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
            .expectStatus().isOk();


        /* act & assert */
        this.webClient
            .get()
            .uri("/api/customer/{id}", created1.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Customer.class);
	}
	
	/* PUT Method TEST */
	@Test
	public void shouldPutACustomer() throws Exception {
		/* arrange */
        Customer created1 = new Customer(54321L, "Byungsoo", "Kim", 26);

		this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
			.expectStatus().isOk();
			
		created1.setFirstname("@@@@");
		created1.setLastname("@@@@");
		created1.setAge(54321);

		/* act & assert */
		this.webClient
			.put()
			.uri("/api/customer/{id}", created1.getId())
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
			.expectStatus().isOk()
			.expectBody(Customer.class)
			.isEqualTo(created1);
	}
	
	/* DELETE Method TEST */
    @Test
	public void shouldDeleteACustomer() throws Exception {
        /* arrange */
        Customer created1 = new Customer(1234L, "Byungsoo", "Kim", 26);

		this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
            .expectStatus().isOk();

        /* act */
		this.webClient
			.delete()
			.uri("/api/customer/{id}", 1234)
			.exchange()
			.expectStatus().isAccepted()
			.expectBody()
            .isEmpty();
            
        /* assert */
        this.webClient
			.get()
			.uri("/api/customer/{id}", 1234)
			.exchange()
            .expectBodyList(Customer.class)
            .hasSize(0);
	}
}