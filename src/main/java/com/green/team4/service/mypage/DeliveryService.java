package com.green.team4.service.mypage;

import com.green.team4.vo.mypage.DeliveryVO;

import java.util.List;

public interface DeliveryService {

    int register(DeliveryVO deliveryVO); // 배송정보 신규 추가
    DeliveryVO readOneByDno(int dno); // 배송정보 하나 가져오기
    DeliveryVO readOneByOINoDCat(int oINo); // 배송정보 하나가져오기
    List<DeliveryVO> readAll(); // 배송정보 전체 가져오기
    List<DeliveryVO> readAllByMno(int mno); // 회원별 배송정보 전체 가져오기
    int modify(DeliveryVO deliveryVO);
    int remove(int dno); // 배송정보 삭제
}
