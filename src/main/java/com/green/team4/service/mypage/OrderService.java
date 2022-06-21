package com.green.team4.service.mypage;

import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.OrderVO;

import java.util.List;

public interface OrderService {

    int readAllCnt(int mno); // 회원 주문 목록 개수 가져오기
    List<OrderVO> readAll(int mno, int pageNum); // 주문목록 전체 가져오기
    OrderVO readOne(String ono); // 주문목록 하나 가져오기
    List<OrderVO> readAllAdmin(); // 관리자 전체목록 가져오기
    int modifyItem(OrderItemVO orderItemVO); // 주문상품 업데이트
    int modifyStatus(OrderVO orderVO); // 주문상태 업데이트
    int register(OrderVO orderVO); // 취소/반품/교환 새주문서 등록
    List<OrderVO> readAllByThisMonth(int mno);
    List<OrderVO> readAllByMno(int mno); // mno 기준으로 모든 주문 가져오기 paging X
}
