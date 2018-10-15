package com.chenfeng.girl.controller;

import com.chenfeng.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

    @Value("${content}")
    private String content;

    @RequestMapping(value = "/hello/{id}" ,method = RequestMethod.GET)
    public String say(@PathVariable(value = "id") Integer id){
        return girlProperties.getCupSize()+content+id;
    }

    @GetMapping(value = "tell")
    public String tell(@RequestParam(value = "name",required = false,defaultValue = "无名氏") String name){
        return "我的名字是："+name;
    }
}
