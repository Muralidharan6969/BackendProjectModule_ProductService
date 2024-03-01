package com.example.backendprojectmodule_productservice.Services;

import com.example.backendprojectmodule_productservice.Models.Product;

import java.util.List;

public interface ProductServiceInterface {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product addProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public Product replaceProduct(Long id, Product product);
    public void deleteProduct(Long id);
    public List<String> getAllCategories();
    public List<Product> getProductsByCategory(String categoryName);
}
