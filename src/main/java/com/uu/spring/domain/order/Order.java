package com.uu.spring.domain.order;

import com.uu.spring.common.BaseDocument;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("orders")
@Data
public class Order extends BaseDocument {
    private String organizationId;

    private String number;
    private Double subTotal;
    private Double shippingFee;
    private Double total;
    private Double discountValue;
    private List<OrderItem> items;

    private String notes;
    private String customerId;
}
