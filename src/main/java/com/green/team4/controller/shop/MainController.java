package com.green.team4.controller.shop;

import com.green.team4.service.shop.ShopService;
import com.green.team4.service.community.BoardService;
import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.PagingVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/mainPage")
    public String  mainPage(@ModelAttribute("cri") ItemPageCriteria cri, Model model){
        log.info("메인페이지 입장");
        log.info("cri : "+cri);
        PagingVO pagingVO = new PagingVO();
        pagingVO.setCri(cri);
        pagingVO.setTotalProductData(shopService.getTotaldatabyFind(cri));
         model.addAttribute("bestList",shopService.getListWithBest(cri));

        model.addAttribute("TDList",shopService.getListWithTimeDeal(cri));
        log.info("TDList"+shopService.getListWithTimeDeal(cri));

        model.addAttribute("boardList",boardService.readListForMain());
        log.info("boardList"+boardService.readListForMain());
        model.addAttribute("pagingVO",pagingVO);
        return "/shop/mainPage";
    }

}
