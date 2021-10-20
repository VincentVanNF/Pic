package com.example.Controller;

import com.example.Service.AliAPI;
import com.example.Service.DownLoadFromURL;
import com.example.Service.IsDig;
import com.example.Service.OnePic.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;


@Controller
@RequestMapping(value = "/picture")
public class controller_1 {
    @Autowired
    ServletContext servletContext;

    @Autowired
    AliAPI API;

    @Autowired
    One one;

    byte[] temp;

    @RequestMapping(value = "/test.do")
    public ModelAndView test() throws IOException {

        System.out.println("=====进入=====");
        ModelAndView modelAndView = new ModelAndView();
        String str = servletContext.getRealPath("/WEB-INF/Pics/test.txt");
        temp = "Hello World".getBytes(StandardCharsets.UTF_8);

        FileOutputStream fileOutputStream = new FileOutputStream(str);
        fileOutputStream.write(temp);

        FileInputStream fileInputStream = new FileInputStream(str);
        fileInputStream.read(temp);
        String msg = new String(temp);

        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("show");
        System.out.println("=====退出=====");

        return modelAndView;

    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public ModelAndView first(HttpServletRequest httpServletRequest,
                              @RequestParam(value = "person", required = false) MultipartFile person,
                              @RequestParam(value = "back", required = false) MultipartFile back,
                              @RequestParam(value = "size",required = false) String size,
                              @RequestParam(value = "degree",required = false) String degree) throws Exception {
        System.out.println("=====进入控制器=====");
        ModelAndView modelAndView = new ModelAndView();
        if (person.getSize() == 0 || back == null || "".equals(degree) || "".equals(degree) ||(!IsDig.IsDigit(degree)) ) {
            System.out.println("=====图片参数为空处理=====");
            modelAndView.setViewName("show_400");
            return modelAndView;
        }

        System.out.println("人物图片url:person变量  " + person);
        System.out.println("背景图片url:back变量  " + back);
        System.out.println("size变量  " + size);
        System.out.println("degree变量 " + degree);
        double sizeID = 0;
        double degree_num = 0;
        degree_num = Double.parseDouble(degree);
        switch (size){
            case "3.0":
                sizeID = 3.0;
                break;
            case "2.5":
                sizeID = 2.5;
                break;
            case "2.0":
                sizeID = 2.0;
                break;
            case "1.5":
                sizeID  = 1.5;
                break;
            case "1.0":
                sizeID = 1.0;
                break;
            case "0.75":
                sizeID = 0.75;
                break;
            case "0.65":
                sizeID = 0.65;
            case "0.5":
                sizeID = 0.5;
                break;
            case "0.35":
                sizeID  = 0.35;
                break;
            case "0.25":
                sizeID  = 0.25;
                break;
            case "0.15":
                sizeID = 0.15;
                break;
            default:
                sizeID = 1.0;
        }





            System.out.println("=====调用的service方法处理图片=====");


            String filename = servletContext.getRealPath("/static/images/person.jpg");
            File person_img = new File(filename);
            person.transferTo(person_img);
            System.out.println(filename);
            //用户照片下载到服务器

            String filename1 = servletContext.getRealPath("/static/images/back.jpg");
            back.transferTo(new File(filename1));
            System.out.println(filename1);
            //用户背景下载到服务器

            System.out.println("=====调用阿里云接口人像分割=====");

            System.out.println(filename);
            API.imageSeg(person_img,filename);
            one.OnePic(filename,filename1,sizeID,degree_num);

            modelAndView.setViewName("show_onepic");


        System.out.println("=====退出控制器=====");
        return modelAndView;
    }

    @RequestMapping(value = "/service_1", method = RequestMethod.POST)
    public ModelAndView second(HttpServletRequest httpServletRequest,
                              @RequestParam(value = "person", required = false) MultipartFile person,
                              @RequestParam(value = "size", required = false) String size,
                              @RequestParam(value ="color" ,required = false)String color) throws Exception {
        System.out.println("=====进入控制器=====");
        ModelAndView modelAndView = new ModelAndView();
        String spaceID;
        String bkColor;

        if (person.getSize() == 0 || "".equals(size) || "".equals(color)){

            System.out.println("=====图片参数为空处理=====");
            modelAndView.setViewName("show_400");
            return modelAndView;
        }

        System.out.println("图片url:person变量  " + person);
        System.out.println("size变量  " + size);
        System.out.println("color变量 " + color);
        switch (size){
            case "一寸":
                spaceID = "1";
                break;
            case "小一寸":
                spaceID = "2";
                break;
            case "大一寸":
                spaceID = "3";
                break;
            case "二寸":
                spaceID = "4";
                break;
            case "小二寸":
                spaceID = "5";
                break;
            case "大二寸":
                spaceID = "6";
                break;
            case "身份证":
                spaceID = "10";
                break;
            case "医保证":
                spaceID = "24";
                break;
            case "英语四六级":
                spaceID = "40";
                break;
            case "美国签证":
                spaceID = "7";
                break;
            case "韩国签证":
                spaceID = "76";
                break;
            case "日本签证":
                spaceID = "8";
                break;
            case "马拉西亚签证":
                spaceID = "82";
                break;
            case "新西兰签证":
                spaceID = "83";
                break;
            case "意大利签证":
                spaceID = "84";
                break;
            case "泰国签证":
                spaceID = "77";
                break;
            default:
                spaceID = "1";
                break;
        }
        switch (color){
            case "蓝底":
                bkColor = "blue";
                break;
            case "红底":
                bkColor = "red";
                break;
            case "白底":
                bkColor = "white";
                break;
            case "蓝色渐变":
                bkColor = "blue_gradual";
                break;
            case "红色渐变":
                bkColor = "red_gradual";
                break;
            case "灰色渐变":
                bkColor = "gray_gradual";
                break;
            default:
                bkColor = "white";
                break;
        }



            System.out.println("=====调用的service方法处理图片=====");


            String filename = servletContext.getRealPath("/static/images/pro_person.jpg");
            File person_file = new File(filename);
            person.transferTo(person_file);
            System.out.println(filename);
            //用户照片下载到服务器


            System.out.println("=====调用阿里云接口人像分割=====");

            System.out.println(filename);


            API.idPhotoMake(person_file,spaceID,bkColor,person_file);
            modelAndView.setViewName("show_professionpic");


        System.out.println("=====退出控制器=====");
        return modelAndView;
    }

    /*
        处理器方法形式参数为java对象，对象中属性值与请求名一致
        框架自动创建此接收对象的参数，给属性赋值。调用setName进行赋值。
     */
//    @RequestMapping(value = "/receiveObject", method = RequestMethod.POST)
//    public ModelAndView first(Picture picture) {
//        System.out.println("=====进入控制器=====");
//        ModelAndView modelAndView = new ModelAndView();
//
//        if ("".equals(picture.name) || "".equals(picture.age)) {
//
//            System.out.println("=====参数为空处理=====");
//            modelAndView.setViewName("show_400");
//        } else {
//
//            System.out.println("=====调用的service方法处理图片=====");
//            modelAndView.addObject("name", picture.name);
//            modelAndView.addObject("age", picture.age);
//            modelAndView.addObject("picture", picture);
//
//
//            modelAndView.setViewName("show1");
//
//        }
//        System.out.println("=====退出控制器=====");
//        return modelAndView;
//    }

    @RequestMapping(value = "/jump1")
    public String jump1(HttpServletRequest httpServletRequest) {
        System.out.println("=====进入控制器，jump1请求=====");
        ServletContext servletContext = httpServletRequest.getServletContext();
        String str = servletContext.getRealPath("/");
        System.out.println(str);

        System.out.println("=====退出控制器，jump1请求=====");
        return "show1";
    }

    @RequestMapping(value = "/jump2")
    public String jump2() {
        System.out.println("=====进入控制器，jump2请求=====");
        System.out.println("=====退出控制器，jump2请求=====");
        return "show2";
    }
}



