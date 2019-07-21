package com.novel.oldtime.service;

import com.novel.oldtime.domain.OrderBook;
import com.novel.oldtime.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Peng
 * @Date: 2019/6/3 17:38
 * @Description:
 */
public interface OrderService {

    public boolean addOrder(Orders orders);

    public int addOrderBook(OrderBook orderBook);

    public int getOrderIdByNo(String orderNo);

    public List<Orders> getOrderList(Integer sellerId, Integer buyerId, Integer stateId);

    public List<OrderBook> getOrderBookList(Integer oid);

    public Orders getOrdersById(Integer oid);

    public int chageOrderState(Integer state,Integer oid,String cause);



}
