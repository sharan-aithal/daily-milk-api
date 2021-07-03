package io.aithal.dailymilkapi.controller;

import io.aithal.dailymilkapi.domain.Product;
import io.aithal.dailymilkapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<Map<String, String>> getAllProducts ( HttpServletRequest request ) {
        Integer userId = (Integer) request.getAttribute ( "userId" );
        Map<String, String> map = new HashMap<> ();
        map.put ( "message", "authenticated " + userId );
        return new ResponseEntity<> ( map, HttpStatus.OK );
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct ( HttpServletRequest request,
                                                            @RequestBody Map<String, Object> productMap ) {
        Integer userId = (Integer) request.getAttribute ( "userId" );
        String name = (String) productMap.get ( "name" );
        String description = (String) productMap.get ( "description" );
        Double price =(Double) productMap.get ("price");
        Product product = productService.addProduct (userId, name, description, price );
        return new ResponseEntity<> ( product, HttpStatus.CREATED );
    }
}
