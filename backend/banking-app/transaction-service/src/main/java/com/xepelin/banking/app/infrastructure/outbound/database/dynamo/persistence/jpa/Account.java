package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.jpa;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
