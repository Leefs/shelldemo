package com.hhstu.shelldemo.dao;

import com.hhstu.shelldemo.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    //查询数据库中的数据
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
