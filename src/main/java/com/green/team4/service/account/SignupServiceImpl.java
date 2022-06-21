package com.green.team4.service.account;

import com.green.team4.mapper.account.SignupMapper;
import com.green.team4.vo.account.SignupVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SignupServiceImpl implements SignupService {

    @Autowired
    private SignupMapper signupMapper;

    @Override
    public int insert(SignupVO vo) {
        signupMapper.insert(vo);
        System.out.println("SignupServiceimpl PostMapping에서 작동"+vo);
        return 0;
    }
}
