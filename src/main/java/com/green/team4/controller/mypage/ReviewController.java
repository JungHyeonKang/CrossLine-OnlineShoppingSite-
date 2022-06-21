package com.green.team4.controller.mypage;

import com.green.team4.service.mypage.ReviewMpService;
import com.green.team4.vo.mypage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/mypage/review/*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewMpService reviewMpService;

    @GetMapping("/list") // 내가 쓴 리뷰 글 List 가져오기
    public void reviewList(int mno,
                           @ModelAttribute SearchVO searchVO,
                           Model model){
        log.info("OrderController => reviewList(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => reviewList(GET) 실행 => 받은 searchVO: "+searchVO);
        List<ReviewMpVO> reviewList = reviewMpService.readAllByMnoSearch(searchVO); // 접속 회원이 작성한 리뷰 모두 가져오기
        reviewList.forEach(i->{
            log.info(i);
        });

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("mno",mno);
        model.addAttribute("searchVO",searchVO);
    }

    @GetMapping("/read")
    public void reviewRead(int rno,Model model){ // 리뷰 글 하나 가져오기
        log.info("OrderController => reviewRead(GET) 실행 => 받은 rno: "+rno);
        ReviewMpVO reviewMpVO = reviewMpService.readOneByRno(rno);
        model.addAttribute("review",reviewMpVO);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> reviewModify(@RequestBody ReviewMpVO reviewMpVO){ // 리뷰 수정 등록
        log.info("OrderController => reviewModify(POST) 실행 => 받은 reviewMpVO: "+reviewMpVO);
        // 리뷰 수정 진행
        reviewMpVO.setRUpdatedate(LocalDateTime.now()); // 수정일 반영
        int result = reviewMpService.modify(reviewMpVO); // 리뷰 수정 등록

        // 수정 결과 저장
        String reply = "";
        if(result == 1) reply = "리뷰 수정 완료하였습니다.";
        else reply = "리뷰 수정 실패하였습니다.";

        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public String reviewRemove(ReviewMpVO reviewMpVO){ // 리뷰 삭제
        log.info("OrderController => reviewRemove(POST) 실행 => 받은 reviewMpVO: "+reviewMpVO);
        reviewMpService.remove(reviewMpVO);
        return "redirect:/mypage/review/list?mno="+reviewMpVO.getMno();
    }
}
