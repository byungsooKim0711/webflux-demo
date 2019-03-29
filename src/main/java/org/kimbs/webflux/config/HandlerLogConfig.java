package org.kimbs.webflux.config;

import org.kimbs.webflux.log.HandlerLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Configuration
public class HandlerLogConfig {

    @Bean
    public EmitterProcessor<HandlerLog> handlerLogEmitterProcessor() {
        return EmitterProcessor.create();
    }

    @Bean
    public FluxSink<HandlerLog> handlerLogSink(EmitterProcessor<HandlerLog> handlerLogEmitterProcessor) {
        return handlerLogEmitterProcessor.sink(FluxSink.OverflowStrategy.DROP);
    }

    @Bean
    public Flux<HandlerLog> handlerLogFlux(EmitterProcessor<HandlerLog> handlerLogEmitterProcessor) {
        return handlerLogEmitterProcessor.publishOn(Schedulers.elastic());
    }
}