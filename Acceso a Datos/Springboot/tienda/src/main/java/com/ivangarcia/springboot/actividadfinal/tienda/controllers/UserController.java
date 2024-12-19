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
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/nuevoProducto")
    public String nuevoProducto() {
        return "nuevoProducto";
    }
    
    @PostMapping("/nuevoProducto")
    public String processForm(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "precio", required = false) Long precio,
        @RequestParam(value = "idCategoria", required = false) Long idCategoria,
        Model model) {

        if (nombre != null && !nombre.isEmpty() &&
            id != null &&
            precio != null &&
            idCategoria != null) {

            Product newProduct = new Product(id, nombre, precio, idCategoria);
            productos.add(newProduct);
        }

        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }
    
}