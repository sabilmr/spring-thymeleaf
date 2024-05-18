package org.gentaracomunity.springthymeleaf.supplier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
}
