package org.gentaracomunity.springthymeleaf.supplier.service;

import org.gentaracomunity.springthymeleaf.supplier.model.SupplierRequest;
import org.gentaracomunity.springthymeleaf.supplier.model.SupplierResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierImpl implements SupplierService {
    private List<SupplierResponse> suppliers;

    public SupplierImpl() {
        suppliers = new ArrayList<>();
    }

    @Override
    public List<SupplierResponse> getSuppliers() {
        return suppliers;
    }

    @Override
    public SupplierResponse getByIdSupplier(Long id) {
        SupplierResponse response = suppliers.stream()
                .filter(supplier -> supplier.getId().equals(id))
                .findFirst()
                .orElse(null);
        return response;
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {
        long maxLong = suppliers.stream()
                .mapToLong(SupplierResponse::getId)
                .max()
                .orElse(0L);
        long newLong = maxLong + 1;
        SupplierResponse response = new SupplierResponse();
        BeanUtils.copyProperties(supplierRequest, response);
        response.setId(newLong);
        suppliers.add(response);
        return response;
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest supplierRequest) {
        for (SupplierResponse supplier : suppliers) {
            if (supplier.getId().equals(id)) {
                BeanUtils.copyProperties(supplierRequest, supplier);
                return supplier;
            }
        }
        return null;
    }

    @Override
    public SupplierResponse deleteSupplier(Long id) {
        SupplierResponse response = new SupplierResponse();
        suppliers.removeIf(supplier -> supplier.getId().equals(id));
        return response;
    }
}
