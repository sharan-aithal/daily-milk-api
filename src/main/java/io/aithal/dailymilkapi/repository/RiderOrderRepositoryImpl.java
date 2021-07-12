package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RiderOrderRepositoryImpl implements RiderOrderRepository {

    private static final String SQL_FIND_ALL_BY_ID = "select o.order_id, o.item_id, o.created_at, o.total_item, o.total_price, o.user_id, o.delivered " +
            "from dm_order as o, dm_rider_profile as r, dm_user_profile as u " +
            "where r.rider_id = ? and o.user_id = u.user_id and r.city = u.city";
    private static final String SQL_FIND_BY_ID = "select order_id, item_id, created_at, total_item, total_price, user_id, delivered " +
            "from dm_order where order_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ResultSetExtractor<List<Order>> orderResultSet = ( ( rs -> {
        List<Order> orders = new ArrayList<> ();
        while (rs.next ()) {
            orders.add ( new Order (
                    rs.getLong ( "order_id" ),
                    rs.getInt ( "item_id" ),
                    rs.getLong ( "created_at" ),
                    rs.getInt ( "total_item" ),
                    rs.getDouble ( "total_price" ),
                    rs.getInt ( "user_id" ),
                    rs.getBoolean ( "delivered" )
            ) );
        }
        return orders;
    } ) );

    private final RowMapper<Order> orderRowMap = ( ( rs, rowNum ) -> {
        return new Order (
                rs.getLong ( "order_id" ),
                rs.getInt ( "item_id" ),
                rs.getLong ( "created_at" ),
                rs.getInt ( "total_item" ),
                rs.getDouble ( "total_price" ),
                rs.getInt ( "user_id" ),
                rs.getBoolean ( "delivered" )
        );
    } );

    @Override
    public List<Order> findAllOrder ( Integer riderId ) {
        return jdbcTemplate.query ( SQL_FIND_ALL_BY_ID, orderResultSet, riderId );
    }

    @Override
    public Order findOrderById ( Long orderId ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_BY_ID, orderRowMap, orderId );
    }

    @Override
    public List<Order> findOrderByUserId ( Integer userId ) {
        return null;
    }

    @Override
    public List<Order> findAllOrderByCity ( String city ) {
        return null;
    }

    @Override
    public List<Order> findDelivered ( Boolean delivered ) {
        return null;
    }

    @Override
    public List<OrderItem> findAllOrderItem ( Integer itemId ) {
        return null;
    }

    @Override
    public List<Item> findAllItem ( Long itemNum ) {
        return null;
    }
}
