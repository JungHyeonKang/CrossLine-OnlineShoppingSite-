package com.green.team4.mapper.admin;

import com.green.team4.vo.shop.Product_optVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductOptMapper {
    int insert(Product_optVO vo);
    List<Product_optVO> getAll();
    List<Product_optVO> getProductOption(int pno);
    List<Product_optVO> getColors(int pno);
    List<Product_optVO> getOpt(int pno);
    int updateAmt(Product_optVO vo); // 재고만 업데이트 (취소/반품/교환)
    int update(Product_optVO vo);
    int delete(int pno);
    void updateOption1();
    void updateOption2();
    void updateColor();
    Product_optVO findAmount(Product_optVO vo);
}
