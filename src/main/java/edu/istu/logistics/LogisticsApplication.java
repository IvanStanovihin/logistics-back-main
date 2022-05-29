package edu.istu.logistics;

import edu.istu.logistics.algorithm.branchAndBound.BranchAndBoundEngine;
import edu.istu.logistics.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
		ApplicationConfig.initializeConfig();
//		BranchAndBoundEngine branchAndBound = new BranchAndBoundEngine();
//		branchAndBound.start("src/main/resources/testData/branchAndBoundTestDouble.txt");

	}

}
