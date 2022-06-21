package com.green.team4.vo.shop;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DBOrderVO {

    private String ono; //주문번호
    private int mno;
    private int pno;
    private String receiverName; //배송받는사람
    private String receiverPhone ; //받는 사람 연락처
    
    private String email; //주문회원아이디
    private String id; // 회원 아이디
    private String phoneNum; // 주문자 번호
    private int count; //주문 갯수
    private String message;// 배송메세지

    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소


   
    private String orderStatus; // 주문상태


    private List<DBOrderItemVO> orders; //주문 상품


    private int tShipFee; // 배송비


    private int tUsePoint; //사용포인트

    private int tCouponPrice; // 쿠폰으로 할인 받은 금액
    private int tCpno; // 사용한 쿠폰번호
    private LocalDateTime orderDate; // 주문 날짜

    /* view에서 받아올 DB에 존재 하지 않는 데이터 */

    private int orderSalePrice; //판매가(모든 상품비용)
    private int tProductPrice ; //총 상품가격(순수 상품가격)
    private int tSavePoint; // 적립 포인트

    private int tTotalPrice; // 최종 판매 비용

    public void getOrderPriceInfo(){
        //상품비용 , 적립포인트 넣기
        for(DBOrderItemVO order : orders){
            tProductPrice += order.getTProductPrice();
            orderSalePrice += order.getITotalPrice();
            tSavePoint += order.getTotalSavePoint();
        }
        //배송비용 넣기
        if(orderSalePrice>=30000){
            tShipFee=0;
        }
        else{
            tShipFee=3000;
        }
        // 최종비용
        tTotalPrice = orderSalePrice + tShipFee - tUsePoint -tCouponPrice;
    }
}
