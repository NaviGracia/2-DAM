package com.ivangarcia.springboot.actividadfinal.tienda.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ivangarcia.springboot.actividadfinal.tienda.models.ProductType;

@Repository
public class ProductTypeRepositoryImpl implements ProductTypeRepository{
    private List<ProductType> data;

    public ProductTypeRepositoryImpl() {
        this.data = new ArrayList<>(Arrays.asList(
            new ProductType(1L, "Play Booster", "Sobre que contiene 15 cartas"),
            new ProductType(2L, "Bundle", "Caja Temática del set que contiene 9 Play Boosters, 1 dado d20, 1 Sobre de Coleccionista de Muestra y 40 cartas de tierra (20 Full-Art Foil y 20 Full-Art)")
        ));
    }

    public List<ProductType> findAll() {
        return data;
    }

    public ProductType findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
