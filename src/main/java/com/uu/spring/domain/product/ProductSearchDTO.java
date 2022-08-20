package com.uu.spring.domain.product;

import com.uu.spring.common.SearchDTO;
import lombok.Data;

@Data
public class ProductSearchDTO extends SearchDTO {
    private String text;
    private Boolean isActive;
    private Boolean isDeleted;
    private String catalogId;
    private String tagId;
}
