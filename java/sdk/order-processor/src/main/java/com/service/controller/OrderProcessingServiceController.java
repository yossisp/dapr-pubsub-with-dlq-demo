package com.service.controller;

import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrderProcessingServiceController {

    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingServiceController.class);
    private static final int MESSAGE_NUM_TO_THROW_ON = 2;

    @PostMapping(path = "/orders", consumes = MediaType.ALL_VALUE)
    public ResponseEntity getCheckout(@RequestBody(required = false) CloudEvent<Order> cloudEvent) {
        try {
            logger.info("Orders subscriber message received: " + cloudEvent.getData().getOrderId());
            if (cloudEvent.getData().getOrderId() == MESSAGE_NUM_TO_THROW_ON) {
                throw new RuntimeException("some error");
            }
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/failedMessages", consumes = MediaType.ALL_VALUE)
    public ResponseEntity failedMessages(@RequestBody(required = false) CloudEvent<Order> cloudEvent) {
        try {
            logger.info("failedMessages subscriber message received: " + cloudEvent.getData().getOrderId());
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@Getter
@Setter
class Order {
    private int orderId;
}