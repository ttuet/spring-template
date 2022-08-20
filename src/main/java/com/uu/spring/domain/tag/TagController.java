package com.uu.spring.domain.tag;

import com.uu.spring.common.CommonResponse;
import com.uu.spring.exception.NotFoundException;
import com.uu.spring.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping
    public CommonResponse<Tag> create(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user, @RequestBody TagDTO dto) {
        return CommonResponse.success(tagService.create(user, dto));
    }

    @GetMapping("/all")
    public CommonResponse<List<Tag>> findAll(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user) {
        List<Tag> result = tagService.findAll(user);
        return CommonResponse.success(result);
    }

    @GetMapping("/{id}")
    public CommonResponse<Tag> findById(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                        @PathVariable("id") String id) {
        Tag result = tagService.findById(user, id);
        if (result != null)
            return CommonResponse.success(result);
        throw NotFoundException.of("Not found");
    }

    @GetMapping
    public CommonResponse<List<Tag>> find(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                          TagSearchDTO searchDTO) {
        return CommonResponse.success(tagService.find(user, searchDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<Tag> update(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                      @PathVariable("id") String id,
                                      @RequestBody TagDTO dto) {
        Tag result = tagService.update(user, id, dto);
        return CommonResponse.success(result);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Tag> delete(@AuthenticationPrincipal @ApiIgnore CustomUserDetails user,
                                      @PathVariable("id") String id) {
        Tag result = tagService.delete(user, id);
        return CommonResponse.success(result);
    }
}
