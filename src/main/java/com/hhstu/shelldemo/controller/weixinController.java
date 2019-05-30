package com.hhstu.shelldemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class weixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("【微信支付】");
        log.info("code={}",code);
    }

}
