package com.ivangarcia.springboot.actividadfinal.tienda.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ivangarcia.springboot.actividadfinal.tienda.models.Product;
import com.ivangarcia.springboot.actividadfinal.tienda.models.ProductType;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private List<Product> data;

    public ProductRepositoryImpl(List<ProductType> categorias) {
        this.data = new ArrayList<>(Arrays.asList(
            new Product(1L, "Duskmourn", 5L, 1L, categorias),
            new Product(2L, "Duskmourn", 30L, 2L, categorias),
            new Product(3L, "Foundations", 5L, 1L, categorias),
            new Product(4L, "Foundations", 30L, 2L, categorias)
        ));
    }

    public List<Product> findAll() {
        return data;
    }

    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
