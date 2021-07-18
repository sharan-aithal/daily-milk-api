package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.OrderItem;

import java.util.List;

public interface RiderOrderRepository {
    List<Order> findAllOrder ( Integer riderId );

    Order findOrderById ( Long orderId );

    List<Order> findOrderByUserId ( Integer userId );

    List<Order> findAllOrderByCity ( String city );

    List<Order> findDelivered ( Boolean delivered );

    List<OrderItem> findAllOrderItem ( Integer itemId );

    List<Item> findAllItem ( Long itemNum );

    List<Order> findAllCompletedOrder ( Integer riderId );

    List<Order> findAllActiveOrder ( Integer riderId );

    Long findItemNumByItemId ( Integer itemId, Integer userId );

    List<Long> findAllItemNumByItemId ( Integer itemId, Integer userId );

    Item findItemByItemNum ( Integer itemNum );

    List<Item> findAllItemByItemId ( Integer itemNum, Integer userId );
}
