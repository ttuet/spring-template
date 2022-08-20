package com.uu.spring.organization;

import com.uu.spring.common.BaseDocument;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("organizations")
@Data
@Builder
public class Organization extends BaseDocument {
    private String name;
    private String description;
    private String code;
    private String owner;
    private String profilePicture;
}
