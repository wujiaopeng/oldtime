package com.novel.oldtime.repository;

import com.novel.oldtime.domain.Book;
import com.novel.oldtime.domain.User;
import org.apache.ibatis.annotations.Delete;

import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/19 12:33
 * @Description:
 */
public class SqlProvider {

    //查询所有的book
    public String select1(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select b.*,t.name as typeName,u.name as userName from " +
                " book b,type t,user u where 1=1 and b.tid=t.id and b.uid=u.id");
        if(map.get("queryname")!=null && map.get("queryname")!=""){
            sql.append(" and b.bookName Like CONCAT('%',#{queryname},'%')");
        }
        if(map.get("querytype")!=null && map.get("querytype")!=""){
            sql.append(" and b.tid = #{querytype}");
        }
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select2(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from book where 1=1");
        if(map.get("queryname")!=null && map.get("queryname")!=""){
            sql.append(" and bookName Like CONCAT('%',#{queryname},'%')");
        }
        if(map.get("querytype")!=null && map.get("querytype")!=""){
            sql.append(" and tid = #{querytype}");
        }
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select3(Integer bid){
        StringBuffer sql=new StringBuffer("select b.*,t.name as typeName,u.name as userName,u.pictPath as userImage,u.sex as sex from " +
                " book b,type t,user u where 1=1 and b.tid=t.id and b.uid=u.id and b.id=#{bid}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String insert1(Book book){
        StringBuffer sql=new StringBuffer("insert into book(bookName,author,press,oldPrice,newPrice,number,pictPath," +
                " description,tid,uid) values(#{bookName},#{author},#{press},#{oldPrice},#{newPrice}," +
                "#{number},#{pictPath},#{description},#{tid},#{uid})");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String update1(Book book){
        StringBuffer sql=new StringBuffer("update book set bookName=#{bookName},author=#{author},press=#{press}, " +
                " oldPrice=#{oldPrice},newPrice=#{newPrice},number=#{number},description=#{description},tid=#{tid} ");
        if(book.getPictPath()!=null && book.getPictPath()!="" && (!book.getPictPath().equals("null"))){
            sql.append(" ,pictPath=#{pictPath}");
        }
        sql.append(" where id=#{id}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select4(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select * from user where 1=1");
        if(map.get("queryname")!=null && map.get("queryname")!=""){
            sql.append(" and name Like CONCAT('%',#{queryname},'%')");
        }
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select5(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from user where 1=1");
        if(map.get("queryname")!=null && map.get("queryname")!=""){
            sql.append(" and name Like CONCAT('%',#{queryname},'%')");
        }
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String insert2(User user){
        StringBuffer sql=new StringBuffer("insert into user(code,name,password,phone,sex,summary,pictPath,school) " +
                "values(#{code},#{name},#{password},#{phone},#{sex},#{summary},#{pictPath},#{school})");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String update2(User user){
        StringBuffer sql=new StringBuffer("update user set name=#{name},password=#{password},phone=#{phone}, " +
                " summary=#{summary}");
        if(user.getPictPath()!=null && user.getPictPath()!=""){
            sql.append(" ,pictPath=#{pictPath}");
        }
        sql.append(" where id=#{id}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    //查询所有的book
    public String select6(Integer tId,Integer sortId,String queryname,String sname,Integer uId){
        StringBuffer sql=new StringBuffer("select b.*,u.name as userName,u.pictPath as userImage,u.sex as sex,u.school as schoolName from " +
                " book b,user u where 1=1 and b.uid=u.id ");
        if(tId!=null && tId != 0){
            sql.append(" and b.tid=#{tId} ");
        }
        if (queryname!=null && queryname != "" && (!queryname.equals("null"))) {
            sql.append(" and b.bookName Like CONCAT('%',#{queryname},'%')");
        }
        if(sname!=null && sname!="" && (!sname.equals("null"))){
            sql.append(" and u.school Like CONCAT('%',#{sname},'%')");
        }
        if(uId!=null && uId != 0){
            sql.append(" and b.uid=#{uId} ");
        }
        if(sortId !=null && sortId==0){
            sql.append(" ORDER BY b.id desc");
        }
        if(sortId!=null && sortId==1){
            sql.append(" ORDER BY (b.newPrice/b.oldPrice) desc");
        }
        if(sortId!=null && sortId==2){
            sql.append(" ORDER BY b.newPrice asc");
        }
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }


    //查询任意30条数据的book
    public String select7(){
        StringBuffer sql=new StringBuffer("select b.*,t.name as typeName,u.name as userName from " +
                " book b,type t,user u where 1=1 and b.tid=t.id and b.uid=u.id");

        sql.append(" ORDER BY rand() LIMIT 25");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select8(Integer sellId,Integer buyId){
        StringBuffer sql=new StringBuffer("select sc.id,sc.bid,sc.uid,sc.number,sc.createtime,b.bookName as bookName,b.pictPath as bookImage,b.newPrice as price,b.number as bookNum " +
                " from shoppingcar sc,book b where sc.bid=b.id and sc.uid=#{buyId} and b.uid=#{sellId}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    /*
       最新，开始订单和购物车部分
    */
    //获取购买订单（同时还要获取买家、卖家、书本（列表）类）
    public String getBuyerOrdersListByBuyerId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select * from orders where buyerId=#{buyerId}");
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }
    //获取购买订单数量
    public String getBuyerOrdersListCountByBuyerId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from orders where buyerId=#{buyerId}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    //获取卖出订单（同时还要获取买家、卖家、书本（列表）类）
    public String getSellerOrdersListBySellerId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select * from orders where sellerId=#{sellerId}");
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }
    //获取卖出订单数量
    public String getSellerOrdersListCountBySellerId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from orders where sellerId=#{sellerId}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    //分页获取一条订单中所有书本
    public String getOrderBookListByOrderId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select * from orderbook where orderId=#{orderId}");
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }
    //获取一条订单中所有书本数量
    public String getOrderBookListCountByOrderId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from orderbook where orderId=#{orderId}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    //获取用户购物车（同时还要获取买家、书本类）
    public String getShoppingCarListByUId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select * from shoppingcar where uid=#{uId}");
        sql.append(" ORDER BY id DESC limit #{currentPageNo} ,#{pageSize}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }
    //获取用户购物车数量
    public String getShoppingCarListCountByUId(Map<String,Object> map){
        StringBuffer sql=new StringBuffer("select count(1) as count from shoppingcar where uid=#{uId}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

}
