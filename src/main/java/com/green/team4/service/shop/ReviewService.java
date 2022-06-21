package com.green.team4.service.shop;

import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.ReviewLikeVO;
import com.green.team4.vo.shop.ReviewPageVO;
import com.green.team4.vo.shop.ReviewVO;

import java.util.List;

public interface ReviewService {

    public List<ReviewVO> getReviewList(int pno);

    public void write(ReviewVO rvo);

    public boolean update(ReviewVO rvo);

    public boolean delete(ReviewVO rvo);

    //리뷰 페이징
    public ReviewPageVO getReviewWithPaging(ItemPageCriteria cri);

    public void updateLike(int rno);
    public void updateLikeCancel(int rno);
    //리뷰 좋아요 등록
    public void insertLike(ReviewLikeVO dto);

    public void deleteLike(ReviewLikeVO dto);

    public int checkLike(ReviewLikeVO dto);

}
