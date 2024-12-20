package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired 
	private CategoryRepository categoryRepository;
	
    public Page<Category> getAllCategories(Pageable pageable)
    {
    	return categoryRepository.findAll(pageable);
    }
    
    public Optional<Category> getCategoryById(Long id)
    {
    	return categoryRepository.findById(id);
    }
    
    public Category saveCategory(Category category)
    {
    	return categoryRepository.save(category);
    }
    
    public void deleteCategory(Long id)
    {
    	categoryRepository.deleteById(id);
    }
}
