## 项目分工

- 倪樊：后端(基于SpringMVC框架开发)

- 何亚非：前端（基于Vue框架开发，Css+html+JavaScript）
- 开发环境：IDEA+JDK11 + Tomcat 9

## 已部署到云服务器地址

```
http://101.132.154.52:8080/Pic/
```

(目前服务器已过期，暂时只能本地IDEA运行)

## 主要功能

- 人像抠图和Ps合成
- 人像抠图和证件照制作
- **备注：**
  - **由于调用接口速度问题，最后显示页面可能先加载出来导致图片资源没有获取到，刷新结果页面即可。**
  - **由于阿里云提供的接口算法问题，人物过多或者人像自拍有特效遮挡会导致识别不出，抠图失败。**

## 调用接口

- 阿里云人像分割技术接口:[智能人像分割（抠图）接口【最新版】_图像识别_人工智能-云市场-阿里云 (aliyun.com)](https://market.aliyun.com/products/57124001/cmapi029992.html?spm=5176.2020520132.101.2.ecf872188CDC18#sku=yuncode2399200005)
- 已部署到云服务器上的接口剩余调用次数：29次

[![fKDPkq.png](https://z3.ax1x.com/2021/08/07/fKDPkq.png)](https://imgtu.com/i/fKDPkq)

- 备用新的接口AppKey与AppSecret：过期时间 2021-8-25

- ```
  AppKey:203963293
  AppSecret:8k7CxHLtdLOyYuAnzUxskGKJUaQyNT9U
  ```

  - 在项目的src\main\java\com\example\Service\AliAPI.java 文件中修改AppKey和AppSecret即可替换新的接口：
  - [![fKrKKg.png](https://z3.ax1x.com/2021/08/07/fKrKKg.png)](https://imgtu.com/i/fKrKKg)

## 功能演示

- 人像抠图和Ps合成

  - 首页

    - [![fKr85q.png](https://z3.ax1x.com/2021/08/07/fKr85q.png)](https://imgtu.com/i/fKr85q)

    

  - 人像抠图与PS合成板块

    - [![fKrwqJ.png](https://z3.ax1x.com/2021/08/07/fKrwqJ.png)](https://imgtu.com/i/fKrwqJ)

      

    - 输入测试图片和背景，人物放缩比例和旋转角度参数，注意参数的规范性填写，参数为空和不规范参数会进入“参数为空或不规范页面”

      

    - 错误页面处理：若图片上传为空或者输入角度为空，或者不是纯数字会进入错误页面：

      - [![fKgxPA.png](https://z3.ax1x.com/2021/08/07/fKgxPA.png)](https://imgtu.com/i/fKgxPA)

      

    - 结果页面：由于调用接口速度问题，最后显示页面可能先加载出来导致图片资源没有获取到，刷新结果页面即可。

      - [![fKgI81.png](https://z3.ax1x.com/2021/08/07/fKgI81.png)](https://imgtu.com/i/fKgI81)

  

- 人像抠图和证件照制作：

  - 首页

    - ![fKr85q.png](https://z3.ax1x.com/2021/08/07/fKr85q.png)

    

  - 进入证件照制作板块

    - [![fKWFhj.png](https://z3.ax1x.com/2021/08/07/fKWFhj.png)](https://imgtu.com/i/fKWFhj)

      

    - 错误页面处理：若图片上传为空进入错误页面

      - ![fKgxPA.png](https://z3.ax1x.com/2021/08/07/fKgxPA.png)

      

    - 结果页面：由于调用接口速度问题，最后显示页面可能先加载出来导致图片资源没有获取到，刷新结果页面即可。

      - [![fKWQN4.png](https://z3.ax1x.com/2021/08/07/fKWQN4.png)](https://imgtu.com/i/fKWQN4)



