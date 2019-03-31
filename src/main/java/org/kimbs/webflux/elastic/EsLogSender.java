package org.kimbs.webflux.elastic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.kimbs.webflux.log.HandlerLog;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EsLogSender {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    
    private final RestHighLevelClient restHighLevelClient;

    private final ObjectMapper objectMapper;

    public EsLogSender(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    public void send(List<HandlerLog> list) {
        try {
            String indexName = "controller-log-" + LocalDate.now().format(FORMATTER);
            BulkRequest request = new BulkRequest();

            for (HandlerLog each : list) {
                request.add(new IndexRequest(indexName, "log").source(objectMapper.writeValueAsString(each), XContentType.JSON));
            }

            restHighLevelClient.bulkAsync(request, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
                @Override
                public void onResponse(BulkResponse response) {
                    log.debug("Send to logs to ES.");
                }

                @Override
                public void onFailure(Exception e) {
                    log.warn("Exception in send to logs to ES.", e);
                }
            });
        } catch (Exception e) {
            log.warn("Exception in send to logs to ES.", e);
        }
    }
}