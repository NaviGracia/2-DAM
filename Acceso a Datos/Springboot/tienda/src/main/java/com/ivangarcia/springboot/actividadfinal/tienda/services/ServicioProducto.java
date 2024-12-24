package com.ivangarcia.springboot.actividadfinal.tienda.services;

import java.util.List;

import com.ivangarcia.springboot.actividadfinal.tienda.models.*;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductRepository;

public interface  ServicioProducto {
    List<Product> findAll();
    Product findById(Long id);
    ProductRepository create(Long id, String nombre, Long precio, Long idCategoria);
    ProductRepository update(Long id, String nombre, Long precio, Long idCategoria);
    ProductRepository delete(Long id);
}
