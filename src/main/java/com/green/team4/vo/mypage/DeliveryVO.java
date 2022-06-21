package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryVO {

    private int mno; // 회원번호
    private int oINo; // 주문상품 번호
    private int dno; // 배송 번호
    private String dCompanyName; // 택배사 이름
    private String dCategory; // 택배 종류(일반구매/반품/교환)
    private String dOption; // 택배 옵션(일반/특급/퀵 등)
    private String dLocation; // 택배 현재 위치
    private LocalDateTime dStartDate; // 배송 시작일
    private LocalDateTime dEndDate; // 배송 종료일
    private String dStatus; // 배송 상태(배송준비중 등)
}
