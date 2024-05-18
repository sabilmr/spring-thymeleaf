package org.gentaracomunity.springthymeleaf.supplier.controller;

import org.gentaracomunity.springthymeleaf.supplier.model.SupplierRequest;
import org.gentaracomunity.springthymeleaf.supplier.model.SupplierResponse;
import org.gentaracomunity.springthymeleaf.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("supplier/index");

        List<SupplierResponse> responses = supplierService.getSuppliers();
        modelAndView.addObject("suppliers", responses);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("supplier/add");
        modelAndView.addObject("supplier", new SupplierRequest());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("supplier") SupplierRequest supplier){
        this.supplierService.createSupplier(supplier);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("supplier/detail");

        SupplierResponse response = supplierService.getByIdSupplier(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("supplier/edit");

        SupplierResponse response = supplierService.getByIdSupplier(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("supplier") SupplierRequest supplier){
        this.supplierService.updateSupplier(supplier.getId(), supplier);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("supplier/delete");

        SupplierResponse response = supplierService.getByIdSupplier(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("supplier") SupplierRequest supplier){
        this.supplierService.deleteSupplier(supplier.getId());
        return new ModelAndView("redirect:/supplier");
    }
}
