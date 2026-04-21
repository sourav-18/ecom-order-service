package com.ms.order_service.s2s;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
public class CartResponse {

    private Long id;

    private List<CartProduct> products;

    private String userId;

    private CartStatus status;
}
