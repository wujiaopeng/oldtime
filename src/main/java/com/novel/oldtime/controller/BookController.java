package com.novel.oldtime.controller;

import com.novel.oldtime.domain.*;
import com.novel.oldtime.service.BookService;
import com.novel.oldtime.utils.PageSupport;
import com.novel.oldtime.utils.TypeConverter;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Peng
 * @Date: 2019/5/19 12:46
 * @Description:
 */
@Controller
@RequestMapping("/")
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping(value="/booklist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllBook(){
        List<Book> books=bookService.getBookListByRand();
        return books;
    }

    @RequestMapping(value="/getbooklist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getBookListBytid(@RequestParam(value="tId",required=false)Integer tId,
                                   @RequestParam(value="sortId",required=false)Integer sortId,
                                   @RequestParam(value="queryname",required=false)String queryname,
                                   @RequestParam(value="sname",required=false)String sname,
                                    @RequestParam(value="uId",required=false)Integer uId){
        System.out.println("-------------tid----------"+tId);
        System.out.println("-------------sortId----------"+sortId);
        System.out.println("-------------queryname----------"+queryname);
        System.out.println("-------------sname----------"+sname);
        System.out.println("-------------uId----------"+uId);
        List<Book> books=bookService.getBookListBytId(tId,sortId,queryname,sname,uId);
        return books;
    }

    //android端传进数据添加书籍信息
    @RequestMapping(value = "addbook.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addBookInfo(Book book,HttpServletRequest request){
        System.out.println("===================PictPath"+book.getPictPath());
        //定义上传路径
        String path=request.getSession().getServletContext().getRealPath("/") + "static/images/";
        String pictPath= TypeConverter.GenerateImage(book.getPictPath(),path);
        if (pictPath!=null){
            book.setPictPath(pictPath);
            System.out.println("===================pictPath"+pictPath);
            boolean flag=bookService.addBook(book);
            if (flag)
                return 1;
            else
                return 0;
        }
        else
            return 0;
    }

    //android端传进数据添加书籍信息
    @RequestMapping(value = "bookmodify.json",method = RequestMethod.POST)
    @ResponseBody
    public Object BookModify(Book book,HttpServletRequest request) {
        //定义上传路径
        String path=request.getSession().getServletContext().getRealPath("/") + "static/uploadfile/";
        String pictPath= TypeConverter.GenerateImage(book.getPictPath(),path);
        if (pictPath!=null) {
            book.setPictPath(pictPath);
            System.out.println("===================pictPath"+pictPath);
            boolean flag=bookService.updateBookById(book);
            if (flag)
                return 1;
            else
                return 0;
        }
        else
            return 0;
    }

    @RequestMapping(value = "/delbook.json",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteBookById(Integer bid){
        boolean flag=bookService.delBookById(bid);
        if (flag){
            return 1;
        }
        return 0;
    }

    @RequestMapping(value="/typelist.json")
    @ResponseBody
    public Object getTypeList(){
        List<Type> typeList=bookService.getBookType();
        return typeList;
    }
    //通过id获取book详细信息
    @RequestMapping(value="/getbook.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getBookById(Integer bid){
        System.out.println("-----------bid--------------"+bid);
        Book book=bookService.getBookById(bid);
        return book;
    }


    //通过id获取留言信息
    @RequestMapping(value="/getcomment.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getCommentById(Integer bid){
        System.out.println("-----------bid--------------"+bid);
        List<Comment>  comments=bookService.getCommentById(bid);
        return comments;
    }

    //通过bid和uid获取满足购物车的数量
    @RequestMapping(value="/getcarnum.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getCarNum(@RequestParam(value="uid",required=false) Integer uid){
        System.out.println("-----------uid--------------"+uid);
        int count=bookService.getCarCount(uid);
        return count;
    }

    //通过bid和uid获取满足购物车的数量
    @RequestMapping(value="/getcarbooknum.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getCarBookNum(@RequestParam(value="uid",required=false) Integer uid,
                            @RequestParam(value="bid",required=false) Integer bid){
        System.out.println("-----------bid--------------"+bid);
        System.out.println("-----------uid--------------"+uid);
        Integer number=bookService.getCarBookCount(uid,bid);
        if(number==null){
            number=-1;
        }
        return number;
    }

    //通过bid和uid获取满足购物车的数量
    @RequestMapping(value="/updatecarnum.json",method = RequestMethod.GET)
    @ResponseBody
    public Object updateCarNum(@RequestParam(value="uid",required=false) Integer uid,
                            @RequestParam(value="bid",required=false) Integer bid){
        System.out.println("-----------bid--------------"+bid);
        System.out.println("-----------uid--------------"+uid);
        int number=bookService.updateCarCount(bid,uid);
        return number;
    }

    @RequestMapping(value = "/addshoppingcar.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addShoppingCar(ShoppingCar shoppingCar){
        System.out.println("-----------bid--------------"+shoppingCar.getBid());
        shoppingCar.setCreatetime(new Date());
        int num=bookService.addCar(shoppingCar);
        return num;
    }

    @RequestMapping(value = "/addcomment.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addComment(Comment comment){
        System.out.println("-----------bid--------------"+comment.getBid());
        comment.setCreatetime(new Date());
        int num=bookService.addComment(comment);
        return num;
    }

    @RequestMapping(value = "/getcarlist.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getShoppingCarList(Integer buyId){
        System.out.println("-----------bid--------------"+buyId);
        List<User> users=bookService.getUserListById(buyId);
        for(User user:users){
            List<ShoppingCar> shoppingCars=bookService.getCarAndBookById(user.getId(),buyId);
            user.setShoppingCarList(shoppingCars);
        }
        return users;
    }

    @RequestMapping(value = "/deletecar.json",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteShoppingCarById(Integer scId){
        System.out.println("-----------bid--------------"+scId);
        int count=bookService.deleteCarById(scId);
        return count;
    }

    @RequestMapping(value = "/changecarnum.json",method = RequestMethod.GET)
    @ResponseBody
    public Object changeShoppingCarNum(Integer scid,Integer num){
        System.out.println("-----------scid--------------"+scid);
        System.out.println("-----------num--------------"+num);
        int count=bookService.changeCarNumById(scid,num);
        return count;
    }

    @RequestMapping(value = "/changebooknum.json",method = RequestMethod.GET)
    @ResponseBody
    public Object changeBookNumber(Integer bid,Integer num){
        System.out.println("-----------bid--------------"+bid);
        System.out.println("-----------num--------------"+num);
        int count=bookService.changeBookNum(bid,num);
        return count;
    }

    @RequestMapping("/booklist")
    public String getBookList(Model model,
                              @RequestParam(value="queryname",required=false) String queryname,
                              @RequestParam(value="querytype",required=false) String querytype,
                              @RequestParam(value="pageIndex",required=false) String pageIndex){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("queryname",queryname);
        map.put("querytype",querytype);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=bookService.getBookCount(map);//总数量（表）
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();//总页数
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        System.out.println("*********************************"+(currentPageNo-1)*pageSize);
        map.put("currentPageNo", (currentPageNo-1)*pageSize);
        map.put("pageSize", pageSize);
        System.out.println("开始****************************************"+pageIndex);
        List<Book> bookList=bookService.getBookList(map);
        model.addAttribute("bookList",bookList);
        model.addAttribute("queryname",queryname);
        model.addAttribute("querytype",querytype);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "book/booklist";
    }

    @RequestMapping("/bookview")
    public String getBookView(Integer bid,Model model){
        System.out.println("-----------bid--------------"+bid);
        Book book=bookService.getBookById(bid);
        model.addAttribute("book",book);
        return "/book/bookview";
    }
    @RequestMapping("/bookadd")
    public String addBook(){
        return "book/bookadd";
    }

    @RequestMapping(value="/savebookadd",method = RequestMethod.POST)
    public String saveAddBook(Model model,Book book,
                              HttpServletRequest request,HttpSession session,
                              @RequestParam(value="a_pictPath",required=false) MultipartFile multipartFile){
        String pictPath=null;//LOGO图片URL路径
        boolean flag=true;
        //定义上传路径
        String filepath=request.getSession().getServletContext().getRealPath("/") + "static/images/";
        if(!multipartFile.isEmpty()) {
            //获取源文件名
            String  fileName=multipartFile.getOriginalFilename();
            //获取文件后缀
            String fileSuffix= FilenameUtils.getExtension(fileName);
            if(multipartFile.getSize()>500000) {
                flag=false;
                System.out.println("大于500kb");
            }else if(fileSuffix.equalsIgnoreCase("jpg")||
                    fileSuffix.equalsIgnoreCase("jpeg")||
                    fileSuffix.equalsIgnoreCase("png")) {
                String newFileName= fileName;
                File file=new File(filepath);
                if(!file.exists()) {
                    file.mkdirs();
                }
                String path = filepath + fileName;
                File tempFile = null;
                try {
                    tempFile=new File(path);
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),tempFile);
                    pictPath="static/images/"+newFileName;
                } catch (IOException e) {
                    flag=false;
                    System.out.println("异常");
                }
            }else {
                flag=false;
            }
        }
        if(flag) {
            book.setPictPath(pictPath);
            book.setUid(11);
            if(bookService.addBook(book)) {
                return "redirect:/booklist";
            }else {
                return "book/bookadd";
            }
        }else {
            return "book/bookadd";
        }
    }

    @RequestMapping("/bookmodify")
    public String modifyBook(Integer bid,Model model){
        System.out.println("-----------bid--------------"+bid);
        Book book=bookService.getBookById(bid);
        model.addAttribute("book",book);
        return "/book/bookmodify";
    }

    @RequestMapping(value="/delpict",method = RequestMethod.POST)
    @ResponseBody
    public Object delPict(Integer bid){
       Map<String,Object> map =new HashMap<>();
       if(bookService.deletePict(bid))
           map.put("result","true");
       else
           map.put("result","false");
        return map;
    }
    @RequestMapping(value="sendbookmodify",method = RequestMethod.POST)
    public String sendModifyBook(Model model,Book book,
                                 HttpServletRequest request,
                                 @RequestParam(value="a_pictPath",required=false) MultipartFile multipartFile){

        String pictPath=null;//LOGO图片URL路径
        boolean flag=true;
        //定义上传路径
        String filepath=request.getSession().getServletContext().getRealPath("/") + "static/images/";
        if(!multipartFile.isEmpty()) {
            //获取源文件名
            String  fileName=multipartFile.getOriginalFilename();
            //获取文件后缀
            String fileSuffix= FilenameUtils.getExtension(fileName);
            if(multipartFile.getSize()>500000) {
                flag=false;
                System.out.println("大于500kb");
            }else if(fileSuffix.equalsIgnoreCase("jpg")||
                    fileSuffix.equalsIgnoreCase("jpeg")||
                    fileSuffix.equalsIgnoreCase("png")) {
                String newFileName= fileName;
                File file=new File(filepath);
                if(!file.exists()) {
                    file.mkdirs();
                }
                String path = filepath + fileName;
                File tempFile = null;
                try {
                    tempFile=new File(path);
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),tempFile);
                    pictPath="static/images/"+newFileName;
                } catch (IOException e) {
                    flag=false;
                    System.out.println("异常");
                }
            }else {
                System.out.println("格式错误");
                flag=false;
            }
        }
        if(flag) {
            book.setPictPath(pictPath);
            if(bookService.updateBookById(book)) {
                return "redirect:/booklist";
            }else {
                return "/book/bookmodify";
            }
        }else {
            return "/book/bookmodify";
        }
    }
    //通过id删除book
    @RequestMapping(value="/deleteBook",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBookByid(Integer bid){
        Map<String,Object> map =new HashMap<>();
        if(bookService.delBookById(bid))
            map.put("result","true");
        else
            map.put("result","false");
        return map;
    }
}
