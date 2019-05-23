package com.hhstu.shelldemo.service.impl;

import com.hhstu.shelldemo.dao.OrderDetailRepository;
import com.hhstu.shelldemo.dao.OrderMasterRepository;
import com.hhstu.shelldemo.dataobject.OrderDetail;
import com.hhstu.shelldemo.dataobject.OrderMaster;
import com.hhstu.shelldemo.dataobject.ProductInfo;
import com.hhstu.shelldemo.dto.CartDTO;
import com.hhstu.shelldemo.dto.OrderDTO;
import com.hhstu.shelldemo.enums.OrderStatusEnum;
import com.hhstu.shelldemo.enums.PayStatusEnum;
import com.hhstu.shelldemo.enums.ResultEnum;
import com.hhstu.shelldemo.exception.SellException;
import com.hhstu.shelldemo.service.OrderService;
import com.hhstu.shelldemo.service.ProductService;
import com.hhstu.shelldemo.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    //创建订单 112
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO); //BigDecimal():保留两位小数

       // List<CartDTO> cartDTOLists = new ArrayList<>();   //扣库存
        //1.查询商品（数量，价格） 对OrderDetail中的所有商品进行遍历
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价   放到循环里去  计算出一个值，然后循环外面再用
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId("1558591286793517954");
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

            /*CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOLists.add(cartDTO);*/
        }

        //3.写入订单数据库（OrderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
