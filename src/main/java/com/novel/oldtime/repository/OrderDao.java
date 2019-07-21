package com.novel.oldtime.repository;

import com.novel.oldtime.domain.OrderBook;
import com.novel.oldtime.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Peng
 * @Date: 2019/6/3 17:18
 * @Description:
 */
@Mapper
@Repository("orderDao")
public interface OrderDao {

    @Insert("insert into orders(orderNo,buyerId,sellerId,total,aid,description,way,createDate,state)" +
            "  values(#{orderNo},#{buyerId},#{sellerId},#{total},#{aid},#{description},#{way},#{createDate},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int addOrder(Orders orders);


    @Insert("insert into orderbook(orderId,bid,number) values(#{orderId},#{bid},#{number})")
    public int addOrderBook(OrderBook orderBook);

    @Select("select id from orders where orderNo=#{orderNo}")
    public int getOrderIdByNo(String orderNo);

    @SelectProvider(type=SqlProviderAndroid.class,method = "select1")
    public List<Orders> getOrderList(Integer sellerId,Integer buyerId,Integer stateId);

    @Select("select ob.*,b.pictPath as bookImage,b.newPrice as bookPrice,b.bookName from orderbook ob,book b " +
            " where b.id=ob.bid and ob.orderId=#{oid} ")
    public List<OrderBook> getOrderBookList(Integer oid);

    @Select("select o.*,os.state as stateName from orders o,orderstate os where os.id=o.state and o.id=#{oid}")
    public Orders getOrdersById(Integer oid);

    @UpdateProvider(type = SqlProviderAndroid.class,method = "update1")
    public int chageOrderState(Integer state,Integer oid,String cause);


}
