package com.example.backendprojectmodule_productservice.Controllers;

import com.example.backendprojectmodule_productservice.Models.Product;
import com.example.backendprojectmodule_productservice.Services.ProductServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductServiceInterface productServiceInterface;

    @Test
    //This test ensures that the no one changed the implementation of the Controller
    //If the implementation of the controller is changed, then controller will not return the exact list
    //returned by the service
    //So we are checking against the stubbed list returned by the service
    void testForProductsSameAsService() {
        //Arrange
        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setTitle("p1");
        Product p2 = new Product();
        p2.setTitle("p2");
        products.add(p1);
        products.add(p2);
        //If we just reutrn the 'products' list, then the test will pass even if the controller
        //alters the values of the objects in the list due to the fact that the objects are passed by reference
        //So we should return a new list with the same values
        List<Product> products11 = new ArrayList<>();
        for (Product product : products) {
            Product product1 = new Product();
            product1.setTitle(product.getTitle());
            products11.add(product1);
        }
        when(productServiceInterface.getAllProducts()).thenReturn(products11);

        //Act
        ResponseEntity<List<Product>> productsResponse = productController.getAllProducts();

        //Assert
        assertEquals(products.size(), productsResponse.getBody().size());
        for(int i = 0; i < productsResponse.getBody().size(); i++){
            assertEquals(products.get(i).getTitle(), productsResponse.getBody().get(i).getTitle());
        }
    }

    @Test
    //This test ensures that the exception thrown by the service is also thrown by the controller
    //This helps in capturing it via Controller Advice and returning a proper response
    //to the end requestor
    void testForProductNotFound() {
        //Arrange
        Long id = 1L;
        when(productServiceInterface.getProductById(id)).thenThrow(new RuntimeException("Product not found"));

        //Assert
        assertThrows(RuntimeException.class, () -> {
            productController.getProductById(id);
        });
    }

    @Test
    void testForDeletingExistingProduct(){
        Long id = 1L;
        productController.deleteProduct(id);
        verify(productServiceInterface, times(1)).deleteProduct(id);
    }
}