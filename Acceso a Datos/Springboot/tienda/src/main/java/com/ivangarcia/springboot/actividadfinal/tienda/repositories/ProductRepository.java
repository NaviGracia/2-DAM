package com.ivangarcia.springboot.actividadfinal.tienda.repositories;

import java.util.List;

import com.ivangarcia.springboot.actividadfinal.tienda.models.Product;

public interface  ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
}
