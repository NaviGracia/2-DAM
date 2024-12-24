package com.ivangarcia.springboot.actividadfinal.tienda.repositories;

import java.util.List;

import com.ivangarcia.springboot.actividadfinal.tienda.models.ProductType;

public interface  ProductTypeRepository {
    List<ProductType> findAll();
    ProductType findById(Long id);
}
