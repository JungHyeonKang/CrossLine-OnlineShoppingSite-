package com.green.team4.controller.mypage;

import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.service.mypage.DeliveryService;
import com.green.team4.vo.mypage.DeliveryVO;
import com.green.team4.vo.mypage.OrderItemVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/mypage/delivery/*")
@RequiredArgsConstructor
public class DeliveryController {

    // 의존성 주입
    private final DeliveryService deliveryService;
    private final OrderItemMapper orderItemMapper;

    @GetMapping("/read") // 배송 정보 가져오기
    public void deliveryRead(int oINo, Model model){
        log.info("DeliveryController => deliveryRead(GET) 실행 => 받은 oIno: "+oINo);
//        log.info("DeliveryController => deliveryRead(GET) 실행 => 받은 dCategory: "+dCategory);

        // 주문 상품 정보 가져오기
        OrderItemVO orderItemVO = orderItemMapper.getOne(oINo);
        model.addAttribute("item",orderItemVO);

        DeliveryVO deliveryVO = deliveryService.readOneByOINoDCat(oINo);
        model.addAttribute("deliveryVO",deliveryVO);
    }

}
