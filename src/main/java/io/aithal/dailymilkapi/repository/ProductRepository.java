package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Product;
import io.aithal.dailymilkapi.exception.DmBadRequestException;
import io.aithal.dailymilkapi.exception.DmResourceNotFoundException;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll(Integer userId) throws DmResourceNotFoundException;

    Product findById(Integer productId) throws DmResourceNotFoundException;

    Integer create(String name, String description, Double price) throws DmBadRequestException;

    Integer update(Integer productId, Product product) throws DmBadRequestException;

    Integer removeById(Integer productId) throws DmResourceNotFoundException;
}
