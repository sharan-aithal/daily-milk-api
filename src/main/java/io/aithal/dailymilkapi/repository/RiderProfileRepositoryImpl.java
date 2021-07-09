package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.RiderProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class RiderProfileRepositoryImpl implements RiderProfileRepository {

    public static final String SQL_FIND_BY_ID = "select rider_id, name, email, address, city, pin_code from dm_rider_profile where rider_id = ?";
    private static final String SQL_UPDATE = "update dm_rider_profile set name = ?, email = ?, address = ?, city = ?, pin_code = ? where rider_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public RiderProfile findById ( Integer riderId ) throws Exception {
        try {
            return jdbcTemplate.queryForObject ( SQL_FIND_BY_ID, riderProfileMapper, riderId );
        } catch (DataAccessException e) {
            e.printStackTrace ();
            throw new Exception ( "unable to find rider profile" );
        } catch (Exception e) {
            e.printStackTrace ();
            throw new Exception ( "unknown error " );
        }
    }

    @Override
    public Integer update ( Integer riderId, String name, String email, String address, String city, Integer pinCode ) throws Exception {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder ();
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_UPDATE, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.setString ( 1, name );
                ps.setString ( 2, email );
                ps.setString ( 3, address );
                ps.setString ( 4, city );
                ps.setInt ( 5, pinCode );
                ps.setInt ( 6, riderId );
                return ps;
            }, keyHolder );
            return riderId;
        } catch (Exception e) {
            e.printStackTrace ();
            throw new Exception ( "unable to update rider profile" );
        }
    }

    private final RowMapper<RiderProfile> riderProfileMapper = ( ( rs, rowNum ) -> {
        return new RiderProfile ( rs.getInt ( "rider_id" ),
                rs.getString ( "name" ),
                rs.getString ( "email" ),
                rs.getString ( "address" ),
                rs.getString ( "city" ),
                rs.getInt ( "pin_code" ) );
    } );

}
