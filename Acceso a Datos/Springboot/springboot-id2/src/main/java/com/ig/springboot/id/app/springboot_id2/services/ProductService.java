package com.ig.springboot.id.app.springboot_id2.services;

import java.util.List;

import com.ig.springboot.id.app.springboot_id2.models.Product;

public interface  ProductService {
    List<Product> findAll();
    Product findById(Long id);
}
