package com.uu.spring.domain.order;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String productId;
    private Integer quantity;
    private Double unitPrice;
}
