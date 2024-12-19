package com.ivangarcia.springboot.actividadfinal.tienda.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivangarcia.springboot.actividadfinal.tienda.models.Product;


@Controller
public class UserController {
  private List <Product> productos = new ArrayList<>( Arrays.asList(
    new Product(1L, "Duskmourn", 5L, 1L),
    new Product(1L, "Duskmourn", 30L, 2L),
    new Product(1L, "Foundations", 5L, 1L),
    new Product(1L, "Foundations", 30L, 2L)
    )
  );

    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }

    @PostMapping("/nuevoProducto")
    public String processForm(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "price", required = false) Long price,
        @RequestParam(value = "productTypeId", required = false) Long productTypeId,
        Model model) {

        if (name != null && !name.isEmpty() &&
            id != null &&
            price != null &&
            productTypeId != null) {

            Product newProduct = new Product(id, name, price, productTypeId);
            productos.add(newProduct);
        }

        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }
    
}