package com.green.team4.vo.admin;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductVO {

    private int pno;
    private int pcno;
    private String pName;
    private double pDiscount;
    private double pRating;
    private int pReviewCnt;
    private int pPrice;
    private int pAmount;
    private Date pRegdate;
    private String pInformation;
    private String pImage;
    private String pSize;
    private String pOption;
    private String pOption2;
    private String pOptionName;
    private String pOptionName2;
    private int pOptionPrice;
    private String pColor;
    
//    카테고리 추가 0528 수정(JH)
    private String pCateCode;

    private String pCateName;
    private String CateParent;

    // 판매상태 추가
    private String pStatus;
}
