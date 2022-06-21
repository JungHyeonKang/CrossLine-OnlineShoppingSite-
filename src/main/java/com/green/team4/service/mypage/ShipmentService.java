package com.green.team4.service.mypage;

import com.green.team4.vo.mypage.ShipmentVO;

import java.util.List;

public interface ShipmentService {

    int register(ShipmentVO shipmentVO); // 배송지 등록
    List<ShipmentVO> readAll(int mno); // 배송지 전체 가져오기
    ShipmentVO readOne(int sno); // 배송지 하나 가져오기
    int modify(ShipmentVO shipmentVO); // 배송지 정보 수정
    int remove(int sno); // 배송지 삭제
}
