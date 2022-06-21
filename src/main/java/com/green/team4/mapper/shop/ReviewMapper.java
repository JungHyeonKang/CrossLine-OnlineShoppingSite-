package com.green.team4.mapper.shop;

import com.green.team4.vo.shop.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {


    public List<ReviewVO> getReviewList(int pno);
    public int getReview(int pno);
    public void write(ReviewVO rvo);
    public int update(ReviewVO rvo);

    public int delete(int rvo);

    //평점 구하기
    public Double getRatingAvg(int pno);

    // 평점 업데이트하기
    public int updateRating(UpdateReviewVO urvo);

    //리뷰 갯수 구하기
    public int getReviewsCount(int pno);
    public int updateReviewsCount(UpdaterReviewCntVO urcvo);

    //리뷰 페이징
    public List<ReviewVO> getReviewListWithPaging(ItemPageCriteria cri);

    //리뷰  좋아요 업데이트
    public void updateLike(int rno);
    public void updateLikeCancel(int rno);


    //리뷰 좋아요 등록
    public void insertLike(ReviewLikeVO dto);

    public void deleteLike(ReviewLikeVO dto);

    public int checkLike(ReviewLikeVO dto);
}



