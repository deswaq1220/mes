package com.example.mes.repository;

import com.example.mes.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {

    @Query("SELECT o FROM Orders o WHERE o.order_no = :orderNo")
    Orders findByOrderNo(String orderNo);

    @Transactional
    @Modifying
    default void insertOrder(String productId, int orderQuantity, Date deliveryDate, String orderStatus) {
        Orders orders = new Orders();
        orders.setProduct_id(productId);
        orders.setOrder_quantity(orderQuantity);
        orders.setDelivery_date(deliveryDate);
        orders.setOrder_status(orderStatus);
        save(orders);
    }

    @Query("SELECT o FROM Orders o WHERE o.order_status = :orderStatus")
    List<Orders> findByOrderStatus(@Param("orderStatus") String orderStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Orders o SET o.order_status = :orderStatus WHERE o.order_no = :orderNo")
    void setOrderStatus(@Param("orderNo") String orderNo, @Param("orderStatus") String orderStatus);

}
