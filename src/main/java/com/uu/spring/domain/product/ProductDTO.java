package com.uu.spring.domain.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private String organizationId;

    private String name;
    private String code;
    private String description;
    private String sku;
    private List<String> catalogIds;
    private List<String> tagIds;
    private List<String> images;
    private String unit;
    private Double unitPrice;
}
