package com.xepelin.banking.app.application.mapper;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.model.TypeTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountApplicationMapperTest {

    @Test
    public void toAccountCommandWhenAccountQueryIsNull(){
        AccountCommand accountCommand = AccountApplicationMapper.toAccountCommand(null, null);
        Assertions.assertNull(accountCommand);
    }

    @Test
    public void toAccountCommandNormalFlow(){
        AccountQuery accountQuery = new AccountQuery(Long.valueOf(1), "test", BigDecimal.valueOf(100.0), LocalDateTime.now(), Long.valueOf(0));
        TransactionCommand transactionCommand = new TransactionCommand("1", Long.valueOf(1), TypeTransaction.DEBIT, BigDecimal.valueOf(100.0), LocalDateTime.now());
        AccountCommand accountCommand = AccountApplicationMapper.toAccountCommand(accountQuery, transactionCommand);
        Assertions.assertEquals(BigDecimal.valueOf(0.0), accountCommand.getBalance());
    }

    @Test
    public void toAccountCommandRollBackWhenAccountQueryIsNull(){
        AccountCommand accountCommand = AccountApplicationMapper.toAccountCommandRollBack(null, null);
        Assertions.assertNull(accountCommand);
    }

    @Test
    public void toAccountCommandRollbackNormalFlow(){
        AccountQuery accountQuery = new AccountQuery(Long.valueOf(1), "test", BigDecimal.valueOf(100.0), LocalDateTime.now(), Long.valueOf(0));
        TransactionCommand transactionCommand = new TransactionCommand("1", Long.valueOf(1), TypeTransaction.DEBIT, BigDecimal.valueOf(100.0), LocalDateTime.now());
        AccountCommand accountCommand = AccountApplicationMapper.toAccountCommandRollBack(accountQuery, transactionCommand);
        Assertions.assertEquals(BigDecimal.valueOf(200.0), accountCommand.getBalance());
    }

}
