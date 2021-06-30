package io.aithal.dailymilkapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/register")
    public String registerUser ( @RequestBody Map<String, Object> userMap ) {
        String name = (String) userMap.get ( "name" );
        String email = (String) userMap.get ( "email" );
        Long phone = (Long) userMap.get ( "phone" );
        String password = (String) userMap.get ( "password" );
        return name + " " + email + " " + phone + " " + password;
    }
}
