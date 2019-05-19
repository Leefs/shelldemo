package com.hhstu.shelldemo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 类目
 * */
//@Table(name = "product_category")     //当数据库表明和文件类名不同时使用Table进行映射
@Entity
@DynamicUpdate      //动态更新参数  （时间）
@Data               //自动生成get/set/toString等方法
public class ProductCategory {

    /**类目id*/
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**类目名称*/
    private String categoryName;

    /**类目编号*/
    private Integer categoryType;

    //private Date createTime;

   // private Date updateTime;


    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
