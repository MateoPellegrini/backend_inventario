package com.inventario.inventario.controller;

import com.inventario.inventario.entity.Product;
import com.inventario.inventario.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.getProducts();
    }

    @PostMapping
    public void saveUpdate(@RequestBody Product product){
        productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("productId") Long productId){
        productService.delete(productId);
    }

    @GetMapping("/{productId}")
    public Optional<Product> getById(@PathVariable("productId") Long productId){
        return productService.getProduct(productId);
    }
}
