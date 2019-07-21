package com.novel.oldtime.repository;

import com.novel.oldtime.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/20 17:15
 * @Description:
 */
@Mapper
@Repository("userDao")
public interface UserDao {

    @SelectProvider(type=SqlProvider.class,method = "select4")
    public List<User> getUserList(Map<String,Object> map);

    @SelectProvider(type=SqlProvider.class,method = "select5")
    public int getUserCount(Map<String,Object> map);

    @InsertProvider(type=SqlProvider.class,method = "insert2")
    public int addUser(User user);

    @Select("select * from user where code=#{ucode}")
    public User getUserByCode(String ucode);


    @UpdateProvider(type=SqlProvider.class,method = "update2")
    public int updateUserById(User user);

    @Update("update user set pictPath='' where id=#{uid}")
    public int delPictByUid(Integer uid);

    @Delete("delete from user where id=#{uid}")
    public int deleteUserById(Integer uid);

    //获取用户地址信息
    @SelectProvider(type = SqlProviderAndroid.class,method = "select2")
    public Address getAddressById(Integer uid,Integer aid);

    //添加用户地址
    @Insert("insert into address(name,phone,address,postCode,uid) values(#{name},#{phone},#{address},#{postCode},#{uid}) ")
    public int addAddress(Address address);

    //修改用户地址
    @Update("update address set name=#{name},phone=#{phone},address=#{address},postCode=#{postCode} where id=#{id}")
    public int modifyAddress(Address address);

    //通过用户id或者用户电话获取用户信息
    @SelectProvider(type = SqlProviderAndroid.class,method = "select3")
    public User getUser(Integer uid,String phone);

    //通过用户电话更改用户密码
    @Update("update user set password=#{password} where phone=#{phone}")
    public int updateUserPwd(String phone,String password);

    //通过用户id获取用户的详细信息
    @SelectProvider(type = SqlProviderAndroid.class,method = "select4")
    public User getUserInfo(Integer uid);

    //通过用户id获取留言信息
    @Select("select c.*,b.bookName as bookName  from comment c,book b where b.id=c.bid and c.uid=#{uid}")
    public List<Comment> getCommentList(Integer uid);

    //通过id删除评论信息
    @Delete("delete from comment where id=#{cid}")
    public int deleteComment(Integer cid);

    /*
        最新，开始订单和购物车部分
     */
    //分页获取购买订单（同时还要获取买家、卖家、书本（列表）类）
    @SelectProvider(type=SqlProvider.class,method = "getBuyerOrdersListByBuyerId")
    public List<Orders> getBuyerOrdersListByBuyerId(Map<String,Object> map);    //传buyerId和currentPageNo
    //获取购买订单数量
    @SelectProvider(type=SqlProvider.class,method = "getBuyerOrdersListCountByBuyerId")
    public int getBuyerOrdersListCountByBuyerId(Map<String,Object> map);   //传buyerId

    //分页获取卖出订单（同时还要获取买家、卖家、书本（列表）类）
    @SelectProvider(type=SqlProvider.class,method = "getSellerOrdersListBySellerId")    //传sellerId和currentPageNo
    public List<Orders> getSellerOrdersListBySellerId(Map<String,Object> map);
    //获取卖出订单数量
    @SelectProvider(type=SqlProvider.class,method = "getSellerOrdersListCountBySellerId")
    public int getSellerOrdersListCountBySellerId(Map<String,Object> map);   //传sellerId

    //获取用户收货地址
    @Select("select address from address where uid=#{uId}")
    public String getAddressByUId(Integer uId);

    //获取订单状态名
    @Select("select state from orderstate where id=#{state}")
    public String getStateNameByState(Integer state);

    //分页获取一条订单中所有书本
    @SelectProvider(type=SqlProvider.class,method = "getOrderBookListByOrderId")
    public List<OrderBook> getOrderBookListByOrderId(Map<String,Object> map);   //orderId和currentPageNo
    //获取一条订单中所有书本数量
    @SelectProvider(type=SqlProvider.class,method = "getOrderBookListCountByOrderId")
    public int getOrderBookListCountByOrderId(Map<String,Object> map);   //orderId

    //删除一条订单中所有书本数据
    @Delete("delete from orderbook where orderId=#{orderId}")
    public int deleteOrderBookByOrdersId(Integer orderId);

    //删除一条订单
    @Delete("delete from orders where id=#{id}")
    public int deleteOrdersById(Integer id);

    //分页获取用户购物车（同时还要获取买家、书本类）
    @SelectProvider(type=SqlProvider.class,method = "getShoppingCarListByUId")
    public List<ShoppingCar> getShoppingCarListByUId(Map<String,Object> map);       //传uId和currentPageNo
    //获取用户购物车数量
    @SelectProvider(type=SqlProvider.class,method = "getShoppingCarListCountByUId")
    public int getShoppingCarListCountByUId(Map<String,Object> map);  //传uId

    //删除一条购物车数据
    @Delete("delete from shoppingcar where id=#{sId}")
    public int deleteShoppingCarById(Integer sId);

}
