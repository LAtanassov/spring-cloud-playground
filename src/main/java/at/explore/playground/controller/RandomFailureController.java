package at.explore.playground.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class RandomFailureController {
	
	@Value("${failure.rate:0.5}")
	private double failureRate;
	
	@GetMapping("/random-failure")
	@HystrixCommand(fallbackMethod="defaultNumber")
	public int randomlyFailure() {
		if (Math.random() > failureRate) {
			throw new NullPointerException();
		} 
		return 0;
		
	}
	
	public int defaultNumber() {
		return 0;
	}
}
