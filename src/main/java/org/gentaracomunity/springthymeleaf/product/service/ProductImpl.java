package org.gentaracomunity.springthymeleaf.product.service;

import org.gentaracomunity.springthymeleaf.product.modal.ProductRequest;
import org.gentaracomunity.springthymeleaf.product.modal.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl implements ProductService{
    private List<ProductResponse> product;

    public ProductImpl() {
        product = new ArrayList<>();
    }
    @Override
    public List<ProductResponse> getProducts() {
        List<ProductResponse> products = new ArrayList<>();
        if (product.isEmpty()){
            product.addAll(products);
        }
        return product;
    }

    @Override
    public ProductResponse getById(Long id) {
        ProductResponse productResponse = product.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
        return productResponse;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        long maxLong = product.stream()
                .mapToLong(ProductResponse::getId)
                .max()
                .orElse(0L);
        long newLong = maxLong + 1;
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(productRequest, response);
        response.setId(newLong);
        product.add(response);
        return response;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
        for (ProductResponse productResponse : product) {
            if (productResponse.getId().equals(id)) {
                BeanUtils.copyProperties(productRequest, productResponse);
                return productResponse;
            }
        }
        return null;
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
       ProductResponse response = new ProductResponse();
       product.removeIf(product -> product.getId().equals(id));
       return response;
    }

}
