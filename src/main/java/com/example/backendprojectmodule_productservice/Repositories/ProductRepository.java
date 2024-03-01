package com.example.backendprojectmodule_productservice.Repositories;

import com.example.backendprojectmodule_productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Product save(Product p);

    @Override
    void deleteById(Long aLong);

    @Query("SELECT p FROM Product p WHERE p.category.categoryName = :cname")
    List<Product> findByCategoryName(@Param("cname") String categoryName);
}
