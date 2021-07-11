package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.domain.UserProfile;
import io.aithal.dailymilkapi.exception.DmAuthException;

public interface UserService {

    User validateUser ( String email, String password ) throws DmAuthException;

    User registerUser ( String name, String email, Long phone, String password ) throws DmAuthException;

    UserProfile fetchUserProfile(Integer userId);

    Integer updateUserProfile(Integer userId, String name, String address, String city, Integer pinCode);
}
