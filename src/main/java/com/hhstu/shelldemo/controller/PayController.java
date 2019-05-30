package com.hhstu.shelldemo.controller;

import com.hhstu.shelldemo.dto.OrderDTO;
import com.hhstu.shelldemo.enums.ResultEnum;
import com.hhstu.shelldemo.exception.SellException;
import com.hhstu.shelldemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){

        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);

        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起微信支付
    }
}
