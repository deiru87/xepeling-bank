package com.xepelin.banking.app.infrastructure.outbound.database.dynamo;

import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa.Transaction;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
