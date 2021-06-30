package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser ( @RequestBody Map<String, Object> userMap ) {
        String name = (String) userMap.get ( "name" );
        String email = (String) userMap.get ( "email" );
        Long phone = (Long) userMap.get ( "phone" );
        String password = (String) userMap.get ( "password" );
        User user = userService.registerUser ( name, email, phone, password );
        Map<String, String> map = new HashMap<> ();
        map.put ( "message", "registered successfully" );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser ( @RequestBody Map<String, Object> userMap ) {
        String email = (String) userMap.get ( "email" );
        String password = (String) userMap.get ( "password" );
        User user = userService.validateUser ( email, password );
        Map<String, String> map = new HashMap<> ();
        map.put ( "message", "logged in successfully" );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }
}
