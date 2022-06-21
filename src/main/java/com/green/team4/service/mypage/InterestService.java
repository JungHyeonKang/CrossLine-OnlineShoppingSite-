package com.green.team4.service.mypage;


import com.green.team4.vo.mypage.InterestVO;
import com.green.team4.vo.mypage.SearchVO;

import java.util.List;

public interface InterestService {

    int readAllCnt(int mno); // 찜목록 개수 가져오기
    int register(InterestVO interestVO); // 장바구니 신규 추가
    List<InterestVO> readAll(int mno, int pageNum); // 장바구니 전체 가져오기
    List<InterestVO> readAllWithSearch(int mno, int pageNum,SearchVO searchVO); // 장바구니 전체 가져오기
    int remove(int mno, int pno); // 장바구니 삭제

    // 위시리스트 데이터 확인
    public int getOne(InterestVO interestVO);
    int removeByPno(int pno); // 찜목록 삭제(pno 단위)
}
