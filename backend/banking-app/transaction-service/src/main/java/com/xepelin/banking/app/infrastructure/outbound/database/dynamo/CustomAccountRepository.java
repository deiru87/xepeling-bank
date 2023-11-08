package com.xepelin.banking.app.infrastructure.outbound.database.dynamo;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;

import java.util.Optional;

public interface CustomAccountRepository {

    void updateAccount(AccountCommand accountCommand, boolean isRollBack);
}
