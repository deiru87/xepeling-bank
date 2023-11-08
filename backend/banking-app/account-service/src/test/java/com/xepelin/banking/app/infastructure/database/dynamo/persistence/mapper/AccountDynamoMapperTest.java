package com.xepelin.banking.app.infastructure.database.dynamo.persistence.mapper;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.AccountDynamoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDynamoMapperTest {

    @Test
    public void toAccountEntityJPAWhenAccountCommandIsNull(){
        AccountDynamoMapper accountDynamoMapper = new AccountDynamoMapper();
        Account account = accountDynamoMapper.toAccountEntityJPA(null);
        Assertions.assertNull(account);
    }

    @Test
    public void toAccountEntityJPATest(){
        AccountDynamoMapper accountDynamoMapper = new AccountDynamoMapper();
        Account account = accountDynamoMapper.toAccountEntityJPA(AccountCommand.builder().build());
        Assertions.assertEquals(LocalDateTime.now().getDayOfMonth(), account.getEventDate().getDayOfMonth());
    }

    @Test
    public void toAccountQueryWhenAccountIsNull(){
        AccountDynamoMapper accountDynamoMapper = new AccountDynamoMapper();
        AccountQuery accountQuery = accountDynamoMapper.toAccountQuery(null);
        Assertions.assertNull(accountQuery);
    }

    @Test
    public void toAccountQueryTest(){
        AccountDynamoMapper accountDynamoMapper = new AccountDynamoMapper();
        AccountQuery accountQuery = accountDynamoMapper.toAccountQuery(new Account(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L));
        Assertions.assertEquals(1L, accountQuery.getAccountId());
    }


}
