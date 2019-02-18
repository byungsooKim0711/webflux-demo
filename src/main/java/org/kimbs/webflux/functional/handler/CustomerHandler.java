package org.kimbs.webflux.functional.handler;

import org.kimbs.webflux.model.Customer;
import org.kimbs.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

    @Autowired
    private CustomerService customerService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Customer> customers = this.customerService.getAllCustomers();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customers, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        long customerId = Long.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Customer> customerMono = customerService.getCustomerById(customerId);

        return customerMono.flatMap(customer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customer))).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> postCustomer(ServerRequest request) {
        Mono<Customer> customer = request.bodyToMono(Customer.class);

        return customerService.saveCustomer(customer)
            .flatMap(c -> 
                ServerResponse
                    //.created(uri)
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(c))
            );
    }

    public Mono<ServerResponse> putCustomer(ServerRequest request) {
        long customerId = Long.valueOf(request.pathVariable("id"));

        Mono<Customer> customer = request.bodyToMono(Customer.class);

        Mono<Customer> responseMono = customerService.putCustomer(customerId, customer);

        return responseMono
                .flatMap(c ->
                    ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(c))
                );
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        long customerId = Long.valueOf(request.pathVariable("id"));
        return ServerResponse
                .accepted()
                .build(customerService.deleteCustomer(customerId));
    }
}