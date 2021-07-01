package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Rider;
import io.aithal.dailymilkapi.exception.DmAuthException;
import io.aithal.dailymilkapi.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;

    @Autowired
    public RiderServiceImpl ( RiderRepository riderRepository ) {
        this.riderRepository = riderRepository;
    }

    @Override
    public Rider validateRider ( Long phone, String password ) throws DmAuthException {
        return riderRepository.findByPhoneAndPassword ( phone, password );
    }

    @Override
    public Rider registerRider ( String name, Long phone, String password ) throws DmAuthException {
        if (!( phone > 1000000000L && phone <= 9999999999L ))
            throw new DmAuthException ( "Invalid phone" );
        Integer count = riderRepository.getCountByPhone ( phone );
        if (count > 0)
            throw new DmAuthException ( "phone already taken" );
        Integer riderId = riderRepository.create ( name, phone, password );
        return riderRepository.findById ( riderId );
    }
}
