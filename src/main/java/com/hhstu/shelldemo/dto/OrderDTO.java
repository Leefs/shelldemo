package com.hhstu.shelldemo.dto;

import com.hhstu.shelldemo.dataobject.OrderDetail;
import com.hhstu.shelldemo.enums.OrderStatusEnum;
import com.hhstu.shelldemo.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 该属性供外部各层调用
 * */
@Data
public class OrderDTO {

    /** 订单Id */
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信Openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态,默认为新下单 */
    private Integer orderStatus;

    /** 支付状态，默认为0未支付 */
    private Integer payStatus;

    /** 创建时间 */
    private Date createTime;

    /**  更新时间 **/
    private Date updateTime;

    //封装OrderDetail数据表中查询的参数信息  表与表之间有关联
    List<OrderDetail> orderDetailList;
}
