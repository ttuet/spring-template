package com.uu.spring.domain.tag;

import com.uu.spring.common.SearchDTO;
import lombok.Data;

@Data
public class TagSearchDTO extends SearchDTO {
    private String text;
    private TagType type;
    private Boolean isActive;
    private Boolean isDeleted;
}
