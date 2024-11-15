package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {

    @GetMapping("/details")
    public String mostrar(Model model){
      model.addAttribute("title", "Hola Mundo");
      model.addAttribute("name", "Ivan");
      model.addAttribute("surname", "García");
      return "details";
    }
}
