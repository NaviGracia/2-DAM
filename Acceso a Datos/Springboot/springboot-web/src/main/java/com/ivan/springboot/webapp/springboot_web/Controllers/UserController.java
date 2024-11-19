package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ivan.springboot.webapp.springboot_web.models.User;

@Controller
public class UserController {

    @GetMapping("/llamada")
    public String mostrar(Map<String, Object> model){
      User user = new User("ivan", "Model");
      model.put("title", "Hola Mundo");
      model.put("user", user);
      return "details";
    }
}
