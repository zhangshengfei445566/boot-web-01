package com.zsf.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/bug.jpg")
    public String hello(){
        return "aaa";
    }
}
