package org.kimbs.webflux.functional.handler;

import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomerHandler {

    private final CustomerRepository customerRepository;

    public CustomerHandler(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        log.info("getAll()");
        Flux<Customer> customers = this.customerRepository.getAllCustomers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customers, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        log.info("getCustomerById()");

        long customerId = Long.valueOf(request.pathVariable("id"));

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        Mono<Customer> customerMono = customerRepository.getCustomerById(customerId);

        return customerMono.flatMap(customer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customer))).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> postCustomer(ServerRequest request) {
        log.info("postCustomer()");
        Mono<Customer> customer = request.bodyToMono(Customer.class);

        return customerRepository.saveCustomer(customer)
            .flatMap(c -> 
                ServerResponse
                    //.created(uri)
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(c))
            );
    }

    public Mono<ServerResponse> putCustomer(ServerRequest request) {
        log.info("putCustomer()");
        long customerId = Long.valueOf(request.pathVariable("id"));

        Mono<Customer> customer = request.bodyToMono(Customer.class);

        Mono<Customer> responseMono = customerRepository.putCustomer(customerId, customer);

        return responseMono
                .flatMap(c ->
                    ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(c))
                );
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        log.info("deleteCustomerById()");
        long customerId = Long.valueOf(request.pathVariable("id"));

        return ServerResponse
                .accepted()
                .build(customerRepository.deleteCustomer(customerId));
    }
}