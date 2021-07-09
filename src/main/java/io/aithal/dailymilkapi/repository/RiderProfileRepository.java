package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.RiderProfile;

public interface RiderProfileRepository {

    RiderProfile findById ( Integer riderId ) throws Exception;

    Integer update ( Integer riderId, String name, String email, String address, String city, Integer pinCode ) throws Exception;
}
