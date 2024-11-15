package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.HashMap;
import java.util.Map;
import 

import org.springframework.web.bind.annotation.GetMapping;

public class UserRestController {

    @GetMapping("/detailsMap")
    public Map<String, Object> muestra(){
      Map<String, Object> body = new HashMap<>();
      body.put("title", "Hola Mundo");
      body.put("name", "Ivan");
      body.put("surname", "García");
      return body;
    }

    /*
    
    @GetMapping("/details")
    public String mostrar(Model model){
      model.addAttribute("title", "Hola Mundo");
      model.addAttribute("name", "Ivan");
      model.addAttribute("surname", "García");
      return "details";
    } */
}
