package com.chengyu.content;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @RequestMapping("test")
    public String string(){
        return "test";
    }
}
