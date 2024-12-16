package com.ig.springboot.id.app.springboot_id2.services;

import java.util.List;
import java.util.stream.Collectors;
import com.ig.springboot.id.app.springboot_id2.models.*;

import com.ig.springboot.id.app.springboot_id2.repositories.ProductRepository;

public class ProductService {
    private ProductRepository repository = new ProductRepository();
    
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceImp = p.getPrice()*1.21d;
            Product newPr = (Product) p.clone();
            //p.setPrice(priceImp.longValue());
            return newPr;
            
        }).collect(Collectors.toList());
    }

    public Product findById(Long id){
        return repository.findById(id);
    }
}