package com.ig.springboot.id.app.springboot_id2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ig.springboot.id.app.springboot_id2.models.Product;
import com.ig.springboot.id.app.springboot_id2.repositories.ProductRepository;
import com.ig.springboot.id.app.springboot_id2.repositories.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepositoryImpl repository){
        this.repository = repository;
    }
    
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
