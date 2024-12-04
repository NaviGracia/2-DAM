package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ivan.springboot.webapp.springboot_web.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
  private List <User> users = new ArrayList<>( Arrays.asList(
    new User("Ajani", "Green | White", "ajani@gmail.com"),
    new User("Liliana", "Black", "liliana@gmail.com"),
    new User("Chandra", "Red"),
    new User("Jace", "Blue")
    )
  );

/* 
  @GetMapping("/llamadaMap")
  public String mostrar(Map<String, Object> model){
    User user = new User("ivan", "Model");
    model.put("title", "Hola Mundo");
    model.put("user", user);
    return "details";
  }
*/
    @GetMapping("/listaUsuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("title", "Lista de Usuarios");
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/listaUsuarios")
    public String processForm(
        @RequestParam(value = "name", required = false) String nombre,
        @RequestParam(value = "lastname", required = false) String apellidos,
        @RequestParam(value = "email", required = false) String email,
        Model model) {

        if (nombre != null && !nombre.isEmpty() &&
            apellidos != null && !apellidos.isEmpty() &&
            email != null && !email.isEmpty()) {

            User newUser = new User(nombre, apellidos, email);
            users.add(newUser);
        }

        model.addAttribute("title", "Lista de Usuarios");
        model.addAttribute("users", users);
        return "userList";
    }
    
}