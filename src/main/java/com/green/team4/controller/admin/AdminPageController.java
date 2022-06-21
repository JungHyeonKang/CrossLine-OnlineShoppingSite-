package com.green.team4.controller.admin;

import com.green.team4.mapper.admin.StaticMapper;
import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.mapper.mypage.PersonalQMapper;
import com.green.team4.service.admin.MailService;
import com.green.team4.service.community.BoardService;
import com.green.team4.service.mypage.ExchangeService;
import com.green.team4.service.mypage.OrderService;
import com.green.team4.vo.admin.StaticVO;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.mypage.ExchangeVO;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.PersonalQVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Log4j2
public class AdminPageController {
    @Autowired
    BoardService boardService;
    @Autowired
    StaticMapper staticMapper;
    @Autowired
    PersonalQMapper personalQMapper;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    OrderItemMapper orderMapper;
    @GetMapping("/admin/adpage")
    public void adPage(Model model){
        log.info("AdminPageController => adPage(GET) 실행");
        StaticVO profit = staticMapper.getTotalProfit();
        StaticVO memberCnt = staticMapper.getTotalMemberCnt();
        StaticVO delMemCnt = staticMapper.getTotalDeleteMemCnt();
        StaticVO productCnt = staticMapper.getTotalProductCnt();
        List<BoardVO> boardCnt = boardService.getList();
        List<PersonalQVO> questionCnt = personalQMapper.getAllAdmin();
        List<ExchangeVO> exchangeCnt = exchangeService.readAllAdmin();
        List<OrderItemVO> normalCnt = orderMapper.getNormalDelivery();

        log.info("staticVo profit 출력: "+profit.getTotalProfit());
        log.info("staticVo memberCnt 출력: "+memberCnt.getTotalMemberCnt());
        log.info("staticVo delMemCnt 출력: "+delMemCnt.getTotalDeleteMemCnt());
        log.info("staticVo productCnt 출력: "+productCnt.getTotalProductCnt());

        model.addAttribute("profit",profit.getTotalProfit());
        model.addAttribute("memberCnt",memberCnt.getTotalMemberCnt());
        model.addAttribute("delMemCnt",delMemCnt.getTotalDeleteMemCnt());
        model.addAttribute("productCnt",productCnt.getTotalProductCnt());
        model.addAttribute("boardCnt",boardCnt.size());
        model.addAttribute("questionCnt", questionCnt.size());
        model.addAttribute("exchangeCnt", exchangeCnt.size());
        model.addAttribute("normalCnt", normalCnt.size());
    }

}
