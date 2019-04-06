package org.kimbs.webflux;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.MapperForTestCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WebfluxApplicationTests {

    @Autowired
	private WebTestClient webClient;

	@Autowired
	private MapperForTestCode mapper;

	@After
	public void tearDown() throws Exception {
		mapper.deleteAllCustomers();
	}

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
        Customer created = new Customer(17L, "Byungsoo", "Kim", 26);
        
        /* act & assert */
		EntityExchangeResult<Customer> returnResult = this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created))
			.exchange()
			.expectStatus().isCreated()
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
            .expectStatus().isCreated();
            
        this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created2))
			.exchange()
			.expectStatus().isCreated();

        /* act & assert */
		this.webClient
			.get()
			.uri("/api/customer")
			.exchange()
            .expectBodyList(Customer.class)
            .hasSize(2)
            .contains(created1, created2);
	}
	
	/* GET Method TEST + Search Condition */
    @Test
    public void shouldFindTheNameContainsKimStringlCustomers() throws Exception {
        /* arrange */
        Customer created1 = new Customer(121L, "Byungsoo", "Kim", 26);
		Customer created2 = new Customer(122L, "admin", "admin", 57);
		
		Map<String, String> searchCondition = new HashMap<>();
		searchCondition.put("search", "admin");

        this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created1))
			.exchange()
            .expectStatus().isCreated();
            
        this.webClient
			.post()
			.uri("/api/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(created2))
			.exchange()
			.expectStatus().isCreated();

        /* act & assert */
		this.webClient
			.get()
			.uri("/api/customer?search=" + searchCondition.get("search"))
			.exchange()
            .expectBodyList(Customer.class)
            .hasSize(1)
            .contains(created2);
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
            .expectStatus().isCreated();


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
			.expectStatus().isCreated();
			
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
            .expectStatus().isCreated();

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