package com.proj.shop.controller;

import com.proj.shop.product.Product;
import com.proj.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Map<String, String> filter
    ) {
        // Handle sorting parameters
        String sortParam = filter.get("sort");
        String sortBy = "id"; // Default sorting field
        String order = "asc"; // Default sorting direction

        if (sortParam != null) {
            try {
                // Parse the sort JSON parameter
                String[] sortArray = sortParam.replace("[", "").replace("]", "").replace("\"", "").split(",");
                sortBy = sortArray[0]; // Field to sort by
                order = sortArray[1]; // Sorting direction
            } catch (Exception e) {
                System.out.println("Error parsing sort parameter: " + e.getMessage());
            }
        }

        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Handle filtering by name
        String nameFilter = filter.get("filterByName");
        Page<Product> productPage;
        if (nameFilter != null) {
            productPage = productRepository.findByNameContainingIgnoreCase(nameFilter, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        // Set HTTP headers for pagination
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "products " + page * size + "-" + (page * size + size - 1) + "/" + productPage.getTotalElements());

        return new ResponseEntity<>(productPage.getContent(), headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product fields
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock_quantity(productDetails.getStock_quantity());
        product.setImage_path(productDetails.getImage_path());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
