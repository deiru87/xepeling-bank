package com.xepelin.banking.app.infrastructure.database.dynamo.persistence.mapper;

import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.AccountDynamoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDynamoMapperTest {

    @Test
    public void toAccountQueryWhenAccountIsNull(){
        AccountDynamoMapper accountDynamoMapper= new AccountDynamoMapper();
        AccountQuery accountQuery = accountDynamoMapper.toAccountQuery(null);
        Assertions.assertEquals(null, accountQuery);
    }

    @Test
    public void toAccountQueryWhenAccountExist(){
        AccountDynamoMapper accountDynamoMapper= new AccountDynamoMapper();
        Account account = new Account(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L);
        AccountQuery accountQuery = accountDynamoMapper.toAccountQuery(account);
        Assertions.assertEquals(BigDecimal.TEN, accountQuery.getBalance());
    }

}
