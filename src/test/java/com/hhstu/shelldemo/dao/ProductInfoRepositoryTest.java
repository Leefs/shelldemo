package com.hhstu.shelldemo.dao;

import com.hhstu.shelldemo.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    @Transactional
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("DD123456");
        productInfo.setProductName("肉包");
        productInfo.setProductPrice(new BigDecimal(2));
        productInfo.setProductStock(300);
        productInfo.setProductDescription("狗不理包子");
        productInfo.setProductIcon("http://www.gobuli.jpg");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    //查询商品状态
    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        //当数据表中有商品时查询成功
        Assert.assertNotEquals(0,productInfoList.size());
    }
}