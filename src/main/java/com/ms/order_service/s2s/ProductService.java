package com.ms.order_service.s2s;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class ProductService {
    private final RestTemplate restTemplate;
    private static final String apiUrl="http://localhost:4001/s2s/products";

    private record ProductPriceRequest(Set<String> ids){};

    public List<ProductPriceResponse> getPriceDetails(Set<String> ids) {
        try {
            ProductPriceRequest request = new ProductPriceRequest(ids);

            ResponseEntity<List<ProductPriceResponse>> response =
                    restTemplate.exchange(
                            apiUrl + "/price",
                            HttpMethod.POST,
                            new HttpEntity<>(request),
                            new ParameterizedTypeReference<List<ProductPriceResponse>>() {}
                    );

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch price details", e);
        }
    }
}
