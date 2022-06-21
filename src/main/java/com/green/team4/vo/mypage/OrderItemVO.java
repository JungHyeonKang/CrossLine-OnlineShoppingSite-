package com.green.team4.vo.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderItemVO {

    // tbl_orderItem 칼럼
    private String ono; // 주문번호
    private int oINo; // 주문 상품 번호
    private int pno; // 상품 번호

    private String iName; // 상품명
    private String iOption; // 상품 옵션1
    private String iOptionName; // 상품 옵션1 이름
    private String iOption2; // 상품 옵션2
    private String iOptionName2; // 상품 옵션2 이름
    private String iColor; // 상품 색상명
    private int iPrice; // 상품 가격
    private float iDiscount; // 할인율
    private int iDisPrice; // 할인된 상품 가격
    private int iCount; // 상품 수량(주문한 상품 수량)
    private int iSavePoint; // 상품 적립포인트
    private int iTotalPrice; // 최종 결재금액

    private String iDeliveryStatus; // 배송 상태
    private String iExStatus; // 교환반품취소 신청 상태
    private String iReviewStatus; // 리뷰등록 여부
}
