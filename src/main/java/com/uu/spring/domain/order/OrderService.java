package com.uu.spring.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    MongoTemplate mongoTemplate;

}
