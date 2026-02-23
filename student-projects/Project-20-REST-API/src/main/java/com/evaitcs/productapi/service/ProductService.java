package com.evaitcs.productapi.service;

import com.evaitcs.productapi.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ============================================================================
 * CLASS: ProductService — Business logic layer
 * ============================================================================
 */
@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // TODO 1: Implement getAllProducts()
    // TODO 2: Implement getProductById(Long id) — returns Optional<Product>
    // TODO 3: Implement createProduct(Product product)
    // TODO 4: Implement updateProduct(Long id, Product product) — returns Optional
    // TODO 5: Implement deleteProduct(Long id) — returns boolean
    // TODO 6: Implement searchProducts(String query)
}

