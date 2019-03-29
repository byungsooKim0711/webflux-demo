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
public class TestRouterFunction implements WebFluxConfigurer {
	
    @Bean
    public RouterFunction<ServerResponse> multipleRouterFunctionTest(CustomerHandler customerHandler) {
        return 
            RouterFunctions
                .route(RequestPredicates.GET("/pact").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), customerHandler::getAll);
    }
    
}