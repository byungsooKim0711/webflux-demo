package org.kimbs.webflux.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
public class CustomerRepository {

    
    private Map<Long, Customer> customerStores = new HashMap<Long, Customer>();

    @PostConstruct
    public void init() throws Exception {
        this.customerStores.put(1L, new Customer(1L, "Amy", "Taylor", 20));
		this.customerStores.put(2L, new Customer(2L, "Peter", "Johnson", 12));
		this.customerStores.put(3L, new Customer(3L, "Kim", "ByungSoo", 26));
		this.customerStores.put(4L, new Customer(4L, "Martin", "Fowler", 56));
    }

    public Mono<Customer> getCustomerById(Long id) {
        return Mono.justOrEmpty(this.customerStores.get(id));
    }

    public Flux<Customer> getAllCustomers() {
        return Flux.fromIterable(this.customerStores.values());
    }

    public Mono<Customer> saveCustomer(Mono<Customer> customer) {
        return customer.doOnNext(c -> {
            this.customerStores.put(c.getId(), c);

            log.info("POST: " + c.toString());
        });
    }

    public Mono<Customer> putCustomer(Long id, Mono<Customer> customer) {
        Mono<Customer> customerMono =  customer.doOnNext(c -> {
			// reset customer.Id
			c.setId(id);
			
			// do put
			this.customerStores.put(id, c);
			
			// log on console
			log.info("PUT:" + c);
        });
		
		return customerMono;
    }

    public Mono<Void> deleteCustomer(Long id) {
        this.customerStores.remove(id);
        return Mono.empty();
    }

}