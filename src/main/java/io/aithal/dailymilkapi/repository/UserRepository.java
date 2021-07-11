package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.domain.UserProfile;
import io.aithal.dailymilkapi.exception.DmAuthException;

public interface UserRepository {

    Integer create ( String name, String email, Long phone, String password ) throws DmAuthException;

    User findByEmailAndPassword ( String email, String password ) throws DmAuthException;

    Integer getCountByEmail ( String email );

    User findById ( Integer userId );

    UserProfile findByUser ( User user );

    Integer updateProfile ( Integer userId, String name, String address, String city, Integer pinCode );
}
