package com.green.team4.controller.mypage;

import com.github.pagehelper.PageInfo;
import com.green.team4.mapper.admin.ProductOptMapper;
import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.service.mypage.ExchangeService;
import com.green.team4.service.mypage.OrderService;
import com.green.team4.service.mypage.ReviewMpService;
import com.green.team4.vo.shop.Product_optVO;
import com.green.team4.vo.mypage.ExchangeVO;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.OrderVO;
import com.green.team4.vo.mypage.ReviewMpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/mypage/order/*")
@RequiredArgsConstructor
public class OrderController {

    // 의존성 주입
    private final OrderService orderService;
    private final ExchangeService exchangeService;
    private final ProductOptMapper productOptMapper;
    private final OrderItemMapper orderItemMapper;
    private final ReviewMpService reviewMpService;


    // Order -----------------------------------------------------------------------------------------

    @GetMapping("/list") // 주문목록 모두 가져오기
    public void orderList(int mno,
                          @RequestParam(required = false, defaultValue = "1") int pageNum,
                          Model model){

        // 주문 목록 가져오기
        log.info("OrderController => orderList(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => orderList(GET) 실행 => 받은 pageNum: "+pageNum);
        PageInfo<OrderVO> orderListP = new PageInfo<>(orderService.readAll(mno,pageNum),10);
        log.info("orderListP: "+orderListP);

        model.addAttribute("orderList",orderListP);
        model.addAttribute("mno",mno);
        model.addAttribute("pageNum",pageNum);
    }

    @GetMapping("/read")
    public void readOne(String ono, Model model){ // 주문 세부내용 가져오기

        // 주문 내역서 가져오기
        log.info("OrderController => readOne(GET) 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderService.readOne(ono);
        log.info("OrderController => readOne(GET) 실행 후 받은 orderVO: "+orderVO);
        model.addAttribute("orderVO",orderVO);
    }

    // Review -----------------------------------------------------------------------------------------

    @GetMapping("/regReview") // 리뷰 신규 등록 페이지 가져오기
    public void regReview(int mno, int oINo, Model model){
        log.info("OrderController => regReview(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => regReview(GET) 실행 => 받은 oINo: "+oINo);

        // 리뷰 등록 대상 상품정보 가져오기
        OrderItemVO orderItemVO = orderItemMapper.getOne(oINo); // 주문상품 정보
        OrderVO orderVO = orderService.readOne(orderItemVO.getOno()); // 주문서
        LocalDateTime orderDate = orderVO.getOrderDate(); // 구매일자

        model.addAttribute("mno",mno);
        model.addAttribute("orderDate",orderDate);
        model.addAttribute("orderItemVO",orderItemVO);
    }

    @PostMapping("/regReview") // 리뷰 신규 등록 진행
    public String regReview(ReviewMpVO reviewMpVO){
        log.info("OrderController => regReview(POST) 실행 => 받은 reviewVO: "+reviewMpVO);
        reviewMpService.register(reviewMpVO);
        return "redirect:/mypage/main?mno="+reviewMpVO.getMno();
    }

    // Exchange -----------------------------------------------------------------------------------------

    @GetMapping("/exchange")
    public void exRegister(int mno, String ono, int pno, int oINo, Model model){ // 취소/반품/교환 등록 페이지 가져오기
        log.info("OrderController => exRegister(GET) 실행 => 받은 mno: "+mno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 ono: "+ono);
        log.info("OrderController => exRegister(GET) 실행 => 받은 pno: "+pno);
        log.info("OrderController => exRegister(GET) 실행 => 받은 oINo: "+oINo);

        // (1) 기존 주문상품 정보 가져오기
//        OrderVO orderVO = orderService.readOne(ono); // 주문서 가져오기
//        List<OrderItemVO> itemList = orderVO.getOrderItemList(); // 주문 상품 List 가져오기
//        itemList.forEach(i->{ // 신청 대상 아이템 model 등록
//            if(i.getPno()==pno) model.addAttribute("orderItem",i);
//        });
        OrderItemVO orderItemVO = orderItemMapper.getOne(oINo); // 신청 대상 주문상품 정보 가져오기
        model.addAttribute("orderItem",orderItemVO);
        model.addAttribute("mno",mno);

        // (2) 변경 주문 옵션 display용 데이터 가져오기
        List<Product_optVO> optList = productOptMapper.getProductOption(pno); // 해당 상품 옵션정보 가져오기

        // (3) 해당 상품 각 주문옵션 데이터 담을 List
        List<String> opt1List = new ArrayList<>();
        List<String> opt2List = new ArrayList<>();
        List<String> colorList = new ArrayList<>();

        // (4) 가져온 주문 옵션 List에서 하나씩 꺼내서 옵션별로 해당 List에 담기
        optList.forEach(opt->{
            opt1List.add(opt.getPOption());
            opt2List.add(opt.getPOption2());
            colorList.add(opt.getPColor());
        });

        // (5) (4)순서에서 배분한 각 List에 중복 값 제거
        List<String> opt1ListN = opt1List.stream().distinct().collect(Collectors.toList());
        List<String> opt2ListN = opt2List.stream().distinct().collect(Collectors.toList());
        List<String> colorListN = colorList.stream().distinct().collect(Collectors.toList());

        opt1ListN.forEach(i->log.info(i));
        opt2ListN.forEach(i->log.info(i));
        colorListN.forEach(i->log.info(i));

        model.addAttribute("itemOptionList",optList);
        model.addAttribute("opt1ListN",opt1ListN);
        model.addAttribute("opt2ListN",opt2ListN);
        model.addAttribute("colorListN",colorListN);

    }

    @PostMapping("/exchange")
    public String exRegister(ExchangeVO exchangeVO){
        log.info("OrderController => exRegister(POST) 실행 => 받은 exchangeVO: "+exchangeVO);

        // 주문 상품 취소/반품/교환 신청 여부 내역 수정
        OrderItemVO orderItemVO = orderItemMapper.getOne(exchangeVO.getOINo()); // 해당 주문상품 가져오기
        orderItemVO.setIExStatus("신청완료"); // 신청완료 상태로 업데이트
        orderService.modifyItem(orderItemVO); // 주문서 내 주문상품 수정

        // 취소/반품/교환 신청 데이터 신규추가
        exchangeVO.setExDate(LocalDateTime.now()); // 신청날짜 업데이트
        exchangeService.register(exchangeVO); // DB저장

        return "redirect:/mypage/order/read?ono="+exchangeVO.getOno();
    }



}
