package com.novel.oldtime.service;

import com.novel.oldtime.domain.OrderBook;
import com.novel.oldtime.domain.Orders;
import com.novel.oldtime.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Peng
 * @Date: 2019/6/3 17:39
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public boolean addOrder(Orders orders) {
        int num=orderDao.addOrder(orders);
        if (num>0)
            return true;
        return false;
    }

    @Override
    public int addOrderBook(OrderBook orderBook) {
        return orderDao.addOrderBook(orderBook);
    }

    @Override
    public int getOrderIdByNo(String orderNo) {
        return orderDao.getOrderIdByNo(orderNo);
    }

    @Override
    public List<Orders> getOrderList(Integer sellerId, Integer buyerId, Integer stateId) {
        return orderDao.getOrderList(sellerId,buyerId,stateId);
    }

    @Override
    public List<OrderBook> getOrderBookList(Integer oid) {
        return orderDao.getOrderBookList(oid);
    }

    @Override
    public Orders getOrdersById(Integer oid) {
        return orderDao.getOrdersById(oid);
    }

    @Override
    public int chageOrderState(Integer state,Integer oid,String cause) {
        return orderDao.chageOrderState(state,oid,cause);
    }
}
