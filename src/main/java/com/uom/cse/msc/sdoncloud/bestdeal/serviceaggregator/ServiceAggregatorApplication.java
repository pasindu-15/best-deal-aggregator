package com.uom.cse.msc.sdoncloud.bestdeal.serviceaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("${base-url.context}")
public class ServiceAggregatorApplication {

	@GetMapping("")
	String hello() {
		return "Hello! Service Aggregator is running";
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceAggregatorApplication.class, args);
	}

}
