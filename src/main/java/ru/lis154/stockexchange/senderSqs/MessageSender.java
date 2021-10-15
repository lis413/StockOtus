package ru.lis154.stockexchange.senderSqs;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ru.lis154.stockexchange.entity.NotificationEntity;

import java.time.LocalDate;

@Component
public class MessageSender {
    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    public void send(String message, String status, String type) {
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationEntity notificationEntity = NotificationEntity.builder()
                .message(message)
                .status(status)
                .type(type)
                .created(LocalDate.now().toString())
                .createdBy("StockExchange")
                .modified(LocalDate.now().toString())
                .modifiedBy("Ilya")
                .build();
        String messageSqs = null;
        try {
            messageSqs = objectMapper.writeValueAsString(notificationEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Message payload =  MessageBuilder.withPayload(messageSqs)
                .setHeader("sender", "StockExchange")
                .build();
        queueMessagingTemplate.send(endpoint, payload);
    }





}
