package org.kimbs.webflux.log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class HandlerLogProcessor {

    private final Flux<HandlerLog> handlerLogFlux;

    private Disposable disposable;

    public HandlerLogProcessor(Flux<HandlerLog> handlerLogFlux) {
        this.handlerLogFlux = handlerLogFlux;
    }

    @PostConstruct
    public void init() {
        disposable = handlerLogFlux.subscribe(it -> log.info(it.toString()));
    }

    @PreDestroy
    public void destroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}