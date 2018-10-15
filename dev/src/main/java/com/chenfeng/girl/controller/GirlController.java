package com.chenfeng.girl.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.chenfeng.girl.repository.GirlRepository;
import com.chenfeng.girl.domains.Girl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger log = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    @GetMapping(value = "/addGirl")
    public Girl addGirl(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("添加信息报错，{}",bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());
        return girlRepository.save(girl);

    }

    @GetMapping(value = "/find/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        //这两种写法都是OK的
//        Optional<Girl> byId = girlRepository.findById(id);
//        if(byId.isPresent()){
//            return byId.get();
//        }else{
//            return null;
//        }
        return girlRepository.findById(id).orElse(null);
    }

    @DeleteMapping(value = "/del/{id}")
    public void girlDelOne(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    @GetMapping(value = "/find/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

}
