package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.User;
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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl ( JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer create ( String name, String email, Long phone, String password ) throws DmAuthException {
        String hashedPassword = BCrypt.hashpw ( password, BCrypt.gensalt (10) );
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder ();
            jdbcTemplate.update ( connection -> {
                PreparedStatement ps = connection.prepareStatement ( SQL_CREATE, Statement.RETURN_GENERATED_KEYS );
                ps.setString ( 1, name );
                ps.setString ( 2, email );
                ps.setLong ( 3, phone );
                ps.setString ( 4, hashedPassword );
                return ps;
            }, keyHolder );
            return (Integer) keyHolder.getKeys ().get ( "user_id" );
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

    private final RowMapper<User> userRowMapper = ( ( rs, rowNum ) -> {
        return new User ( rs.getInt ( "user_id" ),
                rs.getString ( "name" ),
                rs.getString ( "email" ),
                rs.getLong ( "phone" ),
                rs.getString ( "password" ) );
    } );
}