package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Product;
import io.aithal.dailymilkapi.exception.DmBadRequestException;
import io.aithal.dailymilkapi.exception.DmResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public static final String SQL_CREATE = "insert into dm_product(product_id, name, description, price) " +
            "values(nextval('dm_product_seq'), ?, ?, ?)";
    public static final String SQL_FIND_BY_ID = "select product_id, name, description, price from dm_product " +
            "where product_id = ?";
    public static final String SQL_FIND_ALL = "select product_id, name, description, price from dm_product " +
            "order by product_id desc";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll ( Integer userId ) throws DmResourceNotFoundException {
        return null;
    }

    @Override
    public Product findById ( Integer productId ) throws DmResourceNotFoundException {
        return jdbcTemplate.queryForObject (SQL_FIND_BY_ID, productRowMapper, productId);
    }

    @Override
    public Integer create ( String name, String description, Double price ) throws DmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder ();
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.setString ( 1, name );
                ps.setString ( 2, description );
                ps.setDouble ( 3, price );
                return ps;
            }, keyHolder );
            return (Integer) keyHolder.getKeys ().get ( "product_id" );
        } catch (Exception e) {
            throw new DmBadRequestException ( "invalid request to add product" );
        }
    }

    @Override
    public Integer update ( Integer productId, Product product ) throws DmBadRequestException {
        return null;
    }

    @Override
    public Integer removeById ( Integer productId ) throws DmResourceNotFoundException {
        return null;
    }

    private final RowMapper<Product> productRowMapper = (( rs, rowNum) -> {
        return  new Product ( rs.getInt ( "product_id" ),
                rs.getString ( "name" ),
                rs.getString ( "description" ),
                rs.getDouble ( "price" ));
    });
}
