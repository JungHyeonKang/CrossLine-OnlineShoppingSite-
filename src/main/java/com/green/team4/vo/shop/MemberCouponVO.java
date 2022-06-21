package com.green.team4.vo.shop;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberCouponVO {

    private int mno;
    private int cpNo;
    private int cpConditionVal;
    private String cpName;
    private String cpValue ;
    private String cpStatus;
    private Date expireDate;

}
