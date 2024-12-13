package com.ig.springboot.id.app.springboot_id2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ig.springboot.id.app.springboot_id2.models.Product;
import com.ig.springboot.id.app.springboot_id2.repositories.ProductRepository;
import com.ig.springboot.id.app.springboot_id2.services.ProductService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class SomeController {
    private ProductService service = new ProductService();

    @GetMapping("/productos")
    public List<Product> list() {
        return service.findAll();
    }
    
    @GetMapping("/productos/{id}")
    public Product show (Long id) {
        return service.findById(id);
    }


}
