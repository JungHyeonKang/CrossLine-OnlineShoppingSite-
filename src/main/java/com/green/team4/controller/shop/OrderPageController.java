package com.green.team4.controller.shop;

import com.green.team4.service.shop.OrderPageService;

import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.shop.DBOrderVO;
import com.green.team4.vo.shop.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class OrderPageController {

    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private OrderPageService orderPageService;

    @GetMapping("/order/{mno}")
    public String orderPageGet(@PathVariable("mno") int mno, OrderPageVO orders, Model model){
        System.out.println("order입장");
        log.info("mno : "+mno);
        log.info("orders : "+orders.getOrders());


        model.addAttribute("orderlist",orderPageService.getProductListInfo(orders.getOrders()));
        model.addAttribute("memberInfo",memberInfoService.getMemberInfo(mno));
        return "shop/orderSheet";
    }

    @PostMapping("/order")
    public String orderPagePost(DBOrderVO vo,Model model ){
        System.out.println("orderPagePost입장");
        log.info("DBorderVO : " + vo);
        log.info("vo"+vo.getOrders());
        orderPageService.order(vo);
        model.addAttribute("order",vo);
        return "shop/orderCompleted";
    }
}
