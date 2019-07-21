package com.novel.oldtime.service;

import com.novel.oldtime.domain.*;
import com.novel.oldtime.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/20 17:23
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList(Map<String, Object> map) {
        return userDao.getUserList(map);
    }

    @Override
    public int getUserCount(Map<String, Object> map) {
        return userDao.getUserCount(map);
    }

    @Override
    public boolean addUser(User user) {
        int num=userDao.addUser(user);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public User getUserByCode(String ucode) {
        return userDao.getUserByCode(ucode);
    }


    @Override
    public boolean updateUserById(User user) {
        int num=userDao.updateUserById(user);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delPictByUid(Integer uid) {
        int num=userDao.delPictByUid(uid);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer uid) {
        int num=userDao.deleteUserById(uid);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public Address getAddressById(Integer uid,Integer aid) {
        return userDao.getAddressById(uid,aid);
    }

    @Override
    public int addAddress(Address address) {
        return userDao.addAddress(address);
    }

    @Override
    public int modifyAddress(Address address) {
        return userDao.modifyAddress(address);
    }

    @Override
    public User getUser(Integer uid, String phone) {
        return userDao.getUser(uid,phone);
    }

    @Override
    public int updateUserPwd(String phone, String password) {
        return userDao.updateUserPwd(phone,password);
    }

    @Override
    public User getUserInfo(Integer uid) {
        return userDao.getUserInfo(uid);
    }

    @Override
    public List<Comment> getCommentList(Integer uid) {
        return userDao.getCommentList(uid);
    }

    @Override
    public int deleteComment(Integer cid) {
        return userDao.deleteComment(cid);
    }

    /*
        最新，开始订单和购物车部分
     */
    //获取购买订单（同时还要获取买家、卖家、书本（列表）类）
    @Override
    public List<Orders> getBuyerOrdersListByBuyerId(Map<String,Object> map) {
        return userDao.getBuyerOrdersListByBuyerId(map);
    }
    //获取购买订单数量
    @Override
    public int getBuyerOrdersListCountByBuyerId(Map<String, Object> map) {
        return userDao.getBuyerOrdersListCountByBuyerId(map);
    }

    //获取卖出订单（同时还要获取买家、卖家、书本（列表）类）
    @Override
    public List<Orders> getSellerOrdersListBySellerId(Map<String,Object> map) {
        return userDao.getSellerOrdersListBySellerId(map);
    }
    //获取卖出订单数量
    @Override
    public int getSellerOrdersListCountBySellerId(Map<String, Object> map) {
        return userDao.getSellerOrdersListCountBySellerId(map);
    }

    //获取用户收货地址
    @Override
    public String getAddressByUId(Integer uId) {
        return userDao.getAddressByUId(uId);
    }

    //获取订单状态名
    @Override
    public String getStateNameByState(Integer state) {
        return userDao.getStateNameByState(state);
    }

    //分页获取一条订单中所有书本
    @Override
    public List<OrderBook> getOrderBookListByOrderId(Map<String, Object> map) {
        return userDao.getOrderBookListByOrderId(map);
    }
    //获取一条订单中所有书本数量
    @Override
    public int getOrderBookListCountByOrderId(Map<String, Object> map) {
        return userDao.getOrderBookListCountByOrderId(map);
    }

    //删除一条订单中所有书本数据
    @Override
    public boolean deleteOrderBookByOrdersId(Integer orderId) {
        int num=userDao.deleteOrderBookByOrdersId(orderId);
        if(num>0)
            return true;
        return false;
    }

    //删除一条订单
    @Override
    public boolean deleteOrdersById(Integer id) {
        int num=userDao.deleteOrdersById(id);
        if(num>0)
            return true;
        return false;
    }

    //获取用户购物车（同时还要获取买家、书本类）
    @Override
    public List<ShoppingCar> getShoppingCarListByUId(Map<String,Object> map) {
        return userDao.getShoppingCarListByUId(map);
    }

    @Override
    public int getShoppingCarListCountByUId(Map<String, Object> map) {
        return userDao.getShoppingCarListCountByUId(map);
    }

    //删除一条购物车数据
    @Override
    public boolean deleteShoppingCarById(Integer sId) {
        int num=userDao.deleteShoppingCarById(sId);
        if(num>0)
            return true;
        return false;
    }
}
