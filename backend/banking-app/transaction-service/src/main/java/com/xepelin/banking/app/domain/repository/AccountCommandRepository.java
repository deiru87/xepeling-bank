package com.xepelin.banking.app.domain.repository;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;

import java.util.Optional;

public interface AccountCommandRepository {

    void updateAccount(AccountCommand accountCommand, boolean isRollback);

}
