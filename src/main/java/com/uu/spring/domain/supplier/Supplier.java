package com.uu.spring.domain.supplier;

import com.uu.spring.common.BaseDocument;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("suppliers")
public class Supplier extends BaseDocument {
    private String organizationId;

    private String name;
    private String address;
    private String ward;
    private String district;
    private String province;
    private String country;

    private String profilePicture;

    private List<SupplierContact> contacts;
}
