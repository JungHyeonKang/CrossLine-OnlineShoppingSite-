package com.green.team4.mapper.shop;

import com.green.team4.vo.shop.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public List<CategoryVO> cateList();
    public CategoryVO getCateName(String code);

    public List<CategoryVO> getCateCode1();
    public List<CategoryVO> getCateCode2();

    public List<CategoryVO> getCateTier2();
    public List<CategoryVO> getCateTier3WithCode(String code);

    public List<CategoryVO> getCateTier3( );


}
