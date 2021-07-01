package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.exception.DmAuthException;

public interface RiderService {

    Rider validateRider ( Long phone, String password ) throws DmAuthException;

    Rider registerRider ( String name, Long phone, String password ) throws DmAuthException;
}
