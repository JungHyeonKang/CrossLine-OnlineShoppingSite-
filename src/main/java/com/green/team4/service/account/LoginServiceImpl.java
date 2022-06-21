package com.green.team4.service.account;

import com.green.team4.mapper.account.LoginMapper;
import com.green.team4.vo.account.LoginVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2

public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginVO login(LoginVO vo) {
        LoginVO result = loginMapper.login(vo);
        System.out.println("db 에서 가져온데이터 " +result);
        return result;
    }
}
