package org.gentaracomunity.springthymeleaf.product.service;

import org.gentaracomunity.springthymeleaf.product.modal.ProductRequest;
import org.gentaracomunity.springthymeleaf.product.modal.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts();
    ProductResponse getById(Long id);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productRequest, Long id);
    ProductResponse deleteProduct(Long id);
}
