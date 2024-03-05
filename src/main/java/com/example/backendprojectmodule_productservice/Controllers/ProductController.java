package com.example.backendprojectmodule_productservice.Controllers;

import com.example.backendprojectmodule_productservice.Commons.AuthenticationCommons;
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
    private ProductServiceInterface productServiceInterface;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(ProductServiceInterface productServiceInterface,
                             AuthenticationCommons authenticationCommons) {
        this.productServiceInterface = productServiceInterface;
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
        return new ResponseEntity<>(productServiceInterface.getAllProducts(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productServiceInterface.getProductById(id);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return productServiceInterface.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id ,@RequestBody Product product) {
        return productServiceInterface.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id ,@RequestBody Product product) {
        return productServiceInterface.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productServiceInterface.deleteProduct(id);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productServiceInterface.getAllCategories();
    }

    @GetMapping("/category/{name}")
    public List<Product> getProductsByCategory(@PathVariable("name") String categoryName){
        return productServiceInterface.getProductsByCategory(categoryName);
    }
}
