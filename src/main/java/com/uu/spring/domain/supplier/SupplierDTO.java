package com.uu.spring.domain.supplier;

import lombok.Data;

import java.util.List;

@Data
public class SupplierDTO {
    private String name;
    private String address;
    private String ward;
    private String district;
    private String province;
    private String country;

    private String profilePicture;

    private List<SupplierContactDTO> contacts;
}
