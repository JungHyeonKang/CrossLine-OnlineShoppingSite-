package com.green.team4.mapper.account;



import com.green.team4.vo.account.LoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public LoginVO login (LoginVO vo);

}
