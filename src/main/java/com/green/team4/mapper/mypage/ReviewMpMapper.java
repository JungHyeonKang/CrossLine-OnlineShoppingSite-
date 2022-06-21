package com.green.team4.mapper.mypage;

import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.mypage.ReviewMpVO;
import com.green.team4.vo.mypage.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMpMapper {

    int insert(ReviewMpVO reviewMpVO); // 리뷰 등록
    //List<ReviewMpVO> getAllByPno(int pno); // 데이터 전체 가져오기(pno 단위로)
    List<ReviewMpVO> getAllByMno(int mno); // 데이터 전체 가져오기(mno 단위로)
    List<ReviewMpVO> getAllByMnoSearch(SearchVO searchVO); // 데이터 전체 가져오기(mno 단위로)
    ReviewMpVO getOneByRno(int rno); // 데이터 하나 가져오기(rno 단위로)
    int update(ReviewMpVO reviewMpVO); // 리뷰 수정
    int delete( ReviewMpVO reviewMpVO); // 데이터 삭제(reviewMpVO 단위로)
    int deleteByRno(int rno); // 데이터 삭제(rno 단위로)

    List<ReviewMpVO> getAllByPno(ItemPageCriteria cri);

}

