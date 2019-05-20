package com.hhstu.shelldemo.dao;

import com.hhstu.shelldemo.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1223424");
        orderDetail.setOrderId("234567");
        //rderDetail.setProductIcon("http://pingguo.jpg");
        orderDetail.setProductId("11343544");
        orderDetail.setProductName("苹果");
        orderDetail.setProductPrice(new BigDecimal(5));
        orderDetail.setProductQuantity(3);
        orderDetail.setProductIcon("http://pingguo.jpg");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() throws Exception{
        List<OrderDetail> orderDetailList = repository.findByOrderId("234567");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}