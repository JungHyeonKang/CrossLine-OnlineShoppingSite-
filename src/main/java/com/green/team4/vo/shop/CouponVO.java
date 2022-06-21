package com.green.team4.vo.shop;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CouponVO {

    private int cpNo;
    private String cpName ;
    private String cpValue ;
    private int cpConditionVal;
    private Date createDate;
    private Date  assignDate ;
    private Date expireDate;


}
