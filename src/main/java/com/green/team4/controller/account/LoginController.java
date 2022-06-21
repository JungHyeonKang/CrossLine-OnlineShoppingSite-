package com.green.team4.controller.account;

import com.green.team4.mapper.mypage.MemberInfoMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.account.LoginService;


import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MailService mailService;
    public static int generateAuthNumber() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "errorMessage", required = false) String errorMessage,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        log.info("login error: "+error);
        log.info("login errorMessage: "+errorMessage);
        log.info("login exception: "+exception);
        model.addAttribute("error", error);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("exception", exception);
        return "/account/login";
    }

    @GetMapping("/denied")
    public void denied () {

    }
    @PostMapping("/logout")
    public String logout () {

        return "/account/login";
    }

    @GetMapping("/findPw")
    public void findPwGet(){

    }
    int authNum = generateAuthNumber();
    @PostMapping("/findPwAx")
    public ResponseEntity<Integer> findPwPost(@RequestBody MemberInfoVO vo) {

        MemberInfoVO findInfo = memberInfoMapper.findPw(vo);
        if(findInfo != null){
            MailVO mail = new MailVO();
            mail.setEmail(vo.getEmail());
            mail.setText("고객님의 비밀번호 찾기 인증번호가 발급되었습니다.");
            mail.setSubject("발급된 인증번호는 " + authNum + " 입니다.");
            mailService.sendMail(mail);
            System.out.println(mail.getSubject());
            int result = authNum;
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/findPw")
    public String findPw(MemberInfoVO memberInfoVO){
        log.info("findPw 실행 받은 memberInfoVO: "+memberInfoVO);
        return "redirect:/account/newPw?id="+memberInfoVO.getId();
    }


    @GetMapping("/newPw")
    public void newPwGet(String id,Model model){
        log.info("newPwGet Id: " + id);
        model.addAttribute("id",id);
    }


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/newPw")
    public String newPwPost(MemberInfoVO vo, String id){

        log.info("받아온 id: " + id);
        String rowPw = vo.getPassword();
        log.info("받아온 비밀번호: "+rowPw);
        String encodePw = bCryptPasswordEncoder.encode(rowPw);
        log.info("encodePw: " + encodePw);
        vo.setPassword(encodePw);
        memberInfoMapper.newPw(vo);
        return "redirect:/account/login";
    }

}

