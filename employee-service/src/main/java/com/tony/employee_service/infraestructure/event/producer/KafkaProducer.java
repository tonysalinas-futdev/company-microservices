package com.tony.employee_service.infraestructure.event.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;

    public void send(String topic, GenericRecord record){
        log.info("Sending record to topic: {}, Record: {}", topic,record);
        String uuid= UUID.randomUUID().toString();
        CompletableFuture<SendResult<String,GenericRecord>> futureResult=kafkaTemplate.send(topic,uuid,record);
        futureResult.whenComplete((sendResult, throwable)->{
            if (throwable == null){
                log.info("Record sent to topic: {}, key: {}, record: {}, Offset: {}", topic,uuid,record, sendResult.getRecordMetadata().offset());
            }
            else {
                log.error("Record sent to topic: {}, key: {}, record: {}", topic,uuid,record,throwable);
            }

        });
    }
}


