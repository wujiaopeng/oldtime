package com.novel.oldtime.repository;

import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/6/5 16:39
 * @Description:
 */
public class SqlProviderAndroid {

    public String select1(Integer sellerId,Integer buyerId,Integer stateId){
        StringBuffer sql=new StringBuffer("select o.*,u.name as userName,u.pictPath as userImage," +
                "u.sex as userSex,os.state as stateName from orders o,user u,orderstate os where os.id=o.state ");
        if(buyerId!=null && buyerId!=0){
            sql.append(" and u.id=o.sellerId and o.buyerId=#{buyerId}");
        }
        if(sellerId!=null && sellerId!=0){
            sql.append(" and u.id=o.buyerId and o.sellerId=#{sellerId}");
        }
        if (stateId!=null && stateId!=0){
            sql.append(" and o.state=#{stateId}");
        }
        sql.append(" ORDER BY createDate DESC");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select2(Integer uid,Integer aid){
        StringBuffer sql=new StringBuffer("select * from address where 1=1 ");
        if(uid!=null && uid!=0){
            sql.append(" and uid=#{uid}");
        }
        if(aid!=null && aid!=0){
            sql.append(" and id=#{aid}");
        }
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String update1(Integer state,Integer oid,String cause){
        StringBuffer sql=new StringBuffer("update orders set state=#{state} ");
        if(cause!=null && cause!=""){
            sql.append(" ,cause=#{cause}");
        }
        sql.append(" where id=#{oid}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

    public String select3(Integer uid,String phone){
        StringBuffer sql=new StringBuffer("select u.*,(select count(*) from book where uid=u.id) as bookNum " +
                "  from user u where 1=1 ");
        if(uid!=null && uid!=0){
            sql.append(" and u.id=#{uid}");
        }
        if(phone!=null && phone!="" && (!phone.equals("null"))){
            sql.append(" and u.phone = #{phone}");
        }
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }


    public String select4(Integer uid){
        StringBuffer sql=new StringBuffer("select u.*," +
                "(select count(*) from book where uid=u.id) as bookNum ," +
                "(select count(*) from comment where uid=u.id) as commentNum ," +
                "(select count(*) from shoppingcar where uid=u.id) as carNum ," +
                "(select count(*) from orders where sellerId=u.id) as sellNum ," +
                "(select count(*) from orders where buyerId=u.id) as buyNum " +
                "  from user u where 1=1 and u.id=#{uid}");
        System.out.println("=============================="+sql.toString());
        return sql.toString();
    }

}
