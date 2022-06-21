package com.green.team4.controller.admin;

import com.github.pagehelper.PageInfo;
import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.admin.MailVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/member/*")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberInfoService memberInfoService;
    private final MailMapper mailMapper;

    @GetMapping("/member")
    public void memberManage(Model model,
                             @RequestParam(required = false, defaultValue = "1") int pageNum){
        PageInfo<MemberInfoVO> memberList = new PageInfo<>(memberInfoService.readAll(pageNum),10);
//        model.addAttribute("getOne", memberInfoService.readOne(mno));
        model.addAttribute("list", memberList);
        model.addAttribute("pageNum",pageNum);
    }

    @GetMapping("/modify")
    public void getModify(Model model, @RequestParam("mno") int mno){
        model.addAttribute("getOne", memberInfoService.readOne(mno));
    }

    @PostMapping("/modify")
    public String memberModify(MemberInfoVO memberInfoVO, Model model){
        log.info("수정"+memberInfoVO);
        model.addAttribute("memberVO", memberInfoVO);
        MailVO mvo = new MailVO();
        memberInfoService.modifyByAdmin(memberInfoVO);

        return "redirect:/admin/member/member";
    }
    @PostMapping("/remove")
    public String MemberRemove(int mno,String delCategory, String delContent){
        memberInfoService.remove(mno,delCategory,delContent);
        log.info(mno+"번 회원 삭제");
        return "redirect:/admin/member/member?mno=1";
    }
}
