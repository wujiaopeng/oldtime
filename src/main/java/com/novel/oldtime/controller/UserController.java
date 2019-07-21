package com.novel.oldtime.controller;

import com.novel.oldtime.domain.*;
import com.novel.oldtime.service.BookService;
import com.novel.oldtime.service.UserService;
import com.novel.oldtime.utils.PageSupport;
import com.novel.oldtime.utils.TypeConverter;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Peng
 * @Date: 2019/5/20 17:25
 * @Description:
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @RequestMapping(value="getuser.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserById(@RequestParam(required = false,value = "uid") Integer uid,
                              @RequestParam(required = false,value = "phone") String phone){
        System.out.println("====================uid"+uid);
        System.out.println("====================phone"+phone);
        User user=userService.getUser(uid,phone);
        return user;
    }

    @RequestMapping(value = "adduser.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(User user,HttpServletRequest request){
        System.out.println("===================sex"+user.getSex());
        //定义上传路径
        String path=request.getSession().getServletContext().getRealPath("/") + "static/uploadfile/";
        String pictPath= TypeConverter.GenerateImage(user.getPictPath(),path);
        if (pictPath!=null) {
            user.setPictPath(pictPath);
            System.out.println("===================pictPath"+pictPath);
            boolean flag=userService.addUser(user);
            if (flag)
                return 1;
            else
                return 0;
        }
        else
            return 0;
    }


    @RequestMapping(value="getaddress.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getAddress(@RequestParam(required = false,value = "uid") Integer uid,
                             @RequestParam(required = false,value = "aid") Integer aid){
        System.out.println("====================uid"+uid);
        System.out.println("====================aid"+aid);
        Address address=userService.getAddressById(uid,aid);
        return address;
    }

    @RequestMapping(value="addaddress.json",method = RequestMethod.POST)
    @ResponseBody
    public Object addAddress(Address address){
        System.out.println("====================uid"+address.getName());
       int count=userService.addAddress(address);
        return count;
    }

    @RequestMapping(value="modifyaddress.json",method = RequestMethod.POST)
    @ResponseBody
    public Object modifyAddress(Address address){
        System.out.println("====================uid"+address.getName());
        int count=userService.modifyAddress(address);
        return count;
    }

    @RequestMapping(value = "usermodifypwd.json",method =RequestMethod.POST)
    @ResponseBody
    public Object modifyUserPwd(String phone,String password){
        System.out.println("====================phone"+phone);
        int count=userService.updateUserPwd(phone, password);
        return count;
    }

    @RequestMapping(value = "dologin.json",method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(String phone,String password){
        System.out.println("====================phone"+phone);
        User user=userService.getUser(null,phone);
        if (user!=null){
            if (user.getPassword().equals(password)){
                return user.getId();
            }else{
                return 0;
            }
        }else{
            return -1;
        }
    }

    @RequestMapping(value="getuserinfo.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getUserInfo(Integer uid){
        System.out.println("====================uid"+uid);
        User user=userService.getUserInfo(uid);
        return user;
    }

    @RequestMapping(value="getcomments.json",method = RequestMethod.GET)
    @ResponseBody
    public Object getCommentList(Integer uid){
        System.out.println("====================uid"+uid);
        List<Comment> commentList=userService.getCommentList(uid);
        return commentList;
    }

    @RequestMapping(value="delcomment.json",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteComment(Integer cid){
        System.out.println("====================cid"+cid);
        int count=userService.deleteComment(cid);
        return count;
    }

    @RequestMapping("/userlist")
    public String toUserList(@RequestParam(value = "queryname",required = false) String queryname,
                             @RequestParam(value = "pageIndex",required = false) String pageIndex,
                             Model model){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("queryname",queryname);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getUserCount(map);//总数量（表）
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
        List<User> userList=userService.getUserList(map);
        model.addAttribute("userList",userList);
        model.addAttribute("queryname",queryname);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "/user/userlist";
    }

    @RequestMapping("/useradd")
    public String toUserAdd(){
        return "/user/useradd";
    }

    @RequestMapping(value = "/saveuseradd",method = RequestMethod.POST)
    public String saveAddUser(Model model, User user,
                              HttpServletRequest request, HttpSession session,
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
            user.setPictPath(pictPath);
            if(userService.addUser(user)) {
                return "redirect:/userlist";
            }else {
                return "user/useradd";
            }
        }else {
            return "user/useradd";
        }
    }

    @RequestMapping(value="isCode",method = RequestMethod.POST)
    @ResponseBody
    public Object isCodeEixt(String ucode){
        System.out.println("====================ucode"+ucode);
        Map<String,Object> map=new HashMap<>();
        if(ucode==null || ucode==""){
            map.put("result","empty");
        }else{
            User user=userService.getUserByCode(ucode);
            if(user!=null)
                map.put("result","exit");
            else
                map.put("result","noexit");
        }
        return map;
    }

    @RequestMapping("/usermodify")
    public String toUserModify(Integer uid,Model model){
        User user=userService.getUser(uid,null);
        model.addAttribute("user",user);
        return "user/usermodify";
    }

    @RequestMapping(value="delUserPict",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUserPict(Integer uid){
        Map<String,Object> map=new HashMap<>();
        if(userService.delPictByUid(uid))
            map.put("result","true");
        else
            map.put("result","false");
        return map;
    }

    @RequestMapping(value = "/saveusermodify",method = RequestMethod.POST)
    public String saveModifyUser(User user, HttpServletRequest request,
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
            user.setPictPath(pictPath);
            if(userService.updateUserById(user)) {
                return "redirect:/userlist";
            }else {
                return "user/usermodify";
            }
        }else {
            return "user/usermodify";
        }
    }

    @RequestMapping(value="deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUserById(Integer uid){
        Map<String,Object> map=new HashMap<>();
        if(userService.deleteUserById(uid))
            map.put("result","true");
        else
            map.put("result","false");
        return map;
    }

    /*
        新添加
     */
    @RequestMapping("/toBuyerOrdersList")    //去到用户购买订单页
    public String toBuyerOrdersList(@RequestParam(value = "pageIndex",required = false) String pageIndex,
                                    @RequestParam(value = "buyerId",required = false) String buyerId,
                                    Model model){
        System.out.println("====================进入toBuyerOrdersList方法");

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("buyerId",buyerId);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getBuyerOrdersListCountByBuyerId(map); //获取买家订单总数量
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
        List<Orders> ordersList=userService.getBuyerOrdersListByBuyerId(map);   //分页查询买家订单列表

        for(int i=0; i<ordersList.size(); i++){
            User buyerUser = userService.getUserInfo(ordersList.get(i).getBuyerId());
            User sellerUser = userService.getUserInfo(ordersList.get(i).getSellerId());
            String address = userService.getAddressByUId(ordersList.get(i).getBuyerId());
            String stateName = userService.getStateNameByState(ordersList.get(i).getState());

            ordersList.get(i).setBuyerName(buyerUser.getName());
            ordersList.get(i).setSellerName(sellerUser.getName());
            ordersList.get(i).setAddress(address);
            ordersList.get(i).setStateName(stateName);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                ordersList.get(i).setCreateDate1(sdf.format(ordersList.get(i).getCreateDate()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        model.addAttribute("buyerId",buyerId);
        model.addAttribute("ordersList",ordersList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "/user/buyerOrdersList";
    }
    @RequestMapping("/toBuyerOrdersView")      //去到买家单个订单详情页
    public String toBuyerOrdersView(@RequestParam(value = "pageIndex",required = false) String pageIndex,
                                    @RequestParam(value = "orderId",required = false) String orderId,
                                    @RequestParam(value = "buyerId",required = false) String buyerId,
                                    Model model){
        System.out.println("====================进入toBuyerOrdersView方法");
        System.out.println("====================orderId:"+orderId);
        System.out.println("====================buyerId:"+buyerId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("orderId",orderId);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getOrderBookListCountByOrderId(map); //获取买家订单总数量
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
        List<OrderBook> orderBookList=userService.getOrderBookListByOrderId(map);   //分页查询买家订单列表

        for(int i=0; i<orderBookList.size(); i++){
            Book book = bookService.getBookById(orderBookList.get(i).getBid());

            orderBookList.get(i).setBookName(book.getBookName());
            orderBookList.get(i).setBookImage(book.getPictPath());
            orderBookList.get(i).setBookPrice(book.getNewPrice().multiply(new BigDecimal(""+orderBookList.get(i).getNumber())));
        }

        model.addAttribute("buyerId",buyerId);
        model.addAttribute("orderId",orderId);
        model.addAttribute("orderBookList",orderBookList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "user/buyerOrdersView";
    }

    @RequestMapping("/toSellerOrdersList")    //去到用户售出订单页
    public String toSellerOrdersList(@RequestParam(value = "pageIndex",required = false) String pageIndex,
                                     @RequestParam(value = "sellerId",required = false) String sellerId,
                                     Model model){
        System.out.println("====================进入toSellerOrdersList方法");

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("sellerId",sellerId);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getSellerOrdersListCountBySellerId(map); //获取卖家订单总数量
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
        List<Orders> ordersList=userService.getSellerOrdersListBySellerId(map);   //分页查询买家订单列表

        for(int i=0; i<ordersList.size(); i++){
            User buyerUser = userService.getUserInfo(ordersList.get(i).getBuyerId());
            User sellerUser = userService.getUserInfo(ordersList.get(i).getSellerId());
            String address = userService.getAddressByUId(ordersList.get(i).getBuyerId());
            String stateName = userService.getStateNameByState(ordersList.get(i).getState());

            ordersList.get(i).setBuyerName(buyerUser.getName());
            ordersList.get(i).setSellerName(sellerUser.getName());
            ordersList.get(i).setAddress(address);
            ordersList.get(i).setStateName(stateName);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                ordersList.get(i).setCreateDate1(sdf.format(ordersList.get(i).getCreateDate()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        model.addAttribute("sellerId",sellerId);
        model.addAttribute("ordersList",ordersList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "/user/sellerOrdersList";
    }
    @RequestMapping("/toSellerOrdersView")      //去到卖家单个订单详情页
    public String toSellerOrdersView(@RequestParam(value = "pageIndex",required = false) String pageIndex,
                                     @RequestParam(value = "orderId",required = false) String orderId,
                                     @RequestParam(value = "sellerId",required = false) String sellerId,
                                     Model model){
        System.out.println("====================进入toSellerOrdersView方法");
        System.out.println("====================orderId:"+orderId);
        System.out.println("====================sellerId:"+sellerId);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("orderId",orderId);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getOrderBookListCountByOrderId(map); //获取卖家订单总数量
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
        List<OrderBook> orderBookList=userService.getOrderBookListByOrderId(map);   //分页查询卖家订单列表

        for(int i=0; i<orderBookList.size(); i++){
            Book book = bookService.getBookById(orderBookList.get(i).getBid());

            orderBookList.get(i).setBookName(book.getBookName());
            orderBookList.get(i).setBookImage(book.getPictPath());
            orderBookList.get(i).setBookPrice(book.getNewPrice().multiply(new BigDecimal(""+orderBookList.get(i).getNumber())));
        }

        model.addAttribute("sellerId",sellerId);
        model.addAttribute("orderId",orderId);
        model.addAttribute("orderBookList",orderBookList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "user/sellerOrdersView";
    }

    @RequestMapping(value="deleteOrdersById",method = RequestMethod.POST)     //ajax删除单条订单
    @ResponseBody
    public Object deleteOrdersById(Integer orderId){
        Map<String,Object> map=new HashMap<>();
        if(userService.deleteOrderBookByOrdersId(orderId))
            if(userService.deleteOrdersById(orderId))
                map.put("result", "true");
            else
                map.put("result","false");
        else
            map.put("result","false");
        return map;
    }

    @RequestMapping("/toShoppingCarList")    //去到购物车页
    public String toShoppingCarList(@RequestParam(value = "pageIndex",required = false) String pageIndex,
                                    @RequestParam(value = "uId",required = false) String uId,
                                    Model model){
        System.out.println("====================进入toShoppingCarList方法");

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("uId",uId);
        int pageSize=5;//设置一页显示多少条
        int currentPageNo = 1;//当前页码
        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/syserror";
            }
        }
        int totalCount	=userService.getShoppingCarListCountByUId(map); //获取卖家订单总数量
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
        List<ShoppingCar> shoppingCarList=userService.getShoppingCarListByUId(map);   //分页查询买家订单列表

        for(int i=0; i<shoppingCarList.size(); i++){

            Book book = bookService.getBookById(shoppingCarList.get(i).getBid());
            User sellerUser = userService.getUserInfo(book.getUid());

            shoppingCarList.get(i).setBookName(book.getBookName());
            shoppingCarList.get(i).setBookImage(book.getPictPath());
            shoppingCarList.get(i).setSellName(sellerUser.getName());
            try {
                shoppingCarList.get(i).setPrice(book.getNewPrice().multiply(new BigDecimal(String.valueOf(shoppingCarList.get(i).getNumber()))));
            }
            catch (NumberFormatException e){
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            System.out.println("**************shoppingCarList.get(i).getCreatetime():"+shoppingCarList.get(i).getCreatetime());
            try {
                shoppingCarList.get(i).setCreateDate(sdf.format(shoppingCarList.get(i).getCreatetime()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("**************shoppingCarList.get(i).getCreateDate():"+shoppingCarList.get(i).getCreateDate());
        }

        model.addAttribute("uId",uId);
        model.addAttribute("shoppingCarList",shoppingCarList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "/user/shoppingcar";
    }

    @RequestMapping(value="/deleteShoppingCarById",method = RequestMethod.POST)     //ajax删除单条购物车
    @ResponseBody
    public Object deleteShoppingCarById(Integer sId){
        System.out.println("**************进入deleteShoppingCarById方法，sId:"+sId);

        Map<String,Object> map=new HashMap<>();
        if(userService.deleteShoppingCarById(sId))
            map.put("result", "true");
        else
            map.put("result","false");
        return map;
    }

    @RequestMapping("/backUserList")    //去到用户列表页
    public String backUserList(@RequestParam(value = "queryname",required = false) String queryname,
                               @RequestParam(value = "pageIndex",required = false) String pageIndex,
                               Model model) {
        model.addAttribute("queryname", queryname);
        model.addAttribute("pageIndex", pageIndex);

        return "redirect:/userlist";
    }

}
