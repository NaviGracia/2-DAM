package com.ivangarcia.springboot.actividadfinal.tienda.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ivangarcia.springboot.actividadfinal.tienda.models.*;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductRepository;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductRepositoryImpl;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductTypeRepositoryImpl;

@Service
public class ImplServicioProducto implements ServicioProducto{
    private ProductTypeRepositoryImpl ptr; 
    private ProductRepository pr;

    public ImplServicioProducto(ProductRepositoryImpl repository, ProductTypeRepositoryImpl ptr){
        this.pr = repository;
        this.ptr = ptr;
    }
    
    public List<Product> findAll(){
        return pr.findAll();
    }

    public Product findById(Long id){
        return pr.findById(id);
    }

    @Override
    public ProductRepository create(Long id, String nombre, Long precio, Long idCategoria) {
        if (nombre != null && !nombre.isEmpty() &&
            id != null &&
            precio != null &&
            idCategoria != null) {

            pr.findAll().add(new Product(id, nombre, precio, idCategoria, ptr.findAll()));
            Collections.sort(pr.findAll(), Comparator.comparing(Product::getId));
        }

        return pr;
    }

    @Override
    public ProductRepository update(Long id, String nombre, Long precio, Long idCategoria) {
        if (nombre != null && !nombre.isEmpty() &&
            id != null &&
            precio != null &&
            idCategoria != null) {

            Product productoModificado = null;
            for (Product pr : pr.findAll()) {
                if (pr.getId().equals(id)) {
                    productoModificado = pr;
                    break;
                }
            }

            if (productoModificado != null) {
                pr.findAll().remove(productoModificado);
                pr.findAll().add(new Product(id, nombre, precio, idCategoria, ptr.findAll()));
                Collections.sort(pr.findAll(), Comparator.comparing(Product::getId));
            }
        }
        return pr;
    }

    @Override
    public ProductRepository delete(Long id) {
        if (id != null && pr.findAll() != null) {
            pr.findAll().removeIf(producto -> producto.getId().equals(id));
        }
        return pr;
    }
}
