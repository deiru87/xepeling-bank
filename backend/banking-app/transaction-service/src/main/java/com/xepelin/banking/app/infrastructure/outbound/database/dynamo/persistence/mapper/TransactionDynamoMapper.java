package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper;

import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.infrastructure.outbound.database.TransactionMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Transaction;
import com.xepelin.banking.app.infrastructure.share.TransactionEvent;
import org.springframework.stereotype.Component;

@Component
public class TransactionDynamoMapper implements TransactionMapper {

    public Transaction toTransaction(TransactionCommand transactionCommand){
        Transaction transaction = null;
        if (transactionCommand != null){
            transaction = new Transaction();
            transaction.setAccountId(transactionCommand.getAccountId());
            transaction.setType(transactionCommand.getType());
            transaction.setAmount(transactionCommand.getAmount());
            transaction.setEventDate(transactionCommand.getEventDate());
        }

        return transaction;
    }

    @Override
    public TransactionCommand toTransactionCommand(Transaction transaction) {
        TransactionCommand transactionCommand = null;
        if (transaction != null){
            transactionCommand = new TransactionCommand(transaction.getId(), transaction.getAccountId(),
                    transaction.getType(), transaction.getAmount(), transaction.getEventDate());
        }
        return transactionCommand;
    }

    @Override
    public TransactionEvent toTransactionEvent(Transaction transaction) {
        TransactionEvent transactionEvent = null;
        if(transaction != null){
            transactionEvent = new TransactionEvent();
            transactionEvent.setTransactionId(transaction.getId());
            transactionEvent.setAccountId(transaction.getAccountId());
            transactionEvent.setAmount(transaction.getAmount());
        }
        return transactionEvent;
    }


}
