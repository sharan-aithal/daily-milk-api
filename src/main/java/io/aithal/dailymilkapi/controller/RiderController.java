package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/riders")
public class RiderController {

    private final RiderService riderService;

    @Autowired
    public RiderController ( RiderService riderService ) {
        this.riderService = riderService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerRider ( @RequestBody Map<String, Object> riderMap ) {
        String name = (String) riderMap.get ( "name" );
        Long phone = (Long) riderMap.get ( "phone" );
        String password = (String) riderMap.get ( "password" );
        Rider rider = riderService.registerRider ( name, phone, password );
        Map<String, String> map = new HashMap<> ();
        map.put ( "message", "registered successfully" );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginRider ( @RequestBody Map<String, Object> riderMap ) {
        Long phone = (Long) riderMap.get ( "phone" );
        String password = (String) riderMap.get ( "password" );
        Rider rider = riderService.validateRider ( phone, password );
        Map<String, String> map = new HashMap<> ();
        map.put ( "message", "logged in successfully" );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }
}
