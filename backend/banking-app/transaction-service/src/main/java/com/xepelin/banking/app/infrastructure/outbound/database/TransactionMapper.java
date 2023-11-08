package com.xepelin.banking.app.infrastructure.outbound.database;

import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Transaction;
import com.xepelin.banking.app.infrastructure.share.TransactionEvent;

public interface TransactionMapper {

    Transaction toTransaction(TransactionCommand transactionCommand);
    TransactionCommand toTransactionCommand(Transaction transaction);

    TransactionEvent toTransactionEvent(Transaction transaction);



}
