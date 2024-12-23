package com.proj.shop.repository;
import com.proj.shop.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // JPA query to find products with a case-insensitive substring match in 'name'.
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
