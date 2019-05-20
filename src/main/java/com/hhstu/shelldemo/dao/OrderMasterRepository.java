package com.hhstu.shelldemo.dao;

import com.hhstu.shelldemo.dataobject.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 根据买家openid查询具体信息
     * */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
