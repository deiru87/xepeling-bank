package com.xepelin.banking.app.infrastructure.outbound.database.dynamo.persistence;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.infrastructure.outbound.database.dynamo.CustomAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomAccountRepositoryImpl implements CustomAccountRepository {

    private final AmazonDynamoDB amazonDynamoDB;
    @Override
    public void updateAccount(AccountCommand accountCommand, boolean isRollback) {
        UpdateItemRequest updateItemRequest = new UpdateItemRequest()
                .withTableName("Account")
                .withKey(Collections.singletonMap("id", new AttributeValue().withN(String.valueOf(accountCommand.getId()))))
                .withUpdateExpression("SET balance = :newBalance, version = :newVersion")
                .withConditionExpression("version = :currentVersion")
                .withExpressionAttributeValues(Map.of(
                        ":newBalance", new AttributeValue().withN(String.valueOf(accountCommand.getBalance())),
                        ":newVersion", new AttributeValue().withN(String.valueOf(isRollback ? accountCommand.getVersion() + 2 : accountCommand.getVersion() + 1 )),
                        ":currentVersion", new AttributeValue().withN(String.valueOf(isRollback ? accountCommand.getVersion() + 1: accountCommand.getVersion())
                )));
        amazonDynamoDB.updateItem(updateItemRequest);
    }

}
