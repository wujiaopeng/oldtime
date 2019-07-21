package com.novel.oldtime.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;

/**
 * @Author: Peng
 * @Date: 2019/6/10 13:13
 * @Description:
 */
public class TypeConverter {
    //base64字符串转化成图片
    public static String GenerateImage(String imgStr,String path)
    {
        boolean flag=false;
        String pictPath=null;
        System.out.println("已经收到了把字节码转化为图片的方法"+imgStr);
        if (imgStr ==null || imgStr.equals("null") || imgStr==""){
            System.out.println("图像数据为空");
            return pictPath;
        }
       else{
            BASE64Decoder decoder = new BASE64Decoder();
            try
            {
                //Base64解码
                byte[] b = decoder.decodeBuffer(imgStr);
                for(int i=0;i<b.length;++i)
                {
                    if(b[i]<0)
                    {//调整异常数据
                        b[i]+=256;
                    }
                }
                //生成jpeg图片
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String fileName=uuid+".jpg";
                String imgFilePath = path+fileName;//新生成的图片
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
                pictPath="static/uploadfile/"+fileName;
                flag=true;
            }
            catch (Exception e)
            {
                flag=false;
            }
        }
        if (flag){
            return pictPath;
        }else{
            return null;
        }
    }
}
