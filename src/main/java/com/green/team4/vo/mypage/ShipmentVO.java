package com.green.team4.vo.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShipmentVO {

    private int mno; // 회원번호
    private int sno; // 배송지주소번호
    private String shipName; // 배송지이름
    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소
}
