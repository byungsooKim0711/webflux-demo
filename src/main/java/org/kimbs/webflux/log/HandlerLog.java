package org.kimbs.webflux.log;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerLog {

    private String httpMethod;
    private String urlPattern;
    private ZonedDateTime requestedAt;
}