package com.green.team4.controller.mypage;

import com.github.pagehelper.PageInfo;
import com.green.team4.service.community.BoardService;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.mypage.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@Log4j2
@RequestMapping("/mypage/board/*")
@RequiredArgsConstructor
public class BoardReplyController {
    private final BoardService boardService;

    @GetMapping("/list") // 내가 쓴 게시글 글 List 가져오기
    public void reviewList(int mno,
                           @RequestParam(required = false, defaultValue = "1") int pageNum,
                           @ModelAttribute SearchVO searchVO,
                           Model model){
        PageInfo<BoardVO> boardListP = new PageInfo<>(boardService.readAllByMnoSearch(mno,pageNum,searchVO),10);
        // boardListP와 한번에 보여줄 페이지 번호 반영
        model.addAttribute("boardList",boardListP);
        model.addAttribute("mno",mno);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("searchVO",searchVO);
    }
}
