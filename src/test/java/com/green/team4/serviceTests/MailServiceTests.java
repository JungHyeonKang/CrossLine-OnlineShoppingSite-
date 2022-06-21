package com.green.team4.serviceTests;

import com.green.team4.service.admin.MailService;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTests {
    @Autowired
    MailService mailService;

    @Autowired
    MemberInfoService memberInfoService;

    @Test
    public void send(){
        MailVO vo = new MailVO();
        vo.setMno(5);
        vo.setEmail(memberInfoService.readOne(5).getEmail());
        vo.setSubject("테스트 12:45");
        vo.setText("제발");
        mailService.sendMail(vo);
    }

}
