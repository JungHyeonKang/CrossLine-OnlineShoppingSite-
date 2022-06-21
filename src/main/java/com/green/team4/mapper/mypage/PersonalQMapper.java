package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.PersonalQVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalQMapper {

    int getAllCnt(int mno); // 1:1문의글 개수 가져오기 (mno 단위)
    int insert(PersonalQVO personalQVO); // 1:1문의글 데이터 입력
    List<PersonalQVO> getAllByMno(int mno); // 1:1문의글 전체 가져오기(mno 단위로)
    List<PersonalQVO> getAllByPno(int pno); // 1:1문의글 상품 단위로 가져오기
    List<PersonalQVO> getAllByOno(String ono); // 1:1문의글 주문번호 단위로 가져오기
    List<PersonalQVO> getAllAdmin(); // 데이터 전체 가져오기
    PersonalQVO getOne(int pqNo); // 데이터 하나 가져오기(pqNo로 검색)
    int update(PersonalQVO personalQVO); // 데이터 수정
    int delete(int pqNo); // 데이터 삭제
}
