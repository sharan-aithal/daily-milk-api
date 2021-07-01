package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.exception.DmAuthException;

public interface RiderRepository {
    Integer create ( String name, Long phone, String password ) throws DmAuthException;

    Integer getCountByPhone ( Long phone );

    Rider findById ( Integer riderId );

    Rider findByPhoneAndPassword ( Long phone, String password ) throws DmAuthException;
}
