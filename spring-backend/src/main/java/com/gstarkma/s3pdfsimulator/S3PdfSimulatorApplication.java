package com.gstarkma.s3pdfsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gstarkma.s3pdfsimulator")
public class S3PdfSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3PdfSimulatorApplication.class, args);
	}
}
