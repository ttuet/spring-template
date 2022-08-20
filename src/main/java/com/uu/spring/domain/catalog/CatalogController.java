package com.uu.spring.domain.catalog;

import com.uu.spring.common.CommonResponse;
import com.uu.spring.exception.NotFoundException;
import com.uu.spring.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {
    @Autowired
    CatalogService catalogService;

    @PostMapping
    public CommonResponse<Catalog> create(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user, @RequestBody CatalogDTO dto) {
        return CommonResponse.success(catalogService.create(user, dto));
    }

    @GetMapping("/all")
    public CommonResponse<List<Catalog>> findAll(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user) {
        List<Catalog> result = catalogService.findAll(user);
        return CommonResponse.success(result);
    }

    @GetMapping("/{id}")
    public CommonResponse<Catalog> findById(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                            @PathVariable("id") String id) {
        Catalog result = catalogService.findById(user, id);
        if (result != null)
            return CommonResponse.success(result);
        throw NotFoundException.of("Not found");
    }

    @GetMapping
    public CommonResponse<List<Catalog>> find(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                              CatalogSearchDTO searchDTO) {
        return CommonResponse.success(catalogService.find(user, searchDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<Catalog> update(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                          @PathVariable("id") String id,
                                          @RequestBody CatalogDTO dto) {
        Catalog result = catalogService.update(user, id, dto);
        return CommonResponse.success(result);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Catalog> delete(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                          @PathVariable("id") String id) {
        Catalog result = catalogService.delete(user, id);
        return CommonResponse.success(result);
    }
}
