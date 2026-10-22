package com.catalog.web.controllers;

import com.catalog.domain.PagedResult;
import com.catalog.domain.Product;
import com.catalog.domain.ProductNotFoundException;
import com.catalog.domain.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        log.info("Fetching products for page: {}", pageNo);
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        // sleep();  // testing circutbreaker
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }

    // this is just for demo purposes. to trigger circut breaker and retries on 6sec response
    void sleep() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
