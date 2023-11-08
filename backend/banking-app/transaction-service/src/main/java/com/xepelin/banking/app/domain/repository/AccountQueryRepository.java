package com.xepelin.banking.app.domain.repository;

import com.xepelin.banking.app.domain.model.AccountQuery;

import java.util.Optional;

public interface AccountQueryRepository {

    Optional<AccountQuery> findById(Long id) throws Exception;

}
