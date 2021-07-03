package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Product;
import io.aithal.dailymilkapi.exception.DmBadRequestException;
import io.aithal.dailymilkapi.exception.DmResourceNotFoundException;
import io.aithal.dailymilkapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProducts ( Integer userId ) {
        return null;
    }

    @Override
    public Product fetchProductById ( Integer productId ) throws DmResourceNotFoundException {
        return productRepository.findById ( productId );
    }

    @Override
    public Product addProduct ( Integer userId, String name, String description, Double price ) throws DmBadRequestException {
        Integer productId = productRepository.create ( name, description, price );
        try {
            return productRepository.findById ( productId );
        } catch (Exception e) {
            throw new DmBadRequestException ( "invalid resource found" );
        }
    }

    @Override
    public Integer updateProductPrice ( Integer productId, Double price ) throws DmResourceNotFoundException {
        return null;
    }

    @Override
    public Integer removeProduct ( Integer productId ) throws DmResourceNotFoundException {
        return null;
    }
}
