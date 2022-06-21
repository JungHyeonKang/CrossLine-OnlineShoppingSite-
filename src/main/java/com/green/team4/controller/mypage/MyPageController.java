package com.green.team4.controller.mypage;

import com.green.team4.service.community.BoardService;
import com.green.team4.service.mypage.*;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.mypage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/mypage/*")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    // 의존성 주입
    private final MemberInfoService memberInfoService;
    private final InterestService interestService;
    private final CartService cartService;
    private final OrderService orderService;
    private final ExchangeService exchangeService;
    private final ReviewMpService reviewMpService;
    private final PersonalQService personalQService;
    private final BoardService boardService;

    @GetMapping("/main")
    public void getMainPage(int mno, Model model){

        // 회원 기본메뉴 정보 가져오기 ----------------------------------------------------
        log.info("MyPageController => getMainPage 실행 => 받은 mno: "+mno);
        model.addAttribute("mno",mno);

        // 회원 이름 가져오기
        MemberInfoVO memberInfoVO = memberInfoService.readOne(mno);
        String memName = memberInfoVO.getName();
        model.addAttribute("memName",memName);

        // 찜한 상품 개수 가져오기
        int itrCnt = interestService.readAllCnt(mno);
        model.addAttribute("itrCnt",itrCnt);

        // 장바구니 상품 개수 가져오기
        List<CartVO> carList = cartService.readAll(mno);
        int cartCnt = carList.size();
        model.addAttribute("cartCnt",cartCnt);

        // 주문 정보 가져오기
        // 주문 상품 개수
        int orderCnt = orderService.readAllCnt(mno);
        model.addAttribute("orderCnt",orderCnt);

        // 이번 달 적립 포인트
        List<OrderVO> orderList = orderService.readAllByThisMonth(mno);
        int thisMPoint = 0;
        for(OrderVO order : orderList) thisMPoint += order.getTSavePoint();
        model.addAttribute("thisMPoint",thisMPoint);

        // 누적 적립 포인트
        int accuPoint = 0;
        List<OrderVO> orderListdef = orderService.readAllByMno(mno);
        log.info("orderListdef: "+orderListdef);
        List<OrderVO> orderListOnlyPay = orderListdef
                .stream()
                .filter(i->i.getTPayStatus().equals("결제완료"))
                .collect(Collectors.toList());
        log.info("orderListOnlyPay: "+orderListOnlyPay);
        for (OrderVO order : orderListOnlyPay) accuPoint += order.getTSavePoint();
        model.addAttribute("accuPoint",accuPoint);

        // 취소/반품/교환 신청 건 수 가져오기
        int exCnt = exchangeService.readAllCnt(mno);
        model.addAttribute("exCnt",exCnt);

        // 상품 리뷰 작성 건 수 가져오기
        List<ReviewMpVO> reviewList = reviewMpService.readAllByMno(mno);
        int rCnt = reviewList.size();
        model.addAttribute("rCnt",rCnt);

        // 문의 글 작성 건 수 가져오기
        int pqCnt = personalQService.readAllCnt(mno);
        model.addAttribute("pqCnt",pqCnt);

        // 커뮤니티 게시글 개수 가져오기
        List<BoardVO> boardList = boardService.readAllByMno(mno);
        int bCnt = boardList.size();
        model.addAttribute("bCnt",bCnt);
    }

    @GetMapping("/map/mapRead") // 운동시설 찾기 페이지
    public void mapRead(int mno, Model model){
        log.info("MemberInfoController => mapRead(GET) 실행 => 받은 mno: "+mno);
        model.addAttribute("mno",mno);
    }
}
