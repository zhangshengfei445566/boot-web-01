package com.zsf.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String Goto(HttpServletRequest request){
        request.setAttribute("name","zhangsf");
        request.setAttribute("age",24);

        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map Success(@RequestAttribute("name")String name,
                       @RequestAttribute("age")String age,
                       HttpServletRequest request){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        Object name1 = request.getAttribute("name");
        Object age1 = request.getAttribute("age");
        System.out.println(name1 + "---" + age1);
        return map;
    }

}
