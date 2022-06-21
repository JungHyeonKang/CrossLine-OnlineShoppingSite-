package com.green.team4.service.account;


import com.green.team4.vo.account.LoginVO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public LoginVO login (LoginVO vo);
}
