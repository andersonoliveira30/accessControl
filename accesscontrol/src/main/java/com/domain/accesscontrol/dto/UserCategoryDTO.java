package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.UserCategory;
import lombok.Data;


@Data
public class UserCategoryDTO {

    private Long id;
    private String description;

    public UserCategoryDTO(UserCategory userCategory) {
        this.id = userCategory.getId();
        this.description = userCategory.getDescription();
    }
}
