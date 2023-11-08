package com.xepelin.banking.app.domain.repository;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import java.util.Optional;

public interface AccountCommandRepository {

    Optional<AccountQuery> createAccount(AccountCommand accountCommand) throws Exception;

}
