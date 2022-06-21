package com.green.team4.controller.admin;//package com.green.team4.controller.sw;

import com.github.pagehelper.PageInfo;
import com.green.team4.service.mypage.PersonalQService;
import com.green.team4.vo.mypage.PersonalQVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Log4j2
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class CSAdminController {

    private final PersonalQService personalQService;


    @GetMapping("/personalQ/listM") // 1:1문의글 전체 목록 가져오기
    public void pQListAdmin(
                       @RequestParam(required = false, defaultValue = "1") int pageNum,
                       Model model){
        log.info("OrderController => pQListAdmin(Get) 실행 => 받은 pageNum: "+pageNum);
        PageInfo<PersonalQVO> personalQList = new PageInfo<>(personalQService.readAllAdmin(pageNum),10);

        model.addAttribute("pqList",personalQList);
        model.addAttribute("pageNum",pageNum);
    }

    @GetMapping("/personalQ/replyM") // 1:1문의글 답변 화면 가기
    public void pQReplyAdmin(int pqNo, Model model){
        log.info("OrderController => pQReplyAdmin(Get) 실행 => 받은 pqNo: "+pqNo);
        PersonalQVO personalQVO = personalQService.readOne(pqNo);

        model.addAttribute("pqVO",personalQVO);
    }

    @PostMapping("/personalQ/replyM") // 1:1문의글 답변 업데이트 진행
    public String pQReplyAdmin(int pqNo, String pqReplyTitle, String pqReplyContent){
        log.info("OrderController => pQReplyAdmin(POST) 실행 => 받은 pqNo: "+pqNo);
        log.info("OrderController => pQReplyAdmin(POST) 실행 => 받은 pqReplyTitle: "+pqReplyTitle);
        log.info("OrderController => pQReplyAdmin(POST) 실행 => 받은 pqReplyContent: "+pqReplyContent);

        PersonalQVO personalQVO = personalQService.readOne(pqNo); // 해당 문의글 가져오기
        personalQVO.setPqReplyDate(LocalDateTime.now()); // 답변일 업데이트
        personalQVO.setPqReplyTitle(pqReplyTitle); // 답변제목 업데이트
        personalQVO.setPqReplyContent(pqReplyContent); // 답변내용 업데이트
        personalQVO.setPqStatus("답변완료");

        log.info("수정될 personalQVO: "+personalQVO);
        personalQService.modify(personalQVO);

        return "redirect:/admin/personalQ/listM";
    }





}
