package com.example.demoshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ShoppingController {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/doShoppingUsingWebClient/{price}")
	public String doShopping(@PathVariable String price) {
		String uri = "http://localhost:8888/payment-provider/payNow/" + price;
		return webClientBuilder.build()
				.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	@GetMapping("/doShoppingUsingRestTemplate/{price}")
	public String doShoppingUsingRestTemplate(@PathVariable String price) {
		String uri = "http://localhost:8888/payment-provider/payNow/" + price;
		return restTemplate.getForObject(uri, String.class);
	}
}
