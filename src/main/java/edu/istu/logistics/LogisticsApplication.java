package edu.istu.logistics;

import edu.istu.logistics.algorithm.branchAndBound.Graph;
import edu.istu.logistics.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
		ApplicationConfig.initializeConfig();
		Graph branchAndBound = new Graph();
		branchAndBound.start("src/main/resources/testData/branchAndBoundTestDouble.txt");

	}

}
