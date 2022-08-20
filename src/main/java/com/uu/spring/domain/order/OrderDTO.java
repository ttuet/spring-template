package com.uu.spring.domain.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Double shippingFee;
    private Double discountValue;
    private List<OrderItemDTO> items;

    private String notes;
    private String customerId;
}

