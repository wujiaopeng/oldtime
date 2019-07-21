package com.novel.oldtime.repository;

import com.novel.oldtime.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/19 12:27
 * @Description:
 */
@Mapper
@Repository("bookDao")
public interface BookDao {

    //根据条件查询出所有的book
    @SelectProvider(type=SqlProvider.class,method = "select1")
    public List<Book> getBookList(Map<String,Object> map);

    //根据条件查询出满足条件的数目
    @SelectProvider(type=SqlProvider.class,method = "select2")
    public int getBookCount(Map<String,Object> map);

    //根据类型id查出满足条件的book
    @SelectProvider(type=SqlProvider.class,method = "select6")
    public List<Book> getBookListBytId(Integer tId,Integer sortId,String queryname,String sname,Integer uId);

    //book表中随机获取20条数据
    @SelectProvider(type=SqlProvider.class,method  ="select7")
    public List<Book> getBookListByRand();


    //查询出所有的类型
    @Select("select * from type")
    public List<Type> getBookType();

    //通过id查询book详细信息
    @SelectProvider(type=SqlProvider.class,method = "select3")
    public Book getBookById(Integer bid);

    //添加一条book数据
    @InsertProvider(type=SqlProvider.class,method = "insert1")
    public int addBook(Book book);

    //通过id将图片删除
    @Update("update book set pictPath='' where id=#{bid}")
    public  int deletePict(Integer bid);

    //通过id更改book信息
    @UpdateProvider(type = SqlProvider.class,method = "update1")
    public int updateBookById(Book book);

    //通过id删除book
    @Delete("delete from book where id=#{bid}")
    public int delBookById(Integer bid);

    //通过bookid查询书籍留言内容
    @Select("select c.*,u.name as userName,u.pictPath as userImage from comment c,user u " +
            " where c.uid=u.id and c.bid=#{bid}")
    public List<Comment> getCommentById(Integer bid);

    //通过uid和bid获取购物的数据条数
    @Select("select count(*) as count from shoppingcar where uid=#{uid}")
    public int getCarCount(Integer uid);

    //通过uid和bid获取购物车中书籍数量
    @Select("select number from shoppingcar where uid=#{uid} and bid=#{bid}")
    public Integer getCarBookCount(Integer uid,Integer bid);

    //通过uid和bid更改购物车中book 的数量
    @Update("update shoppingcar set number=number+1 where uid=#{uid} and bid=#{bid}")
    public int updateCarCount(Integer bid,Integer uid);

    //添加一条购物车数据
    @Insert("insert into shoppingcar(uid,bid,number,createtime) values(#{uid},#{bid},#{number},#{createtime})")
    public int addCar(ShoppingCar shoppingCar);

    //添加一条评论数据
    @Insert("insert into comment(uid,bid,content,createtime) values(#{uid},#{bid},#{content},#{createtime})")
    public int addComment(Comment comment);

    //通过卖家id和买家id查出购物车中的商品信息
    @SelectProvider(type=SqlProvider.class,method = "select8")
    public List<ShoppingCar> getCarAndBookById(Integer sellId,Integer buyId);

    //通过userid查找shoppingcar表中关于卖家的信息
    @Select("select DISTINCT u.id,u.name,u.sex,u.school,u.pictPath from user u,book b,shoppingcar sc where sc.bid=b.id and b.uid=u.id and sc.uid=#{buyId}")
    public List<User> getUserListById(Integer buyId);

    @Delete("delete from shoppingcar where id=#{scId}")
    public int deleteCarById(Integer scId);

    @Update("update shoppingcar set number=#{num} where id=#{scid}")
    public int changeCarNumById(Integer scid,Integer num);

    //有人购买成功后，更改book的库存
    @Update("update book set number=number-#{num} where id=#{bid}")
    public int changeBookNum(Integer bid,Integer num);

    //当取消订单是，更改book的库存
    @Update("update book set number=number+#{num} where id=#{bid}")
    public int updateBookNumber(Integer bid,Integer num);

}
