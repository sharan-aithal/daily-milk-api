package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.domain.RiderProfile;
import io.aithal.dailymilkapi.service.RiderProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/riders")
public class RiderController {

    @Autowired
    private RiderProfileService riderProfileService;

    @GetMapping("/{riderId}")
    public ResponseEntity<RiderProfile> getRider ( HttpServletRequest request,
                                                   @PathVariable("riderId") Integer riderId ) {
        RiderProfile rider = riderProfileService.fetchRiderProfile ( riderId );
        return new ResponseEntity<> ( rider, HttpStatus.OK );
    }

    @PostMapping("/{riderId}")
    public ResponseEntity<RiderProfile> updateProfile ( HttpServletRequest request,
                                                        @PathVariable("riderId") Integer riderId,
                                                        @RequestBody Map<String, Object> profileMap ) {
        String name = (String) profileMap.get ( "name" );
        String email = (String) profileMap.get ( "email" );
        String address = (String) profileMap.get ( "address" );
        String city = (String) profileMap.get ( "city" );
        Integer pinCode = (Integer) profileMap.get ( "pin_code" );
        RiderProfile riderProfile = riderProfileService.updateProfile ( riderId, name, email, address, city, pinCode );
        return new ResponseEntity<> ( riderProfile, HttpStatus.OK );
    }

    @GetMapping("/{riderId}/orders")
    public ResponseEntity<Map<String,String>> getOrders ( HttpServletRequest request,
                                             @PathVariable("riderId") Integer riderId ) {
        return new ResponseEntity<> ( Collections.emptyMap (), HttpStatus.OK );
    }
}
