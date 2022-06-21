package com.green.team4.controller.admin;

import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.service.mypage.DeliveryService;
import com.green.team4.service.mypage.ExchangeService;
import com.green.team4.service.mypage.OrderService;
import com.green.team4.vo.mypage.DeliveryVO;
import com.green.team4.vo.mypage.ExchangeVO;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/admin/order/*")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final ExchangeService exchangeService;


    // 전체 종합 관리 --------------------------------------------------------------------------------
    @GetMapping("/list") // 전체 종합 관리 페이지
    public void orderList(Model model){ // 주문목록 모두 가져오기
        // 주문 상품 목록 가져오기
        log.info("AdminOrderController => orderList(GET) 실행");
        List<OrderItemVO> itemList = orderItemMapper.getAllAdmin();
        model.addAttribute("itemList",itemList);
    }


    // 일반 주문 관리 --------------------------------------------------------------------------------
    @GetMapping("/normalList") // 일반 주문 관리 페이지
    public void normalList(Model model){
        log.info("AdminOrderController => normalList(GET) 실행");

        // 일반 주문 가져오기(배송전/미신청 건만)
        List<OrderItemVO> normalList = orderItemMapper.getNormalDelivery();
        model.addAttribute("normalList",normalList);

    }

    // 취소 신청 관리 --------------------------------------------------------------------------------

    @GetMapping("/cancelList") // 취소 관리 페이지
    public void cancelList(Model model){
        log.info("AdminOrderController => cancelList(GET) 실행");

        // 취소/반품/교환 신청 목록 가져오기
        List<ExchangeVO> exchangeList = exchangeService.readAllAdmin();
        log.info("exchangeList: "+exchangeList);

        // 취소신청 건만 별도 저장
        List<ExchangeVO> cancelList = new ArrayList<>();
        exchangeList.forEach(exchangeVO -> {
            if(exchangeVO.getExCategory().equals("취소")){
                cancelList.add(exchangeVO);
            }
        });
        log.info("cancelList: "+cancelList);

        model.addAttribute("cancelList",cancelList);
    }

    @PostMapping("/cancelReg") // 취소/반품 진행 (같이 사용)
    public String cancelReg(String ono, int oINo, int pno, int eno, String category){
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 ono: "+ono);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 oINo: "+oINo);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 ono: "+pno);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 eno: "+eno);
        log.info("AdminOrderController => cancelReg(POST) 실행 => 받은 category: "+category);

        exchangeService.cancelAndReturn(ono,oINo,pno,eno,category);

        ExchangeVO exchangeVO = exchangeService.readOne(eno);
        if(exchangeVO.getExCategory().equals("취소")){
            return "redirect:/admin/order/cancelList";
        }
        else {
            return "redirect:/admin/order/returnList";
        }
    }

    // 반품 신청 관리 --------------------------------------------------------------------------------

    @GetMapping("/returnList") // 반품 관리 페이지 => 반품 처리는 취소 처리 Service 동일하게 적용(cancelReg)
    public void returnList(Model model){
        log.info("AdminOrderController => returnList(GET) 실행");

        // 취소/반품/교환 신청 목록 가져오기
        List<ExchangeVO> exchangeList = exchangeService.readAllAdmin();
        log.info("exchangeList: "+exchangeList);

        // 반품신청 건만 별도 저장
        List<ExchangeVO> returnList = new ArrayList<>();
        exchangeList.forEach(exchangeVO -> {
            if(exchangeVO.getExCategory().equals("반품")){
                returnList.add(exchangeVO);
            }
        });
        log.info("returnList: "+returnList);

        model.addAttribute("returnList",returnList);
    }

    // 교환 관리 --------------------------------------------------------------------------------

    @GetMapping("/changeList") // 교환 관리 페이지
    public void changeList(Model model){
        log.info("AdminOrderController => changeList(GET) 실행");

        // 취소/반품/교환 신청 목록 가져오기
        List<ExchangeVO> exchangeList = exchangeService.readAllAdmin();
        log.info("exchangeList: "+exchangeList);

        // 교환신청 건만 별도 저장
        List<ExchangeVO> changeList = new ArrayList<>();
        exchangeList.forEach(exchangeVO -> {
            if(exchangeVO.getExCategory().equals("교환")){
                changeList.add(exchangeVO);
            }
        });
        log.info("changeList: "+changeList);

        model.addAttribute("changeList",changeList);
    }

    @PostMapping("/changeReg")
    public ResponseEntity<String> changeReg(@RequestBody ExchangeVO exchangeVO){ // 교환 처리 진행
        log.info("AdminOrderController => changeReg(POST) 실행 => 받은 exchangeVO: "+exchangeVO);
        log.info("AdminOrderController => changeReg(POST) 실행 => 받은 ono: "+exchangeVO.getOno());
        log.info("AdminOrderController => changeReg(POST) 실행 => 받은 pno: "+exchangeVO.getPno());
        log.info("AdminOrderController => changeReg(POST) 실행 => 받은 eno: "+exchangeVO.getEno());
        log.info("AdminOrderController => changeReg(POST) 실행 => 받은 oINo: "+exchangeVO.getOINo());

        // 교환 처리 후 새로발행한 주문서
        OrderVO newOrderVO = exchangeService.change(exchangeVO.getOno(),
                                                    exchangeVO.getOINo(),
                                                    exchangeVO.getPno(),
                                                    exchangeVO.getEno());

        return new ResponseEntity<>(newOrderVO.getOno(), HttpStatus.OK);
    }


    // 배송 처리 --------------------------------------------------------------------------------
    @GetMapping("/deliveryReg") // 배송 신규등록 화면
    public void deliveryRegister(
            @RequestParam(value = "ono", required = false) String ono,
            @RequestParam(value = "oINo", required = false) Integer oINo,
            @RequestParam(value = "pno", required = false) Integer pno,
            Model model){
        log.info("DeliveryController => deliveryRegister(GET) 실행 => 받은 oINo(일반배송 신청시 넘어옴): "+oINo);
        log.info("DeliveryController => deliveryRegister(GET) 실행 => 받은 pno(교환배송 신청시 넘어옴): "+pno);
        log.info("DeliveryController => deliveryRegister(GET) 실행 => 받은 ono: "+ono);

        // 해당 주문 건의 mno 가져오기
        OrderVO orderVO = orderService.readOne(ono);
        int mno = orderVO.getMno();

        // 주문 상품번호 가져오기
        if(oINo == null) { // 주문상품번호를 안받아왔다면(교환 페이지에서 넘어온 경우)
            OrderVO order = orderService.readOne(ono); // 해당 주문서 가져오기
            order.getOrderItemList().forEach(item->{
                if(item.getPno()==pno){
                    int getOINo = item.getOINo();
                    model.addAttribute("oINo",getOINo);
                    log.info("별도로 가져온 oINo"+getOINo);
                }
            });
        }
        else model.addAttribute("oINo",oINo); // 주문상품번호를 받아왔다면(일반 배송 페이지에서 넘어온 경우)

        model.addAttribute("mno",mno);
        model.addAttribute("ono",ono);
    }

    @PostMapping("/deliveryReg") // 배송 신규등록 진행
    public String deliveryRegister(DeliveryVO deliveryVO){
        log.info("DeliveryController => deliveryRegister(POST) 실행 => 받은 deliveryVO: "+deliveryVO);

        // 배송 데이터 신규 등록
        deliveryService.register(deliveryVO);

        // 해당 주문상품 데이터 배송상태 변경
        OrderItemVO orderItemVO = orderItemMapper.getOne(deliveryVO.getOINo());
        orderItemVO.setIDeliveryStatus("배송준비중");
        orderItemMapper.update(orderItemVO);
        return "redirect:/admin/order/normalList";
    }

}
