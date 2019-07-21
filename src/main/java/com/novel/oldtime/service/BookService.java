package com.novel.oldtime.service;

import com.novel.oldtime.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/19 12:43
 * @Description:
 */
public interface BookService {

    public List<Book> getBookList(Map<String,Object> map);

    public int getBookCount(Map<String,Object> map);

    public List<Type> getBookType();

    public Book getBookById(Integer bid);

    public boolean deletePict(Integer bid);

    public boolean updateBookById(Book book);

    public boolean addBook(Book book);

    public boolean delBookById(Integer bid);

    public List<Book> getBookListBytId(Integer tId,Integer sortId,String queryname,String sname,Integer uId);

    public List<Book> getBookListByRand();

    public List<Comment>  getCommentById(Integer bid);

    public int getCarCount(Integer uid);

    public Integer getCarBookCount(Integer uid,Integer bid);

    public int updateCarCount(Integer bid,Integer uid);

    public int addCar(ShoppingCar shoppingCar);

    public int addComment(Comment comment);

    public List<ShoppingCar> getCarAndBookById(Integer sellId, Integer buyId);

    public List<User> getUserListById(Integer buyId);

    public int deleteCarById(Integer scId);

    public int changeCarNumById(Integer scid,Integer num);

    public int changeBookNum(Integer bid,Integer num);

    public int updateBookNumber(Integer bid,Integer num);
}
