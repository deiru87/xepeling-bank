package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.xepelin.banking.app.domain.model.TypeTransaction;


public class TypeTransactionConverter implements DynamoDBTypeConverter<String, TypeTransaction> {

    @Override
    public String convert( final TypeTransaction typeTransaction ) {
        return typeTransaction.toString();
    }

    @Override
    public TypeTransaction unconvert( final String stringValue ) {
        return TypeTransaction.valueOf(stringValue);
    }

}
