package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.LocalDateTimeConverter;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Account")
public class Account {

    @DynamoDBHashKey
    private Long id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private BigDecimal balance;
    @DynamoDBAttribute
    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    private LocalDateTime eventDate = LocalDateTime.now();
    @DynamoDBAttribute
    private Long version;

}
