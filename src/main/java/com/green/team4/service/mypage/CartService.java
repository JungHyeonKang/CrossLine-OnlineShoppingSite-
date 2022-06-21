package com.green.team4.service.mypage;

import com.green.team4.vo.mypage.CartVO;

import java.util.List;

public interface CartService {

    String register(CartVO cartVO); // 장바구니 신규 추가
    List<CartVO> readAll(int mno); // 장바구니 전체 가져오기
//    CartVO readOne(int mno, int pno); // 장바구니 상품 하나 가져오기
    CartVO modify(CartVO cartVO); // 장바구니 수정
    int remove(int cno); // 장바구니 삭제
    int removeByPno(int pno); // 장바구니 삭제(pno단위)
}
