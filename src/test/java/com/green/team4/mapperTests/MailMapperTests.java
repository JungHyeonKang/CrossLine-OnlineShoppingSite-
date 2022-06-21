package com.green.team4.mapperTests;

import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailMapperTests {
    @Autowired
    MailService mailService;

    @Autowired
    MailMapper mailMapper;

    @Autowired
    MemberInfoService memberInfoService;

    @Test
    public void insert(){
        MailVO vo = new MailVO();
        vo.setEmail(memberInfoService.readOne(5).getEmail());
        vo.setSubject("메일 디비 테스트");
        vo.setText("이게 될까");
        mailMapper.insert(vo);
    }

    @Test
    public void getOne(){
        MailVO result = mailMapper.getOne(5);
        System.out.println(result);
    }

    @Test
    public void getAll(){
        mailMapper.getAll().forEach(System.out::println);
    }

}
