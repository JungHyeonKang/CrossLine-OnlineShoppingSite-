package com.green.team4.controller.mypage;

import com.green.team4.mapper.mypage.MemberInfoMapper;
import com.green.team4.service.mypage.CartService;
import com.green.team4.vo.mypage.CartVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@Log4j2
@RequestMapping("/mypage/cart/*")
@RequiredArgsConstructor
public class CartController {

    // 의존성 주입
    private final CartService cartService;
    private final MemberInfoMapper memberInfoMapper;

    @GetMapping("/list")
    public void cartRead(int mno, Model model){ // 장바구니 List 가져오기
        log.info("CartController => cartRead(GET) 실행 => 받은 mno: "+mno);
        List<CartVO> cartList = cartService.readAll(mno);
        int cartTotalPrice = 0;
        for(CartVO cart : cartList) cartTotalPrice += cart.getCTotalPrice();
        cartList.forEach(i->log.info(i));

        model.addAttribute("mno",mno);
        model.addAttribute("cartList",cartList); // 장바구니 List
        model.addAttribute("cartTotalPrice",cartTotalPrice); // 장바구니 물건 총 금액
    }

    @PostMapping("/listCnt") // 모든 페이지 상단 카트 아이콘 업데이트용
    public ResponseEntity<Integer> getCartCnt(@RequestBody CartVO cartVO){
        log.info("CartController => getCartCnt(POST) 실행 => 받은 mno: "+cartVO.getMno());
        List<CartVO> cartList = cartService.readAll(cartVO.getMno());
        Integer cartCnt = cartList.size();
        return new ResponseEntity<>(cartCnt,HttpStatus.OK);
    }

    @PostMapping("/listMain") // 카트 상단 메뉴 클릭하면 데이터 가져오기
    public ResponseEntity<List<List<CartVO>>> cartListForMain(@RequestBody MemberInfoVO memberInfoVO){
        log.info("CartController => cartListForMain(POST) 실행 => 받은 id: "+memberInfoVO.getId());
        // CartList
        List<List<CartVO>> cartListWithCnt = new ArrayList<>();

        // 해당 회원 장바구니 모두 가져오기
        MemberInfoVO member = memberInfoMapper.findByUsername(memberInfoVO.getId());
        List<CartVO> cartList = cartService.readAll(member.getMno());
        log.info("cartList from controller: "+cartList);

        // 최근 장바구니에 담은 물건 2개만 가져오기
        List<CartVO> recentCartList = new ArrayList<>();
        if(cartList.size()>=1){ // cart가 있으면
            log.info("cartList 있는 경우 접근");
            if(cartList.size()>=2){
                IntStream.rangeClosed(0,1).forEach(i->{
                    recentCartList.add(cartList.get(i));
                });
            }
            else {
                recentCartList.add(cartList.get(0));
            }
        }
        else { // cart가 없으면
            log.info("cartList 없는 경우 접근");
            CartVO cartVO = new CartVO();
            cartVO.setStatus("없음");
            recentCartList.add(cartVO);

        }

        // 장바구니 상품 개수 가져오기
        int cartCnt = cartList.size();

        // 장바구니 상품 개수 + mno 담긴 CartVO List
        List<CartVO> cartCntList = new ArrayList<>();
        CartVO cart = new CartVO();
        cart.setMno(member.getMno());
        cart.setCartCnt(cartCnt);
        cartCntList.add(cart);

        // List set
        cartListWithCnt.add(recentCartList);
        cartListWithCnt.add(cartCntList);

        return new ResponseEntity<>(cartListWithCnt,HttpStatus.OK);
    }

    @PostMapping("/modify") // 장바구니 업데이트 하기
    public ResponseEntity<CartVO> cartModify(@RequestBody CartVO cartVO){
        log.info("CartController => cartModify(POST) 실행 => 받은 cartVO: "+cartVO);

        // 장바구니 업데이트 서비스 호출
        CartVO updateCart = cartService.modify(cartVO); // 업데이트 후 수정된 장바구니 상품 가져오기
        log.info("업데이트된 장바구니 상품: "+updateCart);

        return new ResponseEntity<>(updateCart,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> cartRegister(@RequestBody CartVO cartVO){ // 장바구니 신규 추가
        log.info("CartController => cartRegister(POST) 실행 => 받은 cartVO: "+cartVO);
        String addResult = cartService.register(cartVO);
        return new ResponseEntity<>(addResult, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public String cartDelete(CartVO cartVO){ // 장바구니에서 상품 삭제
        log.info("CartController => cartDelete(POST) 실행 => 받은 CartVO: "+cartVO);
        cartService.remove(cartVO.getCno());
        return "redirect:/mypage/cart/list?mno="+cartVO.getMno();
    }
}
