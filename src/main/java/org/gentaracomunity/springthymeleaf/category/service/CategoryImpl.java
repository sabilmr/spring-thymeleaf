package org.gentaracomunity.springthymeleaf.category.service;

import org.gentaracomunity.springthymeleaf.category.model.CategoryRequest;
import org.gentaracomunity.springthymeleaf.category.model.CategoryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImpl implements CategoryService{
    private final List<CategoryResponse> categories;

    public CategoryImpl() {
        categories = new ArrayList<>();
    }

    @Override
    public List<CategoryResponse> get() {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        if (categories.isEmpty()){
            categories.addAll(categoryResponses);
        }
        return categories;
    }



    @Override
    public CategoryResponse getById(Long id) {
        CategoryResponse categoryResponse = categories.stream().filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
        return categoryResponse;
    }


    @Override
    public CategoryResponse save(CategoryRequest request) {
          long maxLong = categories.stream()
                .mapToLong(CategoryResponse::getId)
                .max()
                .orElse(0L);

        long newLong = maxLong + 1;
        CategoryResponse response1 = new CategoryResponse();
        BeanUtils.copyProperties(request, response1);
        response1.setId(newLong);
        categories.add(response1);
        return response1;
    }

    @Override
    public CategoryResponse update(CategoryRequest request, Long id) {
      for (CategoryResponse categoryResponse : categories) {
          if (categoryResponse.getId().equals(id)) {
              BeanUtils.copyProperties(request, categoryResponse);
              return categoryResponse;
          }
      }
      return null;
    }

    @Override
    public CategoryResponse delete(Long id) {
        CategoryResponse response = new CategoryResponse();
        categories.removeIf(category -> category.getId().equals(id));
        return response;
    }

    public void addNewCategory() {
        long maxLong = categories.stream()
                .mapToLong(CategoryResponse::getId)
                .max()
                .orElse(0L);

        long newLong = maxLong + 1;
        CategoryResponse newCategory = new CategoryResponse(newLong);
        categories.add(newCategory);

    }
}
