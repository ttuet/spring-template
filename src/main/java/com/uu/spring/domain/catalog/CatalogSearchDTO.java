package com.uu.spring.domain.catalog;

import com.uu.spring.common.SearchDTO;
import lombok.Data;

@Data
public class CatalogSearchDTO extends SearchDTO {
    private String text;
    private Boolean isActive;
    private Boolean isDeleted;
}
