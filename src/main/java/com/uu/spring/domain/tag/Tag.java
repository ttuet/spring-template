package com.uu.spring.domain.tag;

import com.uu.spring.common.BaseDocument;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tags")
@Data
public class Tag extends BaseDocument {
    private String organizationId;

    private String name;
    private TagType type;
    private String description;
    private String colorHex;
}
