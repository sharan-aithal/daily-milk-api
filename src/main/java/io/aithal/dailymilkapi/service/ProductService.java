package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Product;
import io.aithal.dailymilkapi.exception.DmBadRequestException;
import io.aithal.dailymilkapi.exception.DmResourceNotFoundException;

import java.util.List;

public interface ProductService {

    List<Product> fetchAllProducts ( Integer userId );

    Product fetchProductById ( Integer productId ) throws DmResourceNotFoundException;

    Product addProduct ( Integer userId, String name, String description, Double price ) throws DmBadRequestException;

    Integer updateProductPrice(Integer productId, Double price) throws DmResourceNotFoundException;

    Integer removeProduct(Integer productId) throws DmResourceNotFoundException;
}
