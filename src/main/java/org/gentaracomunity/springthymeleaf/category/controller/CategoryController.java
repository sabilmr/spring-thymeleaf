package org.gentaracomunity.springthymeleaf.category.controller;

import org.gentaracomunity.springthymeleaf.category.model.CategoryRequest;
import org.gentaracomunity.springthymeleaf.category.model.CategoryResponse;
import org.gentaracomunity.springthymeleaf.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView category() {
        ModelAndView view = new ModelAndView("category/index");
        // langka 1
//        List<CategoryResponse> responses = new ArrayList<>();
//        responses.add(new CategoryResponse(1L, "Nasi Goreng", "Enak sekali"));
//        responses.add(new CategoryResponse(1L, "Nasi Pecel", "Sedap dan Gurih"));
//        responses.add(new CategoryResponse(1L, "Nasi Rawon", "Dodone seger tenan"));


        List<CategoryResponse> categoryResponses = categoryService.get();
        view.addObject("data", categoryResponses);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/detail");

        CategoryResponse result = categoryService.getById(id);
        view.addObject("data", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("category/add");
        view.addObject("data", new CategoryRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("category") CategoryRequest request){
         categoryService.save(request);
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/edit");

        CategoryResponse result = categoryService.getById(id);
        view.addObject("data", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("category") CategoryRequest request){
        categoryService.update(request, request.getId());
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/delete");

        CategoryResponse categoryResponse = this.categoryService.getById(id);
        view.addObject("data", categoryResponse);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("category") CategoryRequest request){
        this.categoryService.delete(request.getId());
        return new ModelAndView("redirect:/category");
    }
}
