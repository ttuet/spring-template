package com.uu.spring.domain.customer;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String ward;
    private String district;
    private String province;
    private String country;
}
