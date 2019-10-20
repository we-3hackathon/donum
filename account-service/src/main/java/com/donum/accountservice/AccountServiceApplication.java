package com.donum.accountservice;


import com.donum.accountservice.Controller.APIKeyController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

	final static Logger logger = Logger.getLogger(AccountServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
		checkKeysAreLoaded();
	}

	private static boolean checkKeysAreLoaded(){

		if(APIKeyController._singleDynamoRepo == null){
			try {
				URL url = new URL("http://localhost:8020/api-key/status");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				conn.disconnect();

			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
