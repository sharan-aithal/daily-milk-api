package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.domain.UserProfile;
import io.aithal.dailymilkapi.exception.DmAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "insert into dm_user(user_id, name, email, phone, password) " +
            "values (nextval('dm_user_seq'), ?, ?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "select count(*) from dm_user where email = ?";
    private static final String SQL_FIND_BY_ID = "select user_id, name, email, phone, password " +
            "from dm_user where user_id = ?";
    private static final String SQL_FIND_BY_EMAIL = "select user_id, name, email, phone, password from dm_user where email = ?";
    private static final String SQL_INSERT_TO_PROFILE = "insert into dm_user_profile(user_id, name) values (?, ?)";
    private static final String SQL_FIND_BY_USER_ID = "select user_id, name, address, city, pin_code from dm_user_profile where user_id = ?";
    private static final String SQL_UPDATE_PROFILE = "update dm_user_profile set name = ?, address = ?, city = ?, pin_code = ? where user_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create ( String name, String email, Long phone, String password ) throws DmAuthException {
        String hashedPassword = BCrypt.hashpw ( password, BCrypt.gensalt ( 10 ) );
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder ();
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.setString ( 1, name );
                ps.setString ( 2, email );
                ps.setLong ( 3, phone );
                ps.setString ( 4, hashedPassword );
                return ps;
            }, keyHolder );
            Integer userId = (Integer) keyHolder.getKeys ().get ( "user_id" );
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_INSERT_TO_PROFILE, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.setInt ( 1, userId );
                ps.setString ( 2, name );
                return ps;
            }, keyHolder );
            return userId;
        } catch (Exception e) {
            throw new DmAuthException ( "Invalid details, Unable to create account" );
        }
    }

    @Override
    public User findByEmailAndPassword ( String email, String password ) throws DmAuthException {
        try {
            User user = jdbcTemplate.queryForObject ( SQL_FIND_BY_EMAIL, userRowMapper, email );
            if (!BCrypt.checkpw ( password, user.getPassword () ))
                throw new DmAuthException ( "invalid email and password" );
            return user;
        } catch (Exception e) {
            throw new DmAuthException ( "invalid email and password" );
        }
    }

    @Override
    public Integer getCountByEmail ( String email ) {
        return jdbcTemplate.queryForObject ( SQL_COUNT_BY_EMAIL, Integer.class, email );
    }

    @Override
    public User findById ( Integer userId ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_BY_ID, userRowMapper, userId );
    }

    @Override
    public UserProfile findByUser ( User user ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_BY_USER_ID, userProfileRowMapper, user.getUserid () );
    }

    @Override
    public Integer updateProfile ( Integer userId, String name, String address, String city, Integer pinCode ) {
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        jdbcTemplate.update ( connection -> {
            PreparedStatement ps = connection.prepareStatement (SQL_UPDATE_PROFILE, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString ( 1, name );
            ps.setString ( 2, address );
            ps.setString ( 3, city );
            ps.setInt (4, pinCode  );
            ps.setInt ( 5, userId );
            return ps;
        }, keyHolder );
        return (Integer) keyHolder.getKeys ().get ( "user_id" );
    }

    private final RowMapper<User> userRowMapper = ( ( rs, rowNum ) -> {
        return new User ( rs.getInt ( "user_id" ),
                rs.getString ( "name" ),
                rs.getString ( "email" ),
                rs.getLong ( "phone" ),
                rs.getString ( "password" ) );
    } );

    private RowMapper<UserProfile> userProfileRowMapper = ( ( rs, rowNum ) -> {
        return new UserProfile ( rs.getInt ( "user_id" ),
                rs.getString ( "name" ),
                rs.getString ( "address" ),
                rs.getString ( "city" ),
                rs.getInt ( "pin_code" ) );
    } );
}
