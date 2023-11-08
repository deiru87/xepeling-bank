package com.xepelin.banking.app.application.mapper;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.model.TypeTransaction;

import java.math.BigDecimal;

public class AccountApplicationMapper {


    public static AccountCommand toAccountCommand(AccountQuery accountQuery, TransactionCommand transactionCommand){
        AccountCommand accountCommand = null;
        if (accountQuery != null){
            BigDecimal amount = transactionCommand.getAmount();
            if (transactionCommand.getType().equals(TypeTransaction.DEBIT)){
                amount = amount.negate();
            }
            accountCommand = new AccountCommand(accountQuery.getAccountId(), accountQuery.getName(),
                    accountQuery.getBalance().add(amount), accountQuery.getEventDate(), accountQuery.getVersion());
        }

        return accountCommand;
    }

    public static AccountCommand toAccountCommandRollBack(AccountQuery accountQuery, TransactionCommand transactionCommand){
        AccountCommand accountCommand = null;
        if (accountQuery != null){
            BigDecimal amount = transactionCommand.getAmount();
            if (!transactionCommand.getType().equals(TypeTransaction.DEBIT)){
                amount = amount.negate();
            }
            accountCommand = new AccountCommand(accountQuery.getAccountId(), accountQuery.getName(),
                    accountQuery.getBalance().add(amount), accountQuery.getEventDate(), accountQuery.getVersion());
        }

        return accountCommand;
    }


}
