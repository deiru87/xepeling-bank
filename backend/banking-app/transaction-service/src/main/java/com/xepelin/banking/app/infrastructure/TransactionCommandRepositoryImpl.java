package com.xepelin.banking.app.infrastructure;

import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.repository.TransactionCommandRepository;
import com.xepelin.banking.app.infrastructure.outbound.database.TransactionMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.TransactionRepository;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Transaction;
import com.xepelin.banking.app.infrastructure.share.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
@RequiredArgsConstructor
public class TransactionCommandRepositoryImpl implements TransactionCommandRepository {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final KafkaTemplate<String, TransactionEvent> transactionEventKafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    @Override
    public TransactionCommand createTransaction(TransactionCommand transactionCommand) {
        Transaction transaction = this.transactionRepository.save(transactionMapper.toTransaction(transactionCommand));
        TransactionEvent transactionEvent = transactionMapper.toTransactionEvent(transaction);
        if(transactionEvent.getAmount().compareTo(BigDecimal.valueOf(10000)) >= 0){
            transactionEventKafkaTemplate.send(topic, transactionEvent);
        }
        return transactionMapper.toTransactionCommand(transaction);
    }

}
