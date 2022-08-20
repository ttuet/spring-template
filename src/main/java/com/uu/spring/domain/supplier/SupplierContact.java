package com.uu.spring.domain.supplier;

import lombok.Data;

@Data
public class SupplierContact {
    private String contactName;
    private String contactPersonName;
    private String contactPersonPosition;
    private String phoneNumber;
    private String email;
    private String address;
    private String ward;
    private String district;
    private String province;
    private String country;
}
