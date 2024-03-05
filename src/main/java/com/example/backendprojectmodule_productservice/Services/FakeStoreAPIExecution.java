package com.example.backendprojectmodule_productservice.Services;

import com.example.backendprojectmodule_productservice.DTOs.FakeStoreToProductDTO;
import com.example.backendprojectmodule_productservice.Models.Category;
import com.example.backendprojectmodule_productservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("fakeStoreAPIExecution")
//@Primary
public class FakeStoreAPIExecution implements ProductServiceInterface{
    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    @Autowired
    public FakeStoreAPIExecution(RestTemplate restTemplate,
                                 RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public Product mapFakeStoreToProduct(FakeStoreToProductDTO fakeStoreToProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreToProductDTO.getId());
        product.setTitle(fakeStoreToProductDTO.getTitle());
        product.setPrice(fakeStoreToProductDTO.getPrice());
        product.setDescription(fakeStoreToProductDTO.getDescription());
        product.setImageURL(fakeStoreToProductDTO.getImage());
        product.setCategory(new Category());
        product.getCategory().setCategoryName(fakeStoreToProductDTO.getCategory());
        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreToProductDTO> productDTOs = Arrays.asList(restTemplate.getForObject
                ("https://fakestoreapi.com/products", FakeStoreToProductDTO[].class));
        return productDTOs.stream().map(productDTO -> mapFakeStoreToProduct(productDTO))
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "Product "+id);
        if (product != null) {
            return product;
        }
        FakeStoreToProductDTO productDTO = restTemplate.getForObject
                ("https://fakestoreapi.com/products/" + id, FakeStoreToProductDTO.class);
        Product product1 = mapFakeStoreToProduct(productDTO);
        redisTemplate.opsForHash().put("PRODUCTS", "Product "+id, product1);
        return product1;
    }

    @Override
    public Product addProduct(Product product) {
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);*/
        FakeStoreToProductDTO productDTO = restTemplate.postForObject("https://fakestoreapi.com/products",
                new FakeStoreToProductDTO(), FakeStoreToProductDTO.class);
        return mapFakeStoreToProduct(productDTO);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreToProductDTO productDTO = restTemplate.patchForObject
                ("https://fakestoreapi.com/products/" + id, new FakeStoreToProductDTO(), FakeStoreToProductDTO.class);
        return mapFakeStoreToProduct(productDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreToProductDTO fakeStoreToProductDTO = new FakeStoreToProductDTO();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(
                fakeStoreToProductDTO, FakeStoreToProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreToProductDTO> responseExtractor = new HttpMessageConverterExtractor(
                FakeStoreToProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreToProductDTO productDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT,
                requestCallback, responseExtractor);
        return mapFakeStoreToProduct(productDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = Arrays.asList(restTemplate.getForObject
                ("https://fakestoreapi.com/products/categories", String[].class));
        return categories;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        List<FakeStoreToProductDTO> productDTOs = Arrays.asList(restTemplate.getForObject
                ("https://fakestoreapi.com/products/category/" + categoryName, FakeStoreToProductDTO[].class));
        return productDTOs.stream().map(productDTO -> mapFakeStoreToProduct(productDTO))
                .collect(Collectors.toList());
    }
}
