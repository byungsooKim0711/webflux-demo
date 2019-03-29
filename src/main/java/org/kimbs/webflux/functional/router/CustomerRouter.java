package org.kimbs.webflux.functional.router;

import org.kimbs.webflux.functional.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerRouter implements WebFluxConfigurer {

    @Autowired
    private RoutingFilterFunction routingFilterFunction;
	
    @Bean
    public RouterFunction<ServerResponse> customerRouterFunction(CustomerHandler customerHandler) {
        return
            RouterFunctions
                .route(RequestPredicates.GET("/api/customer").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::getAll)
        		.andRoute(RequestPredicates.GET("/api/customer/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomer)
        		.andRoute(RequestPredicates.POST("/api/customer").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::postCustomer)
                .andRoute(RequestPredicates.PUT("/api/customer/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::putCustomer)
                .andRoute(RequestPredicates.DELETE("/api/customer/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::deleteCustomer)
                .filter(routingFilterFunction);
    }    
}