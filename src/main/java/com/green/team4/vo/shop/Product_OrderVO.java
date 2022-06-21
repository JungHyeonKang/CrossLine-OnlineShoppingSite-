package com.green.team4.vo.shop;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product_OrderVO {

    // tbl_order 칼럼
    private int mno; // 회원번호 (tbl_memberInfo)
    private String ono; // 주문번호
    private int p_no; // 상품번호 (tbl_tbl_product)
    private int pno; // 결제수단 번호 (tbl_paymentInfo)
//    private String delAddr; // 배송지 주소
    private String addr1; // 우편번호
    private String addr2; // 주소
    private String addr3; // 상세주소
    private String receiverName; // 받는 사람 성명
    private String receiverPhone; // 받는 사람 연락처
    private String message; // 주문/배송 관련 메세지
    private int count; // 주문 개수
    private int productPrice; // 상품 총 금액
    private int shipFee; // 배송비
    private int totalPrice; // 총 결제금액
    private LocalDateTime orderDate; // 주문일시
    private String orderStatus; // 주문상태
    private int usePoint ;
    // tbl_memberInfo join 해서 가져올 칼럼
    private String name; // 주문자 성명 (tbl_memberInfo)
    private String phoneNum; // 주문자 연락처 (tbl_memberInfo)

    // tbl_product join 해서 가져올 칼럼
    private String p_name;

    // tbl_paymentInfo join 해서 가져올 칼럼
    private String payName; // 결제 대분류(휴대폰, 신용카드, 체크카드, 카카오페이 등)
    private String payContent; // 결제정보 세부내용(카드이름 등)
}
