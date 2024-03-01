package com.example.backendprojectmodule_productservice.Controllers;

import com.example.backendprojectmodule_productservice.Commons.AuthenticationCommons;
import com.example.backendprojectmodule_productservice.DTOs.Role;
import com.example.backendprojectmodule_productservice.DTOs.UserDTO;
import com.example.backendprojectmodule_productservice.Models.Product;
import com.example.backendprojectmodule_productservice.Services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductServiceInterface fakeStoreAPIExecution;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("fakeStoreAPIExecution") ProductServiceInterface fakeStoreAPIExecution,
                             AuthenticationCommons authenticationCommons) {
        this.fakeStoreAPIExecution = fakeStoreAPIExecution;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        //Checking if the token is valid
//        UserDTO userDTO = authenticationCommons.validateToken(token);
//        if(userDTO == null){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }

        //Checking if the user is an admin (Role based access control)
//        boolean isAdmin = false;
//        for(Role role : userDTO.getRoles()){
//            if(role.getName().equalsIgnoreCase("ADMIN")){
//                isAdmin = true;
//                break;
//            }
//        }
//        if(!isAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        //throw new RuntimeException("This is a test exception");
        return new ResponseEntity<>(fakeStoreAPIExecution.getAllProducts(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return fakeStoreAPIExecution.getProductById(id);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return fakeStoreAPIExecution.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id ,@RequestBody Product product) {
        return fakeStoreAPIExecution.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id ,@RequestBody Product product) {
        return fakeStoreAPIExecution.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        fakeStoreAPIExecution.deleteProduct(id);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return fakeStoreAPIExecution.getAllCategories();
    }

    @GetMapping("/category/{name}")
    public List<Product> getProductsByCategory(@PathVariable("name") String categoryName){
        return fakeStoreAPIExecution.getProductsByCategory(categoryName);
    }
}
