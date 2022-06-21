package com.green.team4.vo.shop;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DBOrderItemVO {

    private String ono; //주문 번호
    private int pno; //상품 번호
    private String pName;
    private String pImage;
    private String pColor;
    private String pOption;
    private String pOptionName;
    private String pOption2;
    private String pOptionName2;
    private int pOptionPrice;
    private int iCount; // 주문수량

    private int orderItemno; // orderItem_tbl 기본키

    private int pPrice; // 상품 한 개 가격
    
    private double pDiscount; // 상품 할인율
    
    private int iSavePoint; // 상품 한개 구매 후 얻을 포인트(적립)



    // 뷰에서 받아올 데이터들 (dB에 없음)

    private int tProductPrice; // 총 순수 상품 가격(원가)(상품가격 * 수량)
    private int iDisPrice; // 할인 적용된 가격

    private int iTotalPrice; // 총 가격 (salePrice*ItemCount)

    private int totalSavePoint; // 총 획득 포인트(savePoint*ItemCount)

    public void initSaleTotal(){
        this.pPrice = this.pPrice + this.pOptionPrice;
        this.tProductPrice = this.pPrice * this.iCount;
        this.iDisPrice =(int) (this.pPrice * (1-pDiscount));
        this.iTotalPrice = this.iDisPrice*this.iCount;
        this.iSavePoint = ((int) (Math.floor(this.pPrice*0.05)))*this.iCount; // SW 수정 0604_수량 곱하기 추가
        this.totalSavePoint = this.iSavePoint; // SW수정 0604_수량 곱하기 삭제
    }
}
