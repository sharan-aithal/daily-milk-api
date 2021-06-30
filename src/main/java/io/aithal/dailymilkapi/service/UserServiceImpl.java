package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.User;
import io.aithal.dailymilkapi.exception.DmAuthException;
import io.aithal.dailymilkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl ( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }


    @Override
    public User validateUser ( String email, String password ) throws DmAuthException {
        return null;
    }

    @Override
    public User registerUser ( String name, String email, Long phone, String password ) throws DmAuthException {
        Pattern pattern = Pattern.compile ( "^(.+)@(.+)$" );
        if (email != null)
            email = email.toLowerCase ();
        if (!pattern.matcher ( email ).matches ())
            throw new DmAuthException ( "Invalid email format" );
        Integer count = userRepository.getCountByEmail ( email );
        if (count > 0) throw new DmAuthException ( "Email already in use" );
        Integer userId = userRepository.create ( name, email, phone, password );
        return userRepository.findById ( userId );
    }
}
