package com.example.assignment01.controllers;

import com.example.assignment01.dtos.ProductDTO;
import com.example.assignment01.models.Product;
import com.example.assignment01.responses.ProductResponse;
import com.example.assignment01.services.ProductImageService;
import com.example.assignment01.services.product.ProductRestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductRestService productService;
    private final ProductImageService productImageService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessage = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok("Successfully created a product " + productService.createProduct(productDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam("page") int page) {
        int size = 12;
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Product> products = productService.getAllProducts(pageRequest).getContent();
        //Convert sang productresponse

        List<ProductResponse> productResponses = productService.productResponseList(products);

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
        try {
            ProductResponse productResponse = productService.getProduct(id);
            return ResponseEntity.ok(productResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Successfully deleted a product with id: " + id);
    }

}
