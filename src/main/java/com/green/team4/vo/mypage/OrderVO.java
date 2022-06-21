package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderVO {

    // tbl_order 칼럼
    private int mno; // 회원번호 (tbl_memberInfo)
    private String ono; // 주문번호
    private LocalDateTime orderDate; // 주문일시

    private String receiverName; // 받는 사람 성명
    private String receiverPhone; // 받는 사람 연락처

    private String postcode; // 우편번호
    private String address; // 주소(도로명)
    private String detailAddress; // 상세주소
    private String message; // 주문/배송 관련 메세지

    private int tProductPrice; // 상품 총 금액
    private int tShipFee; // 배송비
    private int tUsePoint; // 사용한 총 포인트
    private int tTotalPrice; // 총 결제금액

    private String tPayStatus; // 결재 상태
    private int tSavePoint; // 적립 포인트

    // tbl_memberInfo join 해서 가져올 칼럼
    private String name; // 주문자 성명 (tbl_memberInfo)
    private String mobileNum; // 주문자 휴대전화번호 (tbl_memberInfo)

    // tbl_orderItem 가져오기
    private List<OrderItemVO> orderItemList;


}
