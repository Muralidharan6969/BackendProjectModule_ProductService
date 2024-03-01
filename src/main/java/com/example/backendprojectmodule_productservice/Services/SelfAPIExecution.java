package com.example.backendprojectmodule_productservice.Services;

import com.example.backendprojectmodule_productservice.Models.Category;
import com.example.backendprojectmodule_productservice.Models.Product;
import com.example.backendprojectmodule_productservice.Repositories.CategoryRepository;
import com.example.backendprojectmodule_productservice.Repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service("selfAPIExecution")
@Primary
public class SelfAPIExecution implements ProductServiceInterface {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfAPIExecution(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        return product.get();
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.
                findByCategoryName(product.getCategory().getCategoryName());
        if(categoryOptional.isEmpty()){
            categoryRepository.save(product.getCategory());
        }
        else{
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product productToUpdate = productOptional.get();
        if(product.getTitle() != null){
            productToUpdate.setTitle(product.getTitle());
        }
        if(product.getPrice() != 0){
            productToUpdate.setPrice(product.getPrice());
        }
        if(product.getDescription() != null){
            productToUpdate.setDescription(product.getDescription());
        }
        if(product.getImageURL() != null){
            productToUpdate.setImageURL(product.getImageURL());
        }
        if(product.getCategory() != null){
            Optional<Category> categoryOptional = categoryRepository.
                    findByCategoryName(product.getCategory().getCategoryName());
            if(categoryOptional.isEmpty()){
                categoryRepository.save(product.getCategory());
            }
            productToUpdate.setCategory(categoryOptional.get());
        }
        return productRepository.save(productToUpdate);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        Product productToUpdate = productOptional.get();
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImageURL(product.getImageURL());
        if(product.getCategory() != null){
            Optional<Category> categoryOptional = categoryRepository.
                    findByCategoryName(product.getCategory().getCategoryName());
            if(categoryOptional.isEmpty()){
                categoryRepository.save(product.getCategory());
            }
            productToUpdate.setCategory(categoryOptional.get());
        }
        else{
            productToUpdate.setCategory(product.getCategory());
        }
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> category.getCategoryName()).toList();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}
