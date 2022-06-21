package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InterestVO {

    // 찜목록 기본정보
    private int mno; // 회원 번호(회원 테이블과 foreign)
    private int itrNo; // 장바구니 번호
    private int pno; // 상품 번호(상품 테이블과 foreign)
    private LocalDateTime itrDate;

    // 상품 기본정보(tbl_product와 join해서 가져올 예정)
    private String pName;
    private int pPrice;
    private String pImage;

    // 검색 기능
    private String keyword;
    private String search;
}
