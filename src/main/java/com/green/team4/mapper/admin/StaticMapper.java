package com.green.team4.mapper.admin;

import com.green.team4.vo.admin.ProductVO;
import com.green.team4.vo.admin.StaticVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StaticMapper {
    StaticVO getTotalProfit();
    StaticVO getTotalMemberCnt();
    StaticVO getTotalDeleteMemCnt();
    StaticVO getTotalProductCnt();
}
