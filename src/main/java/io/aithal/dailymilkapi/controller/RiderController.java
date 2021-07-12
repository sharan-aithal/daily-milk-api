package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.RiderProfile;
import io.aithal.dailymilkapi.service.RiderOrderService;
import io.aithal.dailymilkapi.service.RiderProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/riders")
public class RiderController {

    @Autowired
    private RiderProfileService riderProfileService;

    @Autowired
    private RiderOrderService riderOrderService;

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
    public ResponseEntity<List<Order>> listOrders ( HttpServletRequest request,
                                                    @PathVariable("riderId") Integer riderId ) {
        List<Order> order = riderOrderService.fetchAllOrder ( riderId );
        return new ResponseEntity<> ( order, HttpStatus.OK );
    }

    @GetMapping("/{riderId}/orders/{orderId}")
    public ResponseEntity<Order> getOrder ( HttpServletRequest request,
                                            @PathVariable("riderId") Integer riderId,
                                            @PathVariable("orderId") Long orderId ) {
        Order order = riderOrderService.fetchOrder ( orderId );
        return new ResponseEntity<> ( order, HttpStatus.OK );
    }
}
