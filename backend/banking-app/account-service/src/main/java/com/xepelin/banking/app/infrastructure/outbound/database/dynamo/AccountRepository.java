package com.xepelin.banking.app.infrastructure.outbound.database.dynamo;

import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Account;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface AccountRepository extends CrudRepository<Account, Long> {

}
