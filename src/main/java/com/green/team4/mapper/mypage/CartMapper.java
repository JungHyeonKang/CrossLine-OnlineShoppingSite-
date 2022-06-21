package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    int insert(CartVO cartVO); // 데이터 신규 추가
    CartVO getOneByPnoMno(int pno,int mno); // 장바구니 데이터 하나 가져오기(mno/pno 단위) // 사용X
    CartVO getOneByCno(int cno); // 장바구니 데이터 하나 가져오기(cno 단위)
    List<CartVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    int update(CartVO cartVO); // 데이터 수정
    int delete(int cno); // 데이터 삭제
    int deleteByPno(int pno); // 데이터 삭제
}
