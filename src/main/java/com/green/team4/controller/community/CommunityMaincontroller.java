package com.green.team4.controller.community;

import com.green.team4.service.community.BoardService;
import com.green.team4.vo.community.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
public class CommunityMaincontroller {
    @Autowired
    BoardService boardService;
    @GetMapping("main")
    public void communityMain(Model model){
        Criteria criteria = new Criteria();
        Long rand = (long)(Math.random()*boardService.getTotal(criteria))+1;
        System.out.println("rand: "+rand);
        System.out.println("rand result: "+boardService.getOne(rand));
        model.addAttribute("VO",boardService.getOne(rand));
    }
}
