package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.exception.DmAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class RiderRepositoryImpl implements RiderRepository {

    private static final String SQL_CREATE = "insert into dm_rider(rider_id, name, phone, password) " +
            "values (nextval('dm_rider_seq'), ?, ?, ?)";
    private static final String SQL_COUNT_BY_PHONE = "select count(*) from dm_rider where phone = ?";
    private static final String SQL_FIND_BY_ID = "select rider_id, name, phone, password from dm_rider where rider_id = ?";
    private static final String SQL_FIND_BY_PHONE = "select rider_id, name, phone, password from dm_rider where phone = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RiderRepositoryImpl ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create ( String name, Long phone, String password ) throws DmAuthException {
        String hashedPassword = BCrypt.hashpw ( password, BCrypt.gensalt (10) );
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder ();
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.setString ( 1, name );
                ps.setLong ( 2, phone );
                ps.setString ( 3, hashedPassword );
                return ps;
            }, keyHolder );
            return (Integer) keyHolder.getKeys ().get ( "rider_id" );
        } catch (Exception e) {
            throw new DmAuthException ( "Invalid details, unable to create account" );
        }
    }

    @Override
    public Integer getCountByPhone ( Long phone ) {
        return jdbcTemplate.queryForObject ( SQL_COUNT_BY_PHONE, Integer.class, phone );
    }

    @Override
    public Rider findById ( Integer riderId ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_BY_ID, rowMapper, riderId );
    }

    @Override
    public Rider findByPhoneAndPassword ( Long phone, String password ) throws DmAuthException {
        try {
            Rider rider = jdbcTemplate.queryForObject ( SQL_FIND_BY_PHONE, rowMapper, phone );
            if (!BCrypt.checkpw ( password, rider.getPassword () ))
                throw new DmAuthException ( "invalid phone and password" );
            return rider;
        } catch (Exception e) {
            throw new DmAuthException ( "invalid phone and password" );
        }
    }

    private final RowMapper<Rider> rowMapper = ( ( rs, rowNum ) -> {
        return new Rider ( rs.getInt ( "rider_id" ),
                rs.getString ( "name" ),
                rs.getLong ( "phone" ),
                rs.getString ( "password" ) );
    } );
}
