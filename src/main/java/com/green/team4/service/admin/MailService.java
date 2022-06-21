package com.green.team4.service.admin;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.mapper.mypage.MemberInfoMapper;
import com.green.team4.vo.admin.MailVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private final MemberInfoMapper memberInfoMapper;
    private final MailMapper mailMapper;

    public int sendMail(MailVO vo){
        //수신 대상을 담을 ArrayList
        ArrayList<String> toUserList = new ArrayList<>();

        //수신 대상 추가
        toUserList.add(vo.getEmail());
        toUserList.forEach(i-> System.out.println("수신 대상 리스트: "+i));

        //수신 대상 개수
        int toUserSize = toUserList.size();
        System.out.println("수신 대상 개수: "+toUserSize);

        //SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 떄 이용)
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        //수신자 설정
        simpleMailMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        
        //메일 제목
        simpleMailMessage.setSubject(vo.getSubject());

        //메일 내용
        simpleMailMessage.setText(vo.getText());

        //메일 발송
        javaMailSender.send(simpleMailMessage);

        return mailMapper.insert(vo);
    }
    public List<MailVO> page(int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<MailVO> mailList = mailMapper.getAll();
        mailList.forEach(System.out::println);
        return mailList;
    }
}
