package com.xepelin.banking.middleware.processor;

import com.xepelin.banking.middleware.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Processor {

    private static final Logger logger = LoggerFactory.getLogger(Processor.class);
    @KafkaListener(topics = "overload_deposit",
            groupId = "xepelin-bank",
            containerFactory = "transactionListener")
    public void process(TransactionEvent event) {
        logger.info("message = " + event);
    }
}
