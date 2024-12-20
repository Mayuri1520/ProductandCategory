package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/get")
	public Page<Product> getAllProduct(@RequestParam int page, @RequestParam int size)
	{
		return productService.getAllProducts(PageRequest.of(page, size));
		
	}
	
	@PostMapping("/save")
	public Product createProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id)
	{
		return productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails)
	{
		
		Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	    
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setCategory(productDetails.getCategory());
		
		return productService.saveProduct(product);
	
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id)
	{
		productService.deleteProduct(id);
	}
     
	
}
