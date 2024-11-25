package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivan.springboot.webapp.springboot_web.models.User;
import com.ivan.springboot.webapp.springboot_web.models.UserDTO;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserRestController {

    @GetMapping("/detailsThreeUsers")
    @RequestMapping("/api")
    public List<Map<String, Object>> muestra(){
      List<Map<String, Object>> body = new ArrayList<>();
      Map<String, Object> user1 = new HashMap<>();
      user1.put("title", "Hola Mundo");
      user1.put("name", "Ivan");
      user1.put("surname", "García");
      
      Map<String, Object> user2 = new HashMap<>();
      user2.put("title", "Hola");
      user2.put("name", "Zimone");
      user2.put("surname", "Garza");

      Map<String, Object> user3 = new HashMap<>();
      user3.put("title", "Mundo");
      user3.put("name", "Jinx");
      user3.put("surname", "Jayce");

      body.add(user1);
      body.add(user2);
      body.add(user3);

      return body;
    }

    @GetMapping("llamadaDTO")
    public String userDTO() {
        User user = new User("Ajani", "White");
        UserDTO userDto = new UserDTO("Planeswalkers", user)
        userDto = 
    }
    
}
