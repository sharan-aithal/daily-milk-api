package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("{userId}")
    public ResponseEntity<Map<String, String>> updateUser ( @PathVariable("userId") Integer userId,
                                                            @RequestBody Map<String, Object> profileMap ) {
        String name = (String) profileMap.get ( "name" );
        String address = (String) profileMap.get ( "address" );
        String city = (String) profileMap.get ( "city" );
        Integer pinCode = (Integer) profileMap.get ( "pin_code" );
        userId = userService.updateUserProfile ( userId, name, address, city, pinCode );
        Map<String, String> map = new HashMap<> ();
        map.put ( "user_id", userId.toString () );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }
}
