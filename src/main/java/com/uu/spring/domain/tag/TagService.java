package com.uu.spring.domain.tag;

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
public class TagService {
    @Autowired
    MongoTemplate mongoTemplate;

    public Tag create(CustomUserDetails user, TagDTO dto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(dto, tag);

        tag.setOrganizationId(user.getOrganizationId());
        return mongoTemplate.save(tag);
    }

    public Tag findById(CustomUserDetails user, String id) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Tag.class);
    }

    public List<Tag> find(CustomUserDetails user, TagSearchDTO searchDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(user.getOrganizationId()));
        if (searchDTO.getText() != null)
            query.addCriteria(TextCriteria.forDefaultLanguage().matchingPhrase(searchDTO.getText()));
        if (searchDTO.getIsActive() != null)
            query.addCriteria(Criteria.where("active").is(searchDTO.getIsActive()));
        if (searchDTO.getIsDeleted() != null)
            query.addCriteria(Criteria.where("deleted").is(searchDTO.getIsDeleted()));
        if (searchDTO.getType() != null)
            query.addCriteria(Criteria.where("type").is(searchDTO.getType()));
        query.skip(searchDTO.getSkip()).limit(searchDTO.getLimit());
        return mongoTemplate.find(query, Tag.class);
    }

    public List<Tag> findAll(CustomUserDetails user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(user.getOrganizationId())
                .and("active").is(true)
                .and("deleted").is(false));
        return mongoTemplate.find(query, Tag.class);
    }

    public Tag delete(CustomUserDetails user, String id) {
        return mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Update.update("deleted", true), Tag.class);
    }

    public Tag update(CustomUserDetails user, String id, TagDTO updateData) {
        Tag old = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id).and("organizationId").is(user.getOrganizationId())),
                Tag.class);
        if (old == null)
            throw UnauthorizedException.of("User does not have permission to update this document");
        BeanUtils.copyProperties(old, updateData);
        return mongoTemplate.save(old);
    }
}
