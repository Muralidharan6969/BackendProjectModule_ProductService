package com.example.backendprojectmodule_productservice.Services;

import com.example.backendprojectmodule_productservice.Models.Category;
import com.example.backendprojectmodule_productservice.Models.Product;
import com.example.backendprojectmodule_productservice.Repositories.CategoryRepository;
import com.example.backendprojectmodule_productservice.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfAPIExecutionTest {

    @Autowired
    private ProductServiceInterface productServiceInterface;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    //This test ensures that when the Repository returns an empty optional
    //Service should throw the error
    void testForExceptionWhenProductNotFoundInRepo(){
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productServiceInterface.getProductById(id));
    }

    @Test
    //This test ensures that when a request is sent to the Service to add a product,
    //It just redirects the repository to add the product and doesn't alter anything on the way
    void testForProductSavedSameAsRequest(){
        Product p = new Product();
        p.setTitle("TestProduct");
        p.setCategory(new Category("TestCategory"));
        Product p2 = new Product();
        p2.setTitle(p.getTitle());
        p2.setCategory(new Category(p.getCategory().getCategoryName()));
//        when(categoryRepository.findByCategoryName(p2.getCategory().getCategoryName())).
//                thenReturn(Optional.empty());
//        when(categoryRepository.save(p2.getCategory())).thenReturn(p2.getCategory());
//        when(productRepository.save(p2)).thenReturn(p2);
        Product p1 = productServiceInterface.addProduct(p2);
        verify(productRepository).save(productCaptor.capture());
        assertEquals(p.getCategory().getCategoryName(), productCaptor.getValue().getCategory().getCategoryName());
    }
}