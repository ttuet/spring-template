package com.uu.spring.notification;

import com.uu.spring.common.BaseDocument;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notifications")
@Data
public class Notification extends BaseDocument {
    private String organizationId;
}
