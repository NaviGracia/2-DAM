package com.ig.springboot.id.app.springboot_id2.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ig.springboot.id.app.springboot_id2.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private List<Product> data;

    public ProductRepositoryImpl() {
    
        this.data = Arrays.asList(
                new Product(1l, "Ajani", 200L));
    }

    public List<Product> findAll() {
        return data;
    }

    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
