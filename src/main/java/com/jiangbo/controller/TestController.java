package com.jiangbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "hello")
    public String hello(){
        return "王婷是小豆豆啊啊啊啊";
    }
}
