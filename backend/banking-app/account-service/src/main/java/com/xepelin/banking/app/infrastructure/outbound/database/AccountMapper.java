package com.xepelin.banking.app.infrastructure.outbound.database;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;

public interface AccountMapper {

    Account toAccountEntityJPA(AccountCommand accountCommand);
    AccountQuery toAccountQuery(Account account);

}
