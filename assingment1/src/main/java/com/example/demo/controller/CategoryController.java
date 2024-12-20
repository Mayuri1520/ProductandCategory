package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
	private CategoryService categoryService;
    
    @GetMapping("/get")
    public Page<Category>getAllCategories(@RequestParam int page, @RequestParam int size)
    {
    	return categoryService.getAllCategories(PageRequest.of(page, size));
    }
	
    @PostMapping("/save")
    public Category createCategory(@RequestBody Category category)
    {
		 return categoryService.saveCategory(category);
    	
    }
    
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id)
    {
    	return categoryService.getCategoryById(id).orElseThrow(() -> new RuntimeException("category not found"));
    }
    
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails)
    {
    	Category category = categoryService.getCategoryById(id).orElseThrow(() -> new RuntimeException("category not found"));
    	
    	category.setName(categoryDetails.getName());
    	
    	return categoryService.saveCategory(category);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
    	categoryService.deleteCategory(id);
    }
    
	
}
