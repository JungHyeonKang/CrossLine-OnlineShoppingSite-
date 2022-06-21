package com.green.team4.service.mypage;


import com.green.team4.vo.mypage.ExchangeVO;
import com.green.team4.vo.mypage.OrderVO;

import java.util.List;

public interface ExchangeService {


    // CRUD -----------------------------------------------------------------

    int readAllCnt(int mno); // 취소/반품/교환 개수 가져오기
    int register(ExchangeVO exchangeVO); // 취소/반품/교환 신청 등록
    List<ExchangeVO> readAll(int mno, int pageNum); // 취소/반품/교환 전체 가져오기(mno 단위)
    List<ExchangeVO> readAllAdmin(); // 취소/반품/교환 전체 가져오기
    List<ExchangeVO> readAllByOno(String ono); // 주문서 번호 단위로 가져오기
    ExchangeVO readOne(int eno); // 취소/반품/교환 하나 가져오기
    int modify(ExchangeVO exchangeVO); // 취소/반품/교환 수정
    int remove(int eno); // 취소/반품/교환 삭제

    // 취소/반품/교환 -----------------------------------------------------------------
    void cancelAndReturn(String ono,int oINo, int pno, int eno, String category); // 취소/반품 처리 진행
    OrderVO change(String ono,int oINo, int pno, int eno); // 교환 처리 진행
}
