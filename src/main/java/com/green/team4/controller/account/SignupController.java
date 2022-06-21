package com.green.team4.controller.account;

import com.green.team4.mapper.account.SignupMapper;
import com.green.team4.service.account.SignupService;
import com.green.team4.service.admin.MailService;
import com.green.team4.vo.account.SignupVO;
import com.green.team4.vo.admin.MailVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/account/*")
public class SignupController {

    @Autowired
    private SignupService signupService;
    @Autowired
    private SignupMapper signupMapper;
    @Autowired
    private MailService mailService;

    public static int generateAuthNumber() { // 권한 관련 Number 생성
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }

//    @GetMapping("/signupCategory") // 회원가입 종류 화면 가져오기
//    public void signUpCategory() {
//        log.info("SignupController => signUpCategory 실행");
//    }

    @GetMapping("/signup") // 회원가입 화면 가져오기
    public void signUp() {
        log.info("SignupController => signUp 실행");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/signup")
    public String SignupUpload(SignupVO signupVO){
        System.out.println("Signup PostMapping 작동"+ signupVO);
        String rawPassword = signupVO.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        log.info("encodePassword: "+encPassword);
        signupVO.setPassword(encPassword);
        signupVO.setAuth("ROLE_MEMBER");
        signupVO.setGrade("고급회원");
        signupService.insert(signupVO);
        return "redirect:/account/login";
    }

    @GetMapping("/idCheck")
    public ResponseEntity<String> idCheck(@RequestParam String id){
        log.info("받아온 아이디 중복확인: "+id);
        String result = signupMapper.idCheck(id);
        log.info("조회된 아이디: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    int mailAuth = generateAuthNumber();
    @PostMapping("/emailCheck")
    public ResponseEntity<Integer> mailCheck(@RequestParam String email){
        log.info("받아온 이메일: "+email);
        log.info("발급된 인증번호: "+mailAuth);
        MailVO mail = new MailVO();
        mail.setEmail(email);
        mail.setSubject("고객님의 인증번호가 발급되었습니다.");
        mail.setText("발급된 인증번호는 " + mailAuth+" 입니다.");
        mailService.sendMail(mail);
        return new ResponseEntity<Integer>(mailAuth, HttpStatus.OK);
    }

}
