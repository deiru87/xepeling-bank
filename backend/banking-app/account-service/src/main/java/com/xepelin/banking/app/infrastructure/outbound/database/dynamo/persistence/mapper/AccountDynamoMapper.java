package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.infrastructure.outbound.database.AccountMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDynamoMapper implements AccountMapper {

    public Account toAccountEntityJPA(AccountCommand accountCommand){
        Account account = null;
        if (accountCommand != null){
            account = new Account();
            account.setId(accountCommand.getId());
            account.setName(accountCommand.getName());
            account.setBalance(accountCommand.getBalance());
          //  account.setEventDate(accountCommand.getEventDate());
            account.setVersion(accountCommand.getVersion());

        }

        return account;
    }

    @Override
    public AccountQuery toAccountQuery(Account account) {
        AccountQuery accountQuery = null;
        if (account != null){
            accountQuery = new AccountQuery(account.getId(), account.getName(),
                    account.getBalance(), account.getEventDate(), account.getVersion());
        }

        return accountQuery;
    }

}
