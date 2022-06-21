package com.green.team4.vo.admin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StaticVO {

    private int totalProfit; // 총 판매금액 가져오기
    private int totalMemberCnt; // 총 회원수
    private int totalDeleteMemCnt; // 총 탈퇴회원수
    private int totalProductCnt; // 총 판매중인 상품 개수


}
