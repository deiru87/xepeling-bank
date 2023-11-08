package com.xepelin.banking.app.infrastructure;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.xepelin.banking.app.application.create.AccountCreateUseCase;
import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.infrastructure.mapper.GeneralMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.AccountMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountCommandRepositoryImpl.class);

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final GeneralMapper generalMapper;


    @Override
    public Optional<AccountQuery> createAccount(AccountCommand accountCommand) {
        this.accountRepository.save(accountMapper.toAccountEntityJPA(accountCommand));
        logger.debug("Account saved successful in repository");
        return generalMapper.toAccountQuery(accountCommand);
    }

}
