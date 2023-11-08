package com.xepelin.banking.app.application.create;

import com.xepelin.banking.app.application.mapper.AccountApplicationMapper;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.model.TypeTransaction;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.domain.repository.AccountQueryRepository;
import com.xepelin.banking.app.domain.repository.TransactionCommandRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCreateUseCase {

    private final TransactionCommandRepository transactionCommandRepository;
    private final AccountQueryRepository accountQueryRepository;
    private final AccountCommandRepository accountCommandRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionCreateUseCase.class);

    public TransactionCommand createTransaction(TransactionCommand transactionCommand) throws Exception {
        logger.debug("Delegating validation and creation of transactionX");
        AccountQuery query = this.validateTransaction(transactionCommand);
        boolean isDoneAccountUpdate = false;
        try{
            this.accountCommandRepository.updateAccount(AccountApplicationMapper.toAccountCommand(query, transactionCommand), false);
            isDoneAccountUpdate = true;
            return this.transactionCommandRepository.createTransaction(transactionCommand);
        }catch(Exception e){
            logger.error("Error creating transaction {}", e.getMessage());
            if(isDoneAccountUpdate){
                this.accountCommandRepository.updateAccount(AccountApplicationMapper.toAccountCommandRollBack(query, transactionCommand), true);
            }
            throw e;
        }

    }

    private AccountQuery validateTransaction(TransactionCommand transactionCommand) throws Exception {
        AccountQuery accountQuery = this.validateAccount(transactionCommand);
        this.validateAmountTransaction(transactionCommand, accountQuery);
        return accountQuery;
    }

    private AccountQuery validateAccount(TransactionCommand transactionCommand) throws Exception {
        Optional<AccountQuery> optionalAccountQuery = this.accountQueryRepository.findById(transactionCommand.getAccountId());
        if (optionalAccountQuery.isEmpty()){
            logger.error("Does not exist Account with id {}", transactionCommand.getAccountId());
            throw new Exception("Does not exist Account for apply transaction");
        }

        return optionalAccountQuery.get();
    }


    private void validateAmountTransaction(TransactionCommand transactionCommand, AccountQuery accountQuery) throws Exception {
        if(transactionCommand.getType().equals(TypeTransaction.DEBIT)){
            BigDecimal diff = accountQuery.getBalance().subtract(transactionCommand.getAmount());
             if( diff.compareTo(BigDecimal.ZERO) < 0 ){
                 logger.error("There is not enough money in the account with id {}", transactionCommand.getAccountId());
                 throw new Exception("There is not enough money in the account");
             }


        }
    }




}
