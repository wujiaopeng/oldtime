package com.novel.oldtime.service;

import com.novel.oldtime.domain.*;
import com.novel.oldtime.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/19 12:45
 * @Description:
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    @Override
    public List<Book> getBookList(Map<String, Object> map) {
        return bookDao.getBookList(map);
    }

    @Override
    public int getBookCount(Map<String, Object> map) {
        return bookDao.getBookCount(map);
    }

    @Override
    public List<Type> getBookType() {
        return bookDao.getBookType();
    }

    @Override
    public Book getBookById(Integer bid) {
        return bookDao.getBookById(bid);
    }

    @Override
    public boolean deletePict(Integer bid) {
        int num=bookDao.deletePict(bid);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBookById(Book book) {
        int num=bookDao.updateBookById(book);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public boolean addBook(Book book) {
        int num=bookDao.addBook(book);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public boolean delBookById(Integer bid) {
        int num=bookDao.delBookById(bid);
        if(num>0)
            return true;
        return false;
    }

    @Override
    public List<Book> getBookListBytId(Integer tId,Integer sortId,String queryname,String sname,Integer uId) {
        return bookDao.getBookListBytId(tId,sortId,queryname,sname,uId);
    }

    @Override
    public List<Book> getBookListByRand() {
        return bookDao.getBookListByRand();
    }

    @Override
    public List<Comment>  getCommentById(Integer bid) {
        return bookDao.getCommentById(bid);
    }

    @Override
    public int getCarCount(Integer uid) {
        return bookDao.getCarCount(uid);
    }

    @Override
    public Integer getCarBookCount(Integer uid, Integer bid) {
        return bookDao.getCarBookCount(uid,bid);
    }


    @Override
    public int updateCarCount(Integer bid, Integer uid) {
        return bookDao.updateCarCount(bid,uid);
    }

    @Override
    public int addCar(ShoppingCar shoppingCar) {
        return bookDao.addCar(shoppingCar);
    }

    @Override
    public int addComment(Comment comment) {
        return bookDao.addComment(comment);
    }

    @Override
    public List<ShoppingCar> getCarAndBookById(Integer sellId, Integer buyId) {
        return bookDao.getCarAndBookById(sellId,buyId);
    }

    @Override
    public List<User> getUserListById(Integer buyId) {
        return bookDao.getUserListById(buyId);
    }

    @Override
    public int deleteCarById(Integer scId) {
        return bookDao.deleteCarById(scId);
    }

    @Override
    public int changeCarNumById(Integer scid, Integer num) {
        return bookDao.changeCarNumById(scid,num);
    }

    @Override
    public int changeBookNum(Integer bid, Integer num) {
        return bookDao.changeBookNum(bid,num);
    }

    @Override
    public int updateBookNumber(Integer bid, Integer num) {
        return bookDao.updateBookNumber(bid,num);
    }
}
