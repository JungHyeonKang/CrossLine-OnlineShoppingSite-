package com.green.team4.service.mypage;



import com.green.team4.vo.mypage.PersonalQVO;

import java.util.List;

public interface PersonalQService {

    int readAllCnt(int mno); // 1:1문의 회원별 개수 가져오기
    int register(PersonalQVO personalQVO); // 1:1문의 등록
    List<PersonalQVO> readAllByMno(int mno, int pageNum); // 1:1문의글 전체 가져오기(mno 단위로)
    List<PersonalQVO> readAllByPno(int pno); // 1:1문의글 상품 단위로 가져오기
    List<PersonalQVO> readAllByOno(String ono); // 1:1문의글 주문번호 단위로 가져오기
    List<PersonalQVO> readAllAdmin(int pageNum); // 1:1문의글 전체 가져오기
    PersonalQVO readOne(int pqNo); // 데이터 하나 가져오기(pqNo로 검색)
    int modify(PersonalQVO personalQVO); // 1:1문의글 수정
    int remove(int pqNo); // 1:1문의글 삭제
}
