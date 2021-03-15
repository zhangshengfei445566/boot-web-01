package com.zsf.boot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    @GetMapping("/car/{id}/owner/{userName}")
    public Map<String,Object> getCar(
                                    // 获取路径上面的参数car/1/owner/zhangsf
                                    @PathVariable("id")String id,
                                     @PathVariable("userName") String name,
                                     @PathVariable Map<String,String> pv,
//                                     获取header上面的信息
                                     @RequestHeader("User-Agent")String userAgent,
                                     @RequestHeader Map<String,String> header,
//                                     ?age=18&inter=篮球&inter=足球
                                     @RequestParam("age")String age,
                                     @RequestParam("inter")List<String> inter,
                                     @RequestParam Map<String,String> params
//                                     获取cookie上面的信息
                                     //@CookieValue("_ga")String _ga,
//                                     @CookieValue("_ga")Cookie cookie
                                                                        ){
        HashMap<String, Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("username",name);
//        map.put("map",pv);
//        map.put("user-agent",userAgent);
//        map.put("header",header);
        map.put("age",age);
        map.put("inter",inter);
        map.put("params",params);
//        map.put("_ga",_ga);
//        System.out.println(cookie.getName() + "---" + cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    public Map formSub(@RequestBody String content){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }
//    springboot默认是禁用矩阵变量的功能
//        手动开启: 原理： 对路径的处理。urlPathHelper进行解析
//              RemoveSemicolonContent(移除分号内容)支持矩阵变量的
//    矩阵变量必须有url路径变量才能解析
//    /cars/sell;low=34;brand=byd,audi,yd"
    @GetMapping("/cars/{path}")
    public Map CarSell(@MatrixVariable("low")Integer low,
                       @MatrixVariable("brand")List<String> brand,
                       @PathVariable("path") String path){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

   // /cars/bossId;age=24/empId;age=30
   @GetMapping("/cars/{bossId}/{empId}")
   public Map CarSell(@MatrixVariable(value = "age",pathVar = "bossId")Integer age1,
                      @MatrixVariable(value = "age",pathVar = "empId")Integer age2){
       HashMap<Object, Object> map = new HashMap<>();
       map.put("age1",age1);
       map.put("age2",age2);
       return map;
   }
}
