package com.xepelin.banking.app.infrastructure.mapper;


import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GeneralMapper {

    public Optional<AccountQuery> toAccountQuery(AccountCommand accountCommand){
        AccountQuery accountQuery = null;
        if (accountCommand != null){
            accountQuery = new AccountQuery(accountCommand.getId(), accountCommand.getName(),
                    accountCommand.getBalance(), accountCommand.getEventDate(), accountCommand.getVersion());
        }

        return Optional.ofNullable(accountQuery);
    }
}
