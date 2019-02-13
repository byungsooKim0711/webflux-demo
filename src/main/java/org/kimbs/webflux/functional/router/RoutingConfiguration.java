package org.kimbs.webflux.functional.router;

import org.kimbs.webflux.functional.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingConfiguration implements WebFluxConfigurer {
	
    @Bean
    public RouterFunction<ServerResponse> customerRouterFunction(CustomerHandler customerHandler) {
        return
            RouterFunctions
                .route(RequestPredicates.GET("/api/customer").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::getAll)
        		.andRoute(RequestPredicates.GET("/api/customer/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomer)
        		.andRoute(RequestPredicates.POST("/api/customer/post").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::postCustomer)
                .andRoute(RequestPredicates.PUT("/api/customer/put/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::putCustomer)
                .andRoute(RequestPredicates.DELETE("/api/customer/delete/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::deleteCustomer);
    }
    
}