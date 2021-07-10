package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.RiderProfile;

public interface RiderProfileService {

    RiderProfile fetchRiderProfile ( Integer riderId );

    RiderProfile updateProfile ( Integer riderId, String name, String email, String address, String city, Integer pinCode );
}
