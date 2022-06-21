package com.green.team4.vo.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoVO {

    private int mno; // 회원번호 (변경 불가)
    private String id; // 아이디 (변경 불가)
    private String password; // 비밀번호
    private String name; // 이름
    private String nickName; // 별명
    private String gender; // 성별
    private String sSNum; // 주민등록번호(변경 불가)
    private String email; // 이메일
    private String phoneNum; // 전화번호
    private String mobileNum; // 휴대전화번호
    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소
    private String interestSports; // 관심 운동
    private String auth; // 회원 권한 (변경 불가/관리자만 변경 가능)
    private String grade; // 회원 등급 (변경 불가/관리자만 변경 가능)
    private int point; // 회원 보유 포인트 (변경 불가/관리자만 변경 가능)
}
