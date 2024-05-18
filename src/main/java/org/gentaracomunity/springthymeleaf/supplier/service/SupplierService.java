package org.gentaracomunity.springthymeleaf.supplier.service;

import org.gentaracomunity.springthymeleaf.supplier.model.SupplierRequest;
import org.gentaracomunity.springthymeleaf.supplier.model.SupplierResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierResponse> getSuppliers();
    SupplierResponse getByIdSupplier(Long id);
    SupplierResponse createSupplier(SupplierRequest supplierRequest);
    SupplierResponse updateSupplier(Long id, SupplierRequest supplierRequest);
    SupplierResponse deleteSupplier(Long id);
}
