package com.hhstu.shelldemo.converter;

import com.hhstu.shelldemo.dataobject.OrderMaster;
import com.hhstu.shelldemo.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器：
 * 将OrderMaster转成OrderDTO
 * */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)
        ).collect(Collectors.toList());
    }
}
