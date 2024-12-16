package com.ig.springboot.id.app.springboot_id2.repositories;

import java.util.List;

import com.ig.springboot.id.app.springboot_id2.models.Product;

public interface  ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
