package com.hhstu.shelldemo.service;

import com.hhstu.shelldemo.dto.OrderDTO;

/**
 * 支付
 * */
public interface PayService {

    void create(OrderDTO orderDTO);
}
