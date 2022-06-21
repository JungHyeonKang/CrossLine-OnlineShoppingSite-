package com.green.team4.vo.shop;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product_optVO {
    private int pcno ;
    private int pno;
    private int pPrice;
    private String pOption ;
    private String pOptionName ;
    private String pOption2 ;
    private String pOptionName2 ;
    private String pColor ;
    private int pAmount;
    private int pOptionPrice;
}
