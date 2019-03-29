package org.kimbs.webflux.elastic;

import java.time.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.kimbs.webflux.log.HandlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
public class EsHandlerLogProcessor {
    
    @Autowired
    private Flux<HandlerLog> handlerLogFlux;

    @Autowired
    private EsLogSender esLogSender;

    private Disposable disposable;

    @PostConstruct
    public void init() {
        disposable = handlerLogFlux.bufferTimeout(1000, Duration.ofSeconds(10), Schedulers.elastic()) 
                .filter(it -> !CollectionUtils.isEmpty(it))
                .subscribe(esLogSender::send);
    }

    @PreDestroy
    public void destroy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}