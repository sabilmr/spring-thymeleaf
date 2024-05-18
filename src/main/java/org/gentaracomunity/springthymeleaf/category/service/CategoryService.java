package org.gentaracomunity.springthymeleaf.category.service;

import org.gentaracomunity.springthymeleaf.category.model.CategoryRequest;
import org.gentaracomunity.springthymeleaf.category.model.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> get();
    CategoryResponse getById(Long id);
    CategoryResponse save(CategoryRequest request);
    CategoryResponse update(CategoryRequest request, Long id);
    CategoryResponse delete(Long id);
}
