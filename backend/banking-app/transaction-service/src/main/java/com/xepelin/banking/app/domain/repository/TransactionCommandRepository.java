package com.xepelin.banking.app.domain.repository;

import com.xepelin.banking.app.domain.model.TransactionCommand;

import java.util.Optional;

public interface TransactionCommandRepository {

    TransactionCommand createTransaction(TransactionCommand transactionCommand) throws Exception;

}
