package com.uu.spring.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public Organization create(String ownerId) {
        Organization org = Organization.builder()
                .owner(ownerId)
                .build();
        return organizationRepository.save(org);
    }
}
