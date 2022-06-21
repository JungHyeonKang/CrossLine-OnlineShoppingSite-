package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteMemVO {

    // 기존 MemberInfo 에서 가져오는 내용
    private String id; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String nickName; // 별명
    private String gender; // 성별
    private String sSNum; // 주민등록번호
    private String email; // 이메일
    private String phoneNum; // 전화번호
    private String mobileNum; // 휴대전화번호
    private String postcode; // 우편번호
    private String address; // 기본주소
    private String detailAddress; // 세부주소
    private String interestSports; // 관심 운동
    private String auth; // 회원 권한
    private String grade; // 회원 등급
    private int point; // 회원 보유 포인트

    // 삭제 회원 테이블 자체 신규 내용
    private LocalDateTime dMDate; // 탈퇴 날짜
    private String dMCategory; // 탈퇴 분류
    private String dMContent; // 탈퇴 세부내용
}
