package org.kimbs.webflux.service;

import java.util.Map;

import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public Mono<Customer> getCustomerById(Long id) {
        return Mono.justOrEmpty(this.customerMapper.selectCustomerById(id));
        // return Mono.fromSupplier(() -> this.customerMapper.selectCustomerById(id).get());
    }

    public Flux<Customer> getAllCustomers(Map<String, String> map) {
        return Flux.fromIterable(this.customerMapper.selectCustomers(map));
    }

    public Mono<Customer> saveCustomer(Mono<Customer> customer) {
        return customer.doOnNext(c -> {
            this.customerMapper.inserCustomer(c);
        });
    }

    public Mono<Customer> putCustomer(Long id, Mono<Customer> customer) {
        Mono<Customer> customerMono =  customer.doOnNext(c -> {
 			c.setId(id);
			this.customerMapper.updateCustomer(c);
        });
		
		return customerMono;
    }

    public Mono<Void> deleteCustomer(Long id) {
        this.customerMapper.deleteCustomer(id);
        return Mono.empty();
    }
}