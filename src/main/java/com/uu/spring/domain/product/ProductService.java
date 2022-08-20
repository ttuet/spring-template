package com.uu.spring.domain.product;

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
public class ProductService {
    @Autowired
    MongoTemplate mongoTemplate;

    public Product create(CustomUserDetails user, ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        product.setOrganizationId(user.getOrganizationId());
        return mongoTemplate.save(product);
    }

    public Product findById(CustomUserDetails user, String productId) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(productId).and("organizationId").is(user.getOrganizationId())),
                Product.class);
    }

    public List<Product> find(CustomUserDetails user, ProductSearchDTO searchDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(user.getOrganizationId()));
        if (searchDTO.getText() != null)
            query.addCriteria(TextCriteria.forDefaultLanguage().matchingPhrase(searchDTO.getText()));
        if (searchDTO.getIsActive() != null)
            query.addCriteria(Criteria.where("active").is(searchDTO.getIsActive()));
        if (searchDTO.getIsDeleted() != null)
            query.addCriteria(Criteria.where("deleted").is(searchDTO.getIsDeleted()));
        if (searchDTO.getCatalogId() != null)
            query.addCriteria(Criteria.where("catalogIds").all(searchDTO.getCatalogId()));
        if (searchDTO.getTagId() != null)
            query.addCriteria(Criteria.where("tagIds").all(searchDTO.getTagId()));
        query.skip(searchDTO.getSkip()).limit(searchDTO.getLimit());
        return mongoTemplate.find(query, Product.class);
    }

    public Product delete(CustomUserDetails user, String productId) {
        return mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(productId).and("organizationId").is(user.getOrganizationId())),
                Update.update("deleted", true), Product.class);
    }

    public Product update(CustomUserDetails user, String productId, ProductDTO updateData) {
        Product oldProduct = mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(productId).and("organizationId").is(user.getOrganizationId())),
                Product.class);
        if (oldProduct == null)
            throw UnauthorizedException.of("User does not have permission to update this product");
        BeanUtils.copyProperties(oldProduct, updateData);
        return mongoTemplate.save(oldProduct);
    }
}
