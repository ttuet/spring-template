package com.uu.spring.domain.order;

import com.uu.spring.domain.product.Product;
import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private Product product;
    private Integer quantity;
    private Double unitPrice;
}
