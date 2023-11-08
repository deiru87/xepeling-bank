package com.xepelin.banking.app.infrastructure.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.xepelin.banking.app.infrastructure.outbound.database.dynamo")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider){
        return AmazonDynamoDBClientBuilder.standard()
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoEndpoint, "us-east-1"))
                        .withCredentials(awsCredentialsProvider).build();
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(){
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey));
    }



}
