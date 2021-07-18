package io.aithal.dailymilkapi.service;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.OrderItem;

import java.util.List;

public interface RiderOrderService {

    List<Order> fetchAllOrder ( Integer riderId );

    Order fetchOrder ( Long orderId );

    List<Order> fetchAllCompletedOrder ( Integer riderId );

    List<Order> fetchAllActiveOrder ( Integer riderId );
}
