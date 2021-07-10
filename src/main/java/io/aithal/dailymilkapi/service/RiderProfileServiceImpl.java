package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.RiderProfile;
import io.aithal.dailymilkapi.repository.RiderProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RiderProfileServiceImpl implements RiderProfileService {

    @Autowired
    RiderProfileRepository riderProfileRepository;

    @Override
    public RiderProfile fetchRiderProfile ( Integer riderId ) {
        try {
            return riderProfileRepository.findById ( riderId );
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }

    @Override
    public RiderProfile updateProfile ( Integer riderId, String name, String email, String address, String city, Integer pinCode ) {
        try {
            Integer updatedRiderId = riderProfileRepository.update ( riderId, name,email,address,city,pinCode );
            return riderProfileRepository.findById ( updatedRiderId );
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return null;
    }
}
