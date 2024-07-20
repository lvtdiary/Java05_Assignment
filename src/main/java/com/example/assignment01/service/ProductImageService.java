package com.example.assignment01.service;

import com.example.assignment01.model.ProductImage;
import com.example.assignment01.repository.ProductImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public List<ProductImage> findAll() {
        return this.productImageRepository.findAll();
    }

    public ProductImage save(ProductImage productImage) {
        return this.productImageRepository.save(productImage);
    }

    public ProductImage findByProductId(Integer categoryId) {
        return this.productImageRepository.findByProductId(categoryId);
    }
}
