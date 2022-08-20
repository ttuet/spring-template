package com.uu.spring.domain.product;

import com.uu.spring.common.CommonResponse;
import com.uu.spring.exception.NotFoundException;
import com.uu.spring.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public CommonResponse<Product> create(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user, @RequestBody ProductDTO productDTO) {
        return CommonResponse.success(productService.create(user, productDTO));
    }

    @GetMapping("/{id}")
    public CommonResponse<Product> findById(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                            @PathVariable("id") String id) {
        Product product = productService.findById(user, id);
        if (product != null)
            return CommonResponse.success(product);
        throw NotFoundException.of("Not found");
    }

    @GetMapping
    public CommonResponse<List<Product>> find(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                              ProductSearchDTO productSearchDTO) {
        return CommonResponse.success(productService.find(user, productSearchDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<Product> update(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                          @PathVariable("id") String id,
                                          @RequestBody ProductDTO productDTO) {
        Product product = productService.update(user, id, productDTO);
        return CommonResponse.success(product);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Product> delete(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                          @PathVariable("id") String id) {
        Product product = productService.delete(user, id);
        return CommonResponse.success(product);
    }
}
