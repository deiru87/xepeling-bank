package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.xepelin.banking.app.domain.model.TypeTransaction;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.LocalDateTimeConverter;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.TypeTransactionConverter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "Transaction")
public class Transaction {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE)
    private String id;
    @DynamoDBIndexHashKey(attributeName = "accountId", globalSecondaryIndexName = "search-account")
    private Long accountId;
    @DynamoDBAttribute
    @DynamoDBTypeConverted( converter = TypeTransactionConverter.class )
    private TypeTransaction type;
    @DynamoDBAttribute
    private BigDecimal amount;
    @DynamoDBAttribute
    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    private LocalDateTime eventDate = LocalDateTime.now();

}
