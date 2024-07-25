package com.project.assignment.repositories;

import com.project.assignment.models.Product;
import com.project.assignment.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    @Query("SELECT o FROM ProductImage o where o.product.category.id = ?1")
    ProductImage findByProductId(Integer categoryId);

    List<ProductImage> findByProduct(Product product);
}