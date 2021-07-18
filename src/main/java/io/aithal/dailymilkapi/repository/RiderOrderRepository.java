package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;

import java.util.List;

public interface RiderOrderRepository {

    List<Order> findAllOrder ( Integer riderId );

    Order findOrderById ( Long orderId );

    List<Order> findAllCompletedOrder ( Integer riderId );

    List<Order> findAllActiveOrder ( Integer riderId );

    List<Long> findAllItemNumByItemId ( Integer itemId, Integer userId );

    Item findItemByItemNum ( Integer itemNum );

    List<Item> findAllItemByItemId ( Integer itemNum, Integer userId );
}
