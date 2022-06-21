package com.green.team4.controller.admin;

import com.github.pagehelper.PageInfo;
import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.paging.PagingEntity;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/mail/*")
@Log4j2
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final MailMapper mailMapper;
    private final MemberInfoService memberInfoService;

    @GetMapping("/write")
    public void write(@RequestParam @Nullable int mno, Model model){
        MemberInfoVO member = memberInfoService.readOne(mno);
        model.addAttribute("member", member);
    }
    @GetMapping("/writeDirect")
    public void writeD(){

    }

    @PostMapping("/write")
    public String writePost(Model model, int mno, String subject, String text){
        MailVO vo = new MailVO();
        vo.setMno(mno);
        vo.setEmail(memberInfoService.readOne(mno).getEmail());
        vo.setSubject(subject);
        vo.setText(text);
        mailService.sendMail(vo);
        return "redirect:/admin/mail/list";
    }

    @GetMapping("/list")
    public void list(Model model, @RequestParam(required = false, defaultValue = "1") int pageNum){
        PageInfo<MailVO> list = new PageInfo<>(mailService.page(pageNum), 10);
        model.addAttribute("list", list);
        model.addAttribute("pageNum",pageNum);
    }

}
