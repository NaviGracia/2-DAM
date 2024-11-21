package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ivan.springboot.webapp.springboot_web.models.User;

@Controller
public class UserController {

    @GetMapping("/llamadaMap")
    public String mostrar(Map<String, Object> model){
      User user = new User("ivan", "Model");
      model.put("title", "Hola Mundo");
      model.put("user", user);
      return "details";
    }

    @GetMapping("/listaUsuarios")
    public String listarUsuarios(Model model){
      List <User> users = Arrays.asList(
        new User("Ajani", "Green | White"),
        new User("Liliana", "Black"),
        new User("Chandra", "Red")
      );
      
      model.addAttribute("title", "Hola Jugadores de Magic The Gathering");
      model.addAttribute("users", users);
      return "usersList";
    }
}