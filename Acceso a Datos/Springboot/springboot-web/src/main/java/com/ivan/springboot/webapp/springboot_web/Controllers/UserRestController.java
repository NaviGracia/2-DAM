package com.ivan.springboot.webapp.springboot_web.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springboot.webapp.springboot_web.Controllers.dto.UserDTO;
import com.ivan.springboot.webapp.springboot_web.models.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/detailsThreeUsers")
    public List<Map<String, Object>> muestra(){
      List<Map<String, Object>> usuarios = new ArrayList<>();
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

      usuarios.add(user1);
      usuarios.add(user2);
      usuarios.add(user3);

      return usuarios;
    }

    @GetMapping("/llamadaDTO")
    public UserDTO userDTO() {
        User user = new User("Ajani", "White");
        UserDTO userDTO = new UserDTO();
        userDTO.setTitle("Planeswalkers");
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());

        return userDTO;
    }

    @GetMapping("/dtoParamsPath/{name}/{lastname}")
    public UserDTO DtoParamsPath(@PathVariable String name, @PathVariable String lastname) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setLastname(lastname);
        return userDTO;
    }
    
    @PostMapping("/crearUsuario")
    public UserDTO crearUsuario(@RequestBody UserDTO userDto) {
        userDto.setName(userDto.getName().toUpperCase());
        return userDto;
    }
    
}
