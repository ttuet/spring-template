package com.uu.spring.domain.catalog;

import com.uu.spring.exception.UnauthorizedException;
import com.uu.spring.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    MongoTemplate mongoTemplate;

    public Catalog create(CustomUserDetails user, CatalogDTO dto) {
        Catalog catalog = new Catalog();
        BeanUtils.copyProperties(dto, catalog);

        catalog.setOrganizationId(user.getOrganizationId());
        return mongoTemplate.save(catalog);
    }

    public Catalog findById(CustomUserDetails user, String id) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Catalog.class);
    }

    public List<Catalog> find(CustomUserDetails user, CatalogSearchDTO searchDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(user.getOrganizationId()));
        if (searchDTO.getText() != null)
            query.addCriteria(TextCriteria.forDefaultLanguage().matchingPhrase(searchDTO.getText()));
        if (searchDTO.getIsActive() != null)
            query.addCriteria(Criteria.where("active").is(searchDTO.getIsActive()));
        if (searchDTO.getIsDeleted() != null)
            query.addCriteria(Criteria.where("deleted").is(searchDTO.getIsDeleted()));
        query.skip(searchDTO.getSkip()).limit(searchDTO.getLimit());
        return mongoTemplate.find(query, Catalog.class);
    }

    public List<Catalog> findAll(CustomUserDetails user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(user.getOrganizationId())
                .and("active").is(true)
                .and("deleted").is(false));
        return mongoTemplate.find(query, Catalog.class);
    }

    public Catalog delete(CustomUserDetails user, String id) {
        return mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Update.update("deleted", true), Catalog.class);
    }

    public Catalog update(CustomUserDetails user, String id, CatalogDTO updateData) {
        Catalog old = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Catalog.class);
        if (old == null)
            throw UnauthorizedException.of("User does not have permission to update this document");
        BeanUtils.copyProperties(old, updateData);
        return mongoTemplate.save(old);
    }
}
