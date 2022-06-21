package com.green.team4.mapper.account;



import com.green.team4.vo.account.SignupVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    public int insert(SignupVO vo);
    String idCheck(String id);
}
