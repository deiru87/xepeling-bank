package com.xepelin.banking.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiddlewareConsumerApplication  implements CommandLineRunner {

		private static final Logger logger = LoggerFactory.getLogger(MiddlewareConsumerApplication.class);

		public static void main(String[] args) {
			SpringApplication.run(MiddlewareConsumerApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			logger.debug("\n\n\nreceiving messages for transaction analysis...\n\n\n");
		}

}

