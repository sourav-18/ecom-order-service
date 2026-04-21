package com.ms.order_service.services;

import com.ms.order_service.domins.Order;
import com.ms.order_service.repositories.OrderRepository;
import com.ms.order_service.s2s.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    public Object create(String userId){
        CartResponse cartDetails= cartService.getCartDetails(userId);
        Set<String> productIds=cartDetails.getProducts()
                .stream().map(CartProduct::getId)
                .collect(Collectors.toSet());
      List<ProductPriceResponse> priceResponse= productService.getPriceDetails(productIds);
        Map<String, Integer> priceMap = priceResponse.stream()
                .collect(Collectors.toMap(
                        ProductPriceResponse::getId,
                        ProductPriceResponse::getPrice
                ));

        System.out.println(priceMap);

        int totalPrice = cartDetails.getProducts().stream()
                .reduce(0,
                        (sum, item) -> sum + (priceMap.getOrDefault(item.getId(), 0)*item.getQuantity()),
                        Integer::sum);

        Order order=new Order();
        order.setCardId(cartDetails.getId());
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        return cartDetails;

    }
}
