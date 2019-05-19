package com.hhstu.shelldemo.service;

import com.hhstu.shelldemo.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目
 * */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
