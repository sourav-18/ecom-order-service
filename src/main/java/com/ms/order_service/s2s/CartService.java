package com.ms.order_service.s2s;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CartService {
    private final RestTemplate restTemplate;
    private static final String apiUrl="http://localhost:4002/s2s/carts";

    public CartResponse getCartDetails(String userId){
        try{
            return restTemplate.getForObject(apiUrl+"/"+userId, CartResponse.class);
        } catch (Exception e) {
            System.out.println("enter");
            throw new RuntimeException(e.getMessage());
        }
    }
}
