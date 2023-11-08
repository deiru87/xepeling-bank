package com.xepelin.banking.app.application.find;

import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountFindUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AccountFindUseCase.class);

    private final AccountQueryRepository accountQueryRepository;

    public Optional<AccountQuery> findById(Long id)  throws Exception{
        logger.debug("Searching account balance with account id : {}", id);
        return this.accountQueryRepository.findById(id);
    }


}
