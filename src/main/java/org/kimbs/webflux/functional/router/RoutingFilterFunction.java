package org.kimbs.webflux.functional.router;

import java.time.ZonedDateTime;

import org.kimbs.webflux.log.HandlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Component
public class RoutingFilterFunction implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Autowired
    private FluxSink<HandlerLog> handlerLogSink;

    @Override
	public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        HandlerLog handlerLog = HandlerLog.builder()
            .httpMethod(request.method().toString())
            .urlPattern(request.attribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).get().toString())
            .requestedAt(ZonedDateTime.now())
            .build();
        
        handlerLogSink.next(handlerLog);
        
        return next.handle(request);
	}
    
}