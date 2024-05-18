package org.gentaracomunity.springthymeleaf.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

    public CategoryResponse(long newLong) {
        this.id = newLong;

    }
}
