package com.green.team4.vo.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartVO {

    // 장바구니 기본정보
    private int mno; // 회원 번호(회원 테이블과 foreign)
    private int cno; // 장바구니 번호
    private int pno; // 상품 번호(상품 테이블과 foreign)
    private String cName; // 상품명
    private String cOption; // 상품 옵션1
    private String cOptionName; // 상품 옵션1 이름
    private String cOption2; // 상품 옵션1
    private String cOptionName2; // 상품 옵션1 이름
    private String cColor; // 상품 색상명
    private int cOptionPrice; // 상품 옵션 추가 가격
    private int cPrice; // 상품 가격(순수 상품가격)
    private int cDiscount; // 상품 할인률(%)
    private int cDisPrice; // 할인된 상품 가격 (상품 가격에 할인률 적용한 가격)
    private int cCount; // 상품 수량(주문한 상품 수량)
    private int cTotalPrice; // 최종 결재금액

    // 상품 기본정보(장바구니 List 표시에 필요한 정보)
    private String pMainCategory; // 메인 카테고리
    private String pSubCategory; // 서브 카테고리
    private String pImage; // 상품 대표 이미지

    // 카트 sub 메뉴 데이터 담을때 사용
    private int cartCnt;
    private String status;
}
