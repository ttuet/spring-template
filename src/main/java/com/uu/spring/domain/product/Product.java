package com.uu.spring.domain.product;

import com.uu.spring.common.BaseDocument;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("products")
public class Product extends BaseDocument {
    private String organizationId;

    @TextIndexed
    @Field("name")
    private String name;
    private String code;
    private String description;
    private String sku;
    private List<String> catalogIds;
    private List<String> tagIds;
    private List<String> images;
    private String unit;
    private Double unitPrice;
    private Integer numberInStock = 0;
}
