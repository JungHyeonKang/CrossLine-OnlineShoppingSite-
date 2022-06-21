package com.green.team4.controller.mypage;//package com.green.team4.controller.sw;

import com.github.pagehelper.PageInfo;
import com.green.team4.service.mypage.ExchangeService;
import com.green.team4.service.mypage.OrderService;
import com.green.team4.vo.mypage.ExchangeVO;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/mypage/exchange/*")
@RequiredArgsConstructor
public class ExchangeController {

    // 의존성 주입
    private final ExchangeService exchangeService;
    private final OrderService orderService;

    @GetMapping("/list")
    public void getExList(int mno,
                          @RequestParam(required = false, defaultValue = "1") int pageNum,
                          Model model){ // 취소/반품/교환 List가져오기
        log.info("OrderController => getExList(Get) 실행 => 받은 mno: "+mno);
        log.info("OrderController => getExList(Get) 실행 => 받은 pageNum: "+pageNum);

        PageInfo<ExchangeVO> exListP = new PageInfo<>(exchangeService.readAll(mno,pageNum),10);
        log.info("exListP: "+exListP);
        model.addAttribute("mno",mno);
        model.addAttribute("exList",exListP);
        model.addAttribute("pageNum",pageNum);
    }

    @GetMapping("/read")
    public void getExOne(int eno, Model model){ // 취소/반품/교환 하나 가져오기
        log.info("OrderController => getExOne(Get) 실행 => 받은 eno: "+eno);
        ExchangeVO exchangeVO = exchangeService.readOne(eno);
        model.addAttribute("exchangeVO",exchangeVO);
    }

    @PostMapping("/delete")
    public String exDelete(int eno, int mno,int pno, String ono){ // 취소/반품/교환 하나 가져오기
        log.info("OrderController => exDelete(POST) 실행 => 받은 eno: "+eno);
        log.info("OrderController => exDelete(POST) 실행 => 받은 mno: "+mno);
        log.info("OrderController => exDelete(POST) 실행 => 받은 ono: "+ono);
        log.info("OrderController => exDelete(POST) 실행 => 받은 ono: "+pno);

        // 취소/반품/교환 데이터 삭제
        exchangeService.remove(eno);

        // 주문 상품 테이블 취소/반품/교환 신청상태 변경
        OrderVO orderVO = orderService.readOne(ono);
        List<OrderItemVO> itemList = orderVO.getOrderItemList();
        itemList.forEach(i->{
            if(i.getPno() == pno){
                i.setIExStatus("미신청");
                orderService.modifyItem(i);
            }
        });

        return "redirect:/mypage/exchange/list?mno="+mno;
    }

}
