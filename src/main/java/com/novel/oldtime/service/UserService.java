package com.novel.oldtime.service;

import com.novel.oldtime.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/20 17:21
 * @Description:
 */
public interface UserService {

    public List<User> getUserList(Map<String,Object> map);

    public int getUserCount(Map<String,Object> map);

    public boolean addUser(User user);

    public User getUserByCode(String ucode);

    public boolean updateUserById(User user);

    public boolean delPictByUid(Integer uid);

    public boolean deleteUserById(Integer uid);

    public Address getAddressById(Integer uid,Integer aid);

    public int addAddress(Address address);

    public int modifyAddress(Address address);

    public User getUser(Integer uid,String phone);

    public int updateUserPwd(String phone,String password);

    public User getUserInfo(Integer uid);

    public List<Comment> getCommentList(Integer uid);

    public int deleteComment(Integer cid);
    /*
       最新，开始订单和购物车部分
    */
    //获取购买订单（同时还要获取买家、卖家、书本（列表）类）
    public List<Orders> getBuyerOrdersListByBuyerId(Map<String,Object> map);    //传buyerId和currentPageNo
    //获取购买订单数量
    public int getBuyerOrdersListCountByBuyerId(Map<String,Object> map);   //传buyerId

    //获取卖出订单（同时还要获取买家、卖家、书本（列表）类）
    public List<Orders> getSellerOrdersListBySellerId(Map<String,Object> map);    //传sellerId和currentPageNo
    //获取卖出订单数量
    public int getSellerOrdersListCountBySellerId(Map<String,Object> map);   //传sellerId

    //获取用户收货地址
    public String getAddressByUId(Integer uId);

    //获取订单状态名
    public String getStateNameByState(Integer state);

    //分页获取一条订单中所有书本
    public List<OrderBook> getOrderBookListByOrderId(Map<String,Object> map);   //orderId和currentPageNo
    //获取一条订单中所有书本数量
    public int getOrderBookListCountByOrderId(Map<String,Object> map);   //orderId

    //删除一条订单中所有书本数据
    public boolean deleteOrderBookByOrdersId(Integer orderId);

    //删除一条订单
    public boolean deleteOrdersById(Integer id);

    //获取用户购物车（同时还要获取买家、书本类）
    public List<ShoppingCar> getShoppingCarListByUId(Map<String,Object> map);       //传uId和currentPageNo
    //获取用户购物车数量
    public int getShoppingCarListCountByUId(Map<String,Object> map);  //传uId

    //删除一条购物车数据
    public boolean deleteShoppingCarById(Integer sId);
}
