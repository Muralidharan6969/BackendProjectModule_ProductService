//package com.example.backendprojectmodule_productservice.Controllers;
//
//import com.example.backendprojectmodule_productservice.Models.Product;
//import com.example.backendprojectmodule_productservice.Services.ProductServiceInterface;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//
//    @MockBean
//    ProductServiceInterface productServiceInterface;
//
//    @Test
//    void testForProductsSameAsService() {
//        List<Product> products = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setTitle("p1");
//        Product p2 = new Product();
//        p2.setTitle("p2");
//        products.add(p1);
//        products.add(p2);
//        when(productServiceInterface.getAllProducts()).thenReturn(products);
//        List<Product> productsFromController = productController.getAllProducts();
//        assertEquals(products.size(), productsFromController.size());
//    }
//}