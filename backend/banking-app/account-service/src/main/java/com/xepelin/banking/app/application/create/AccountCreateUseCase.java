package com.xepelin.banking.app.application.create;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.infrastructure.inbound.controllers.AccountController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCreateUseCase {

    private final AccountCommandRepository accountCommandRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountCreateUseCase.class);
    public Optional<AccountQuery> createAccount(AccountCommand accountCommand) throws Exception{
        logger.debug("delegating process to create account in repository: {}", accountCommand.toString());
        return accountCommandRepository.createAccount(accountCommand);
    }


}
