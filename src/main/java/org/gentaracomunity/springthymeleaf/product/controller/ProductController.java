package org.gentaracomunity.springthymeleaf.product.controller;

import org.gentaracomunity.springthymeleaf.category.model.CategoryResponse;
import org.gentaracomunity.springthymeleaf.category.service.CategoryService;
import org.gentaracomunity.springthymeleaf.product.modal.ProductRequest;
import org.gentaracomunity.springthymeleaf.product.modal.ProductResponse;
import org.gentaracomunity.springthymeleaf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("product/index");
        addObject(mav);
//        List<ProductResponse> products = new ArrayList<>();
//        products.add(new ProductResponse(1L,2L,"Kopi Tubruk","Enak sekali kalo di seduh dipagi hari",200.0,30.0));

        List<ProductResponse> products2 =productService.getProducts();
        mav.addObject("products", products2);
        addObject(mav);
        return mav;
    }

    public void addObject(ModelAndView view){
        List<CategoryResponse> categoryResponses = categoryService.get();
        view.addObject("category", categoryResponses);
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("product/detail");
        addObject(mav);

        ProductResponse productResponse = productService.getById(id);
        mav.addObject("product", productResponse);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("product/add");
        mav.addObject("product", new ProductRequest());
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("product") ProductRequest productRequest) {
        ModelAndView mav = new ModelAndView("product/add");
        addObject(mav);

        this.productService.createProduct(productRequest);
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("product/edit");
        addObject(mav);

        ProductResponse productResponse = productService.getById(id);
        mav.addObject("product", productResponse);
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("product") ProductRequest productRequest) {
        this.productService.updateProduct(productRequest, productRequest.getId());
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("product/delete");
        addObject(mav);

        ProductResponse productResponse = productService.getById(id);
        mav.addObject("product", productResponse);
        return mav;
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("product") ProductRequest productRequest) {
        this.productService.deleteProduct(productRequest.getId());
        return new ModelAndView("redirect:/product");
    }
}
