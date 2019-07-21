package com.novel.oldtime.controller;

import com.novel.oldtime.domain.OrderBook;
import com.novel.oldtime.domain.Orders;
import com.novel.oldtime.service.BookService;
import com.novel.oldtime.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author: Peng
 * @Date: 2019/6/3 17:41
 * @Description:
 */
@Controller
@RequestMapping("/")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private BookService bookService;

    @RequestMapping(value="addorders.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addOrders(Orders orders){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        String orderNo=newDate+result;
        orders.setOrderNo(orderNo);
        orders.setCreateDate(new Date());
        orders.setState(1);
        boolean flag=orderService.addOrder(orders);
        int oid=orders.getId();
        return oid;
    }

    @RequestMapping(value="addorderbook.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addOrderBook(OrderBook orderBook){
        int num=orderService.addOrderBook(orderBook);
        return num;
    }

    @RequestMapping(value="getorders.json",method = RequestMethod.GET)
    @ResponseBody
    public Object addOrderBook(@RequestParam(required = false,value = "sellerId")Integer sellerId,
                               @RequestParam(required = false,value = "buyerId")Integer buyerId,
                               @RequestParam(required = false,value = "state")Integer state){
        List<Orders> ordersList=orderService.getOrderList(sellerId,buyerId,state);
        for(Orders orders:ordersList){
            List<OrderBook> orderBooks=orderService.getOrderBookList(orders.getId());
            orders.setOrderBookList(orderBooks);
        }
        return ordersList;
    }

    @RequestMapping(value="getorder.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getOrderById(Integer oid){
        Orders orders=orderService.getOrdersById(oid);
        return orders;
    }

    @RequestMapping(value="getorderbooks.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getOrderBookList(Integer oid){
        List<OrderBook> orderBooks=orderService.getOrderBookList(oid);
        return orderBooks;
    }

    @RequestMapping(value="chageorderstate.json",method = RequestMethod.GET)
    @ResponseBody
    public Object changeOrderState(Integer state,Integer oid,
                                   @RequestParam(required = false,value = "cause")String cause){
        int count=orderService.chageOrderState(state,oid,cause);
        return count;
    }


}
