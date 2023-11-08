package com.xepelin.banking.app.infrastructure;

import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountQueryRepository;
import com.xepelin.banking.app.infrastructure.outbound.database.AccountMapper;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.AccountRepository;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AccountQueryRepositoryImpl implements AccountQueryRepository {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<AccountQuery> findById(Long id) throws Exception {
         Optional<Account> optionalAccount = accountRepository.findById(id);
         Account account = null;
         if (optionalAccount.isPresent()){
             account = optionalAccount.get();
         }else{
             throw new Exception("There is no account number:" + id);
         }

         return Optional.of(accountMapper.toAccountQuery(account));
    }
}

