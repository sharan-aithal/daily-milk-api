package io.aithal.dailymilkapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.aithal.dailymilkapi.Constant;
import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.service.RiderService;
import io.aithal.dailymilkapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private RiderService riderService;

    @Autowired
    private UserService userService;

    @PostMapping("/riders/register")
    public ResponseEntity<Map<String, String>> registerRider ( @RequestBody Map<String, Object> riderMap ) {
        String name = (String) riderMap.get ( "name" );
        Long phone = (Long) riderMap.get ( "phone" );
        String password = (String) riderMap.get ( "password" );
        Rider rider = riderService.registerRider ( name, phone, password );
        return new ResponseEntity<> ( generateRiderJWT ( rider ), HttpStatus.OK );
    }

    @PostMapping("/riders/login")
    public ResponseEntity<Map<String, String>> loginRider ( @RequestBody Map<String, Object> riderMap ) {
        Long phone = (Long) riderMap.get ( "phone" );
        String password = (String) riderMap.get ( "password" );
        Rider rider = riderService.validateRider ( phone, password );
        return new ResponseEntity<> ( generateRiderJWT ( rider ), HttpStatus.OK );
    }

    @PostMapping("/users/register")
    public ResponseEntity<Map<String, String>> registerUser ( @RequestBody Map<String, Object> userMap ) {
        String name = (String) userMap.get ( "name" );
        String email = (String) userMap.get ( "email" );
        Long phone = (Long) userMap.get ( "phone" );
        String password = (String) userMap.get ( "password" );
        User user = userService.registerUser ( name, email, phone, password );
        return new ResponseEntity<> ( generateUserJWT ( user ), HttpStatus.OK );
    }

    @PostMapping("/users/login")
    public ResponseEntity<Map<String, String>> loginUser ( @RequestBody Map<String, Object> userMap ) {
        String email = (String) userMap.get ( "email" );
        String password = (String) userMap.get ( "password" );
        User user = userService.validateUser ( email, password );
        return new ResponseEntity<> ( generateUserJWT ( user ), HttpStatus.OK );
    }

    public Map<String, String> generateUserJWT ( User user ) {
        long timestamp = System.currentTimeMillis ();
        String token = JWT.create ()
                .withIssuer ( Constant.API_ISSUER )
                .withIssuedAt ( new Date ( timestamp ) )
                .withExpiresAt ( new Date ( timestamp + Constant.TOKEN_VALIDITY ) )
                .withClaim ( "userId", user.getUserid () )
                .withClaim ( "email", user.getEmail () )
                .withClaim ( "name", user.getName () )
                .sign ( Algorithm.HMAC256 ( Constant.API_SECRET_KEY ) );
        Map<String, String> map = new HashMap<> ();
        map.put ( "token", token );
        map.put ( "user_id", user.getUserid ().toString () );
        return map;
    }

    public Map<String, String> generateRiderJWT ( Rider rider ) {
        long timestamp = System.currentTimeMillis ();
        String token = JWT.create ()
                .withIssuer ( Constant.API_ISSUER )
                .withIssuedAt ( new Date ( timestamp ) )
                .withExpiresAt ( new Date ( timestamp + Constant.TOKEN_VALIDITY ) )
                .withClaim ( "riderId", rider.getRiderId () )
                .withClaim ( "phone", rider.getPhone () )
                .withClaim ( "name", rider.getName () )
                .sign ( Algorithm.HMAC256 ( Constant.API_SECRET_KEY ) );
        Map<String, String> map = new HashMap<> ();
        map.put ( "token", token );
        map.put ( "rider_id", rider.getRiderId ().toString () );
        return map;
    }
}