package com.ivangarcia.springboot.actividadfinal.tienda.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivangarcia.springboot.actividadfinal.tienda.models.Product;
import com.ivangarcia.springboot.actividadfinal.tienda.models.ProductType;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductRepositoryImpl;
import com.ivangarcia.springboot.actividadfinal.tienda.repositories.ProductTypeRepositoryImpl;
import com.ivangarcia.springboot.actividadfinal.tienda.services.ImplServicioProducto;

@Controller
public class UserController {
    ProductTypeRepositoryImpl ptr = new ProductTypeRepositoryImpl();
    ProductRepositoryImpl pr = new ProductRepositoryImpl(ptr.findAll());
    ImplServicioProducto sp = new ImplServicioProducto(pr, ptr);

    List<ProductType> categorias = ptr.findAll();
    List<Product> productos = pr.findAll();

    @GetMapping("/listaCategorias")
    public String listarTipos(Model model) {
        model.addAttribute("title", "Lista de Categorias");
        model.addAttribute("categorias", categorias);
        return "listaTiposProductos";
    }

    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }

    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model model) {
        model.addAttribute("categorias", categorias);
        return "nuevoProducto";
    }
    
    @PostMapping("/nuevoProducto")
    public String agregarProducto(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "precio", required = false) Long precio,
        @RequestParam(value = "idCategoria", required = false) Long idCategoria,
        Model model) {

        productos = sp.create(id, nombre, precio, idCategoria).findAll();

        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(
        @PathVariable Long id,
        Model model) {
        
        productos = sp.delete(id).findAll();

        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }

    @GetMapping("/modificarProducto/{id}")
    public String mostrarProducto(
        @PathVariable Long id,
        Model model) {
        if (id != null && pr.findAll() != null) {
            for (Product pr : pr.findAll()) {
                if (pr.getId().equals(id)) {
                    model.addAttribute("producto", pr);
                }
            }
        }
        model.addAttribute("categorias", categorias);
        return "modificarProducto";
    }
    

    @PostMapping("/modificarProducto/{id}")
    public String modificarProducto(
        @PathVariable Long id,
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "precio", required = false) Long precio,
        @RequestParam(value = "idCategoria", required = false) Long idCategoria,
        Model model) {

        productos = sp.update(id, nombre, precio, idCategoria).findAll();

        model.addAttribute("title", "Lista de Productos");
        model.addAttribute("productos", productos);
        return "listaProductos";
    }
}