package com.ig.springboot.id.app.springboot_id2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ig.springboot.id.app.springboot_id2.models.Product;
import com.ig.springboot.id.app.springboot_id2.services.ProductService;
import com.ig.springboot.id.app.springboot_id2.services.ProductServiceImpl;


@RestController
@RequestMapping("/api")
public class SomeController {
    private ProductService service = new ProductServiceImpl();

    @GetMapping("/productos")
    public List<Product> list() {
        return service.findAll();
    }
    
    @GetMapping("/productos/{id}")
    public Product show (Long id) {
        return service.findById(id);
    }


}
