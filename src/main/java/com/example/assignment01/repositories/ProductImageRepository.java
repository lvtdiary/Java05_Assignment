package com.example.assignment01.repositories;

import com.example.assignment01.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query("SELECT o FROM ProductImage o where o.product.category.id = ?1")
    ProductImage findByProductId(Integer categoryId);
}