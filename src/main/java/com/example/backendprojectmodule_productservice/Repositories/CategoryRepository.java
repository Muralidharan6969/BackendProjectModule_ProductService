package com.example.backendprojectmodule_productservice.Repositories;

import com.example.backendprojectmodule_productservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Optional<Category> findById(Long aLong);
    Optional<Category> findByCategoryName(String categoryName);

    List<Category> findAll();
}
