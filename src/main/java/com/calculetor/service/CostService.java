package com.calculetor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CostService {

    private final RestTemplate restTemplate;

    public CostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer getRandomCost() {
        // Replace with the actual random.org API call
        // Example: Fetch a random number between 1 and 100
        String url = "https://www.random.org/integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new";
        String response = restTemplate.getForObject(url, String.class);
        return Integer.parseInt(response.trim());
    }
}
