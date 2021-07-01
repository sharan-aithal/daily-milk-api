package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.exception.DmAuthException;

public interface UserService {

    User validateUser ( String email, String password ) throws DmAuthException;

    User registerUser ( String name, String email, Long phone, String password ) throws DmAuthException;
}
