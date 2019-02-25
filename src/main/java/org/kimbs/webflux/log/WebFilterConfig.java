package org.kimbs.webflux.log;

import java.time.ZonedDateTime;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class WebFilterConfig implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest serverRequest = exchange.getRequest();
        String url = serverRequest.getURI().getPath();
        HandlerLog handlerLog = new HandlerLog(serverRequest.getMethodValue(), url, ZonedDateTime.now());
        log.info(handlerLog.toString());
        return chain.filter(exchange);
    }    
}