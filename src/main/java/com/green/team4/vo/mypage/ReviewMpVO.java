package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewMpVO {

    private int rno; // 리뷰 번호
    private int pno ; // 상품 번호
    private int mno ; // 회원 번호
    private int oINo ; // 주문상품 번호
    private int rRating; // 평점
    private int likeHit ; // 좋아요 개수
    private String rContent; // 리뷰 내용

    private LocalDateTime rRegdate; // 등록일
    private LocalDateTime rUpdatedate; // 수정일
    
    private List<ReviewFilesMpVO> reviewFilesList; // 첨부파일

    // orderItem 테이블 join 데이터
    private String iName; // 상품명
    private String iOption; // 상품 옵션1
    private String iOptionName; // 상품 옵션1 이름
    private String iOption2; // 상품 옵션2
    private String iOptionName2; // 상품 옵션2 이름
    private String iColor; // 상품 색상명
    private int iPrice; // 상품 가격
    private int iDiscount; // 할인율
    private int iDisPrice; // 할인된 상품 가격
    private int iCount; // 상품 수량(주문한 상품 수량)
    private int iSavePoint; // 상품 적립포인트
    private int iTotalPrice; // 최종 결재금액
}

