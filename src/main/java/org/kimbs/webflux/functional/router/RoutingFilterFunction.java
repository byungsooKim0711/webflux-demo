package org.kimbs.webflux.functional.router;

import java.time.ZonedDateTime;

import org.kimbs.webflux.log.HandlerLog;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class RoutingFilterFunction implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
	public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        HandlerLog handlerLog = HandlerLog.builder()
            .httpMethod(request.method().toString())
            .urlPattern(request.attribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).get().toString())
            .requestedAt(ZonedDateTime.now())
            .build();
        log.info(handlerLog.toString());
        return next.handle(request);
	}
    
}