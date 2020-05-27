package com.xupt.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author pavi
 * @date 2020/5/25 15:35
 */
@RestController
public class Hello {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
