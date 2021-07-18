package io.aithal.dailymilkapi.repository;

import io.aithal.dailymilkapi.domain.Item;
import io.aithal.dailymilkapi.domain.Order;
import io.aithal.dailymilkapi.domain.OrderItem;
import io.aithal.dailymilkapi.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RiderOrderRepositoryImpl implements RiderOrderRepository {

    private static final String SQL_FIND_ALL_ORDER_BY_ID = "select o.order_id, o.item_id, o.created_at, o.total_item, o.total_price, " +
            "o.user_id, u.name as user_name, u.address as user_address, u.city as user_city, u.pin_code as user_pin_code, o.delivered " +
            "from dm_order as o, dm_rider_profile as r, dm_user_profile as u " +
            "where r.rider_id = ? and o.user_id = u.user_id and r.city = u.city";
    private static final String SQL_FIND_ORDER_BY_ID = "select o.order_id, o.item_id, o.created_at, o.total_item, o.total_price, o.user_id, o.delivered, " +
            "u.name as user_name, u.address as user_address, u.city as user_city, u.pin_code as user_pin_code " +
            "from dm_order as o, dm_user_profile as u where o.order_id = ? and o.user_id = u.user_id";
    private static final String SQL_FIND_COMPLETED_BY_ID = "select order_id, item_id, created_at, total_item, total_price, user_id, delivered " +
            "from dm_order where order_id = ? and delivered = true ";
    private static final String SQL_FIND_ACTIVE_BY_ID = "select order_id, item_id, created_at, total_item, total_price, user_id, delivered " +
            "from dm_order where order_id = ?";
    private static final String SQL_FIND_ALL_ITEM_NUM_BY_ID = "select item_num, product_id, quantity from dm_order_item where item_id = ? and user_id = ?";
    private static final String SQL_FIND_ALL_ITEM_BY_ID = "select oi.item_id, oi.item_num, oi.total_price, oi.user_id, i.item_num, i.product_id, i.quantity " +
            "from dm_order_item as oi, dm_item as i where oi.item_id = ? and oi.user_id = ? and oi.item_num = i.item_num";
    private static final String SQL_FIND_ITEM_BY_NUM = "select item_num, product_id, quantity from dm_item where item_num = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final ResultSetExtractor<List<Order>> allOrderResultSet = ( ( rs -> {
        List<Order> orders = new ArrayList<> ();
        while (rs.next ()) {
            orders.add ( new Order (
                    rs.getLong ( "order_id" ),
                    rs.getInt ( "item_id" ),
                    rs.getInt ( "total_item" ),
                    rs.getDouble ( "total_price" ),
                    rs.getLong ( "created_at" ),
                    rs.getBoolean ( "delivered" )
            ) );
        }
        return orders;
    } ) );

    private final RowMapper<Order> orderRowMap = ( ( rs, rowNum ) -> {
        return new Order (
                rs.getLong ( "order_id" ),
                rs.getInt ( "item_id" ),
                new OrderItem (
                        rs.getInt ( "item_id" ),
                        rs.getDouble ( "total_price" ),
                        rs.getInt ( "user_id" ),
                        findAllItemByItemId ( rs.getInt ( "item_id" ), rs.getInt ( "user_id" ) )
                ),
                rs.getInt ( "total_item" ),
                rs.getDouble ( "total_price" ),
                new UserProfile (
                        rs.getInt ( "user_id" ),
                        rs.getString ( "user_name" ),
                        rs.getString ( "user_address" ),
                        rs.getString ( "user_city" ),
                        rs.getInt ( "user_pin_code" ) ),
                rs.getLong ( "created_at" ),
                rs.getBoolean ( "delivered" ),
                null );
    } );

    @Override
    public List<Order> findAllOrder ( Integer riderId ) {
        return jdbcTemplate.query ( SQL_FIND_ALL_ORDER_BY_ID, allOrderResultSet, riderId );
    }

    @Override
    public Order findOrderById ( Long orderId ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_ORDER_BY_ID, orderRowMap, orderId );
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

    @Override
    public List<Order> findAllCompletedOrder ( Integer riderId ) {
        return jdbcTemplate.query ( SQL_FIND_COMPLETED_BY_ID, allOrderResultSet, riderId );
    }

    @Override
    public List<Order> findAllActiveOrder ( Integer riderId ) {
        return jdbcTemplate.query ( SQL_FIND_ACTIVE_BY_ID, allOrderResultSet, riderId );
    }

    @Override
    public Long findItemNumByItemId ( Integer itemId, Integer userId ) {
        return null;
    }

    @Override
    public List<Long> findAllItemNumByItemId ( Integer itemId, Integer userId ) {
        return jdbcTemplate.query ( SQL_FIND_ALL_ITEM_NUM_BY_ID, resultSet -> {
            List<Long> itemNums = new ArrayList<> ();
            while (resultSet.next ()) {
                itemNums.add ( resultSet.getLong ( "item_num" ) );
            }
            return itemNums;
        }, itemId, userId );
    }

    @Override
    public Item findItemByItemNum ( Integer itemNum ) {
        return jdbcTemplate.queryForObject ( SQL_FIND_ITEM_BY_NUM, ( ( resultSet, i ) -> {
            return new Item ( resultSet.getLong ( "item_num" ),
                    resultSet.getInt ( "product_id" ),
                    resultSet.getInt ( "quantity" ) );
        } ), itemNum );
    }

    @Override
    public List<Item> findAllItemByItemId ( Integer itemId, Integer userId ) {
        return jdbcTemplate.query ( SQL_FIND_ALL_ITEM_BY_ID, resultSet -> {
            List<Item> items = new ArrayList<> ();
            while (resultSet.next ()) {
                items.add ( new Item (
                        resultSet.getLong ( "item_num" ),
                        resultSet.getInt ( "product_id" ),
                        resultSet.getInt ( "quantity" )
                ) );
            }
            return items;
        }, itemId, userId );
    }
}
