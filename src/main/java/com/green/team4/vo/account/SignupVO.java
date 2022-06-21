package com.green.team4.vo.account;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignupVO {
    private int mno;
    private String id;
    private String password;
    private String name;
    private String nickName;
    private String gender;
    private String sSNum;
    private String email;
    private String phoneNum;
    private String mobileNum;
    private String postcode;
    private String address;
    private String detailAddress;
    private String interestSports;
    private String auth;
    private String grade;
    private int point;
}
