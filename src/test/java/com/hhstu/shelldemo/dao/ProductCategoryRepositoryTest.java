package com.hhstu.shelldemo.dao;

import com.hhstu.shelldemo.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 查询方法测试
     * */
    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    /**
     * 添加方法测试
     * */
    @Test
    public void saveTest(){
        //ProductCategory productCategory = repository.findOne(2);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    /**
     * 测试更新方法
     * */
    @Test
    public void updateTest(){
        ProductCategory productCategory = new ProductCategory();
        //获取更新数据的id
        productCategory.setCategoryId(4);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory);
    }

    @Test
    @Transactional  //测试成功后测试数据回滚    不存入数据库中
    public void addTest2(){
        ProductCategory productCategory = new ProductCategory("男生最爱",5);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,4,5);

        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        //当查询条数不为0时说明查询成功
        Assert.assertNotEquals(0,result.size());
    }
}