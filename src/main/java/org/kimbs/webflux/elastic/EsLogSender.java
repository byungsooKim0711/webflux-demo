package org.kimbs.webflux.elastic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.kimbs.webflux.log.HandlerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EsLogSender {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    
    private final RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200,  "http")));

    @Autowired
    private ObjectMapper mapper;


    public void send(List<HandlerLog> list) {
        try {
            String indexName = "controller-log-" + LocalDate.now().format(FORMATTER);
            BulkRequest request = new BulkRequest();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            for (HandlerLog each : list) {
                request.add(new IndexRequest(indexName, "log").source(mapper.writeValueAsBytes(each), XContentType.JSON));
            }

            client.bulkAsync(request, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
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