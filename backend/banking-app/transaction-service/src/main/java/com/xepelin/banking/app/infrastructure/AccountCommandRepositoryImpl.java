package com.xepelin.banking.app.infrastructure;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.CustomAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    private final CustomAccountRepository customAccountRepository;


    @Override
    public void updateAccount(AccountCommand accountCommand, boolean isRollBack) {
        customAccountRepository.updateAccount(accountCommand, isRollBack);

    }
}

