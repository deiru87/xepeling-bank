package com.xepelin.banking.app.infrastructure.outbound.database;

import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;

public interface AccountMapper {

    AccountQuery toAccountQuery(Account account);

}
