package com.research.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL =
            "http://localhost:8081/api/users/";

    public Object getUserById(Long userId) {

        String url = USER_SERVICE_URL + userId;

        return restTemplate.getForObject(url, Object.class);
    }
}