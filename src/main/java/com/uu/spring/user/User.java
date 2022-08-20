package com.uu.spring.user;

import com.uu.spring.common.BaseDocument;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Data
@Builder
public class User extends BaseDocument {
    private String fullName;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Indexed(unique = true)
    private String email;

    private String organizationId;

}
