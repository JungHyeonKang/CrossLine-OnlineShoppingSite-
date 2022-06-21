package com.green.team4.service.admin;

import com.green.team4.vo.shop.Product_optVO;

import java.util.List;

public interface ProductOptService {
    List<Product_optVO> getOpt1Names(int pno);
    List<Product_optVO> getOpt2Names(int pno);
    List<Product_optVO> getColors(int pno);
    List<Product_optVO> getOpt(int pno);
    Product_optVO findAmount(Product_optVO vo);

}
