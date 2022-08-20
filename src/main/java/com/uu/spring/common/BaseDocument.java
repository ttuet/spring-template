package com.uu.spring.common;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseDocument implements Serializable {
    @Id
    private String id = NanoIdUtils.randomNanoId();

    @Indexed
    private boolean isActive = true;
    @Indexed
    private boolean isDeleted = false;

    @CreatedDate
    @Indexed
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}
