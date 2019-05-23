package com.hhstu.shelldemo.controller;

import com.hhstu.shelldemo.VO.ProductInfoVO;
import com.hhstu.shelldemo.VO.ProductVO;
import com.hhstu.shelldemo.VO.ResultVO;
import com.hhstu.shelldemo.dataobject.ProductCategory;
import com.hhstu.shelldemo.dataobject.ProductInfo;
import com.hhstu.shelldemo.service.CategoryService;
import com.hhstu.shelldemo.service.ProductService;
import com.hhstu.shelldemo.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * */
@RestController
@RequestMapping("/buyer/product")
public class




BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * API最外层
     * */
    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有的上架商品
        List<ProductInfo> productInfoList= productService.findUpAll();

        //2.查询类目（一次性查询）
        //List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        /*for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //精简方法（Java8,lambda）
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList =  categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

       // ResultVO resultVO = new ResultVO();
       /* ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();*/

        //将productInfoVO的list集合对象放入到 productVO.setProductInfoVOList方法中去。
        //productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        //将productVO的对象放入到resultVO.setData方法中去。
        //resultVO.setData(Arrays.asList(productVO));

       /* resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("成功");*/



        return ResultVOUtil.success(productVOList);
    }


}
