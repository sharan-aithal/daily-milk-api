package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.OrderItem;
import io.aithal.dailymilkapi.repository.RiderOrderRepository;
import io.aithal.dailymilkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RiderOrderServiceImpl implements RiderOrderService {

    @Autowired
    private RiderOrderRepository riderOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> fetchAllOrder ( Integer riderId ) {
        return riderOrderRepository.findAllOrder (riderId);
    }

    @Override
    public Order fetchOrder ( Long orderId ) {
        return riderOrderRepository.findOrderById ( orderId );
    }

    @Override
    public List<Order> fetchAllOrderByUser ( Integer userId ) {
        return riderOrderRepository.findOrderByUserId ( userId );
    }

    @Override
    public List<Order> fetchAllOrderByCity ( String city ) {
        return riderOrderRepository.findAllOrderByCity ( city );
    }

    @Override
    public List<Order> fetchDeliveredOrder ( Boolean delivered ) {
        return riderOrderRepository.findDelivered ( delivered );
    }

    @Override
    public List<OrderItem> fetchAllOrderItem ( Integer itemId ) {
        return riderOrderRepository.findAllOrderItem ( itemId );
    }

    @Override
    public List<Item> fetchAllItem ( Long itemNum ) {
        return riderOrderRepository.findAllItem (itemNum);
    }
}
