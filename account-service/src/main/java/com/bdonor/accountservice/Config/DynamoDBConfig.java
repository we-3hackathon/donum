package com.bdonor.accountservice.Config;

import com.bdonor.accountservice.Model.Variable.EnumAPIKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


public class DynamoDBConfig {

    private String awsAccessKey;

    private String awsSecretKey;

    @Value("${amazon.region}")
    private String awsRegion;

    @Value("${amazon.end-point.url}")
    private String awsDynamoDBEndPoint;

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    public AmazonDynamoDB amazonDynamoDBConfig() {
        System.out.println("**********************************************i am running*********************************************");
        System.out.println("**********************************************i am running*********************************************");

        System.out.println("**********************************************i am running*********************************************");

        System.out.println("**********************************************i am running*********************************************");

        System.out.println("**********************************************i am running*********************************************");

        awsAccessKey = EnumAPIKey.DYNAMO_KEY.toString();
        awsSecretKey = EnumAPIKey.DYNAMO_SECRET_KEY.toString();
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.eu-west-2.amazonaws.com", awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
}


