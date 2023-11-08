package com.xepelin.banking.app.infrastructure.database.dynamo.persistence.mapper;

import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.model.TypeTransaction;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Transaction;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.TransactionDynamoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDynamoMapperTest {

    @Test
    public void toTransactionWhenTransactionCommandIsNull(){
        TransactionDynamoMapper transactionDynamoMapper = new TransactionDynamoMapper();
        Transaction transaction = transactionDynamoMapper.toTransaction(null);
        Assertions.assertNull(transaction);
    }

    @Test
    public void toTransactionNormalFlow(){
        TransactionDynamoMapper transactionDynamoMapper = new TransactionDynamoMapper();
        TransactionCommand transactionCommand = new TransactionCommand("1", 1L, TypeTransaction.DEBIT, BigDecimal.TEN, LocalDateTime.now());
        Transaction transaction = transactionDynamoMapper.toTransaction(transactionCommand);
        Assertions.assertEquals(TypeTransaction.DEBIT, transaction.getType());
    }

    @Test
    public void toTransactionCommandWhenTransactionIsNull(){
        TransactionDynamoMapper transactionDynamoMapper = new TransactionDynamoMapper();
        TransactionCommand transactionCommand = transactionDynamoMapper.toTransactionCommand(null);
        Assertions.assertNull(transactionCommand);
    }

    @Test
    public void toTransactionCommandNormalFlow(){
        TransactionDynamoMapper transactionDynamoMapper = new TransactionDynamoMapper();
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setType(TypeTransaction.DEBIT);
        transaction.setAmount(BigDecimal.TEN);
        transaction.setAccountId(1L);
        transaction.setEventDate(LocalDateTime.now());
        TransactionCommand transactionCommand = transactionDynamoMapper.toTransactionCommand(transaction);
        Assertions.assertEquals(TypeTransaction.DEBIT, transactionCommand.getType());
        Assertions.assertEquals(BigDecimal.TEN, transaction.getAmount());
        Assertions.assertEquals("1", transaction.getId());
    }

}
