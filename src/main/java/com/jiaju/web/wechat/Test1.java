package com.jiaju.web.wechat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:02
 */
@RestController
@RequestMapping("/a")
public class Test1 {
    @GetMapping
    public String get(){
        return "aaa";
    }
}
