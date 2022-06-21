package com.green.team4.service.shop;

import com.green.team4.mapper.shop.ReviewMapper;
import com.green.team4.vo.shop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewVO> getReviewList(int pno) {
        return reviewMapper.getReviewList(pno);
    }

    @Override
    public void write(ReviewVO rvo) {
        reviewMapper.write(rvo);
        setReviewCnt(rvo.getPno());
        setRating(rvo.getPno());
    }

    @Override
    public boolean update(ReviewVO rvo) {
        setReviewCnt(rvo.getPno());
        setRating(rvo.getPno());

        return reviewMapper.update(rvo)==1;

    }

    @Override
    public boolean delete(ReviewVO rvo) {
        setReviewCnt(rvo.getPno());
        setRating(rvo.getPno());
        return reviewMapper.delete(rvo.getPno())==1;
    }

    @Override
    public ReviewPageVO getReviewWithPaging(ItemPageCriteria cri) {
        ReviewPageVO reviewPageVO = new ReviewPageVO();
        //reviewPageVO.setList(reviewMapper.getReviewListWithPaging(cri));
        reviewPageVO.setPageInfo(new PagingVO(cri,reviewMapper.getReviewsCount(cri.getPno())));
        return reviewPageVO;
    }

    @Override
    public void updateLike(int rno) {
        reviewMapper.updateLike(rno);
    }

    @Override
    public void updateLikeCancel(int rno) {
        reviewMapper.updateLikeCancel(rno);
    }

    @Override
    public void insertLike(ReviewLikeVO dto) {
        reviewMapper.insertLike(dto);
    }

    @Override
    public void deleteLike(ReviewLikeVO dto) {
        reviewMapper.deleteLike(dto);
    }

    @Override
    public int checkLike(ReviewLikeVO dto) {
        return reviewMapper.checkLike(dto);
    }


    //평점 업데이트
    public void setRating(int pno){
        Double ratingAvg = reviewMapper.getRatingAvg(pno);
        if(ratingAvg == null){
            ratingAvg=0.0;
        }
        UpdateReviewVO urvo = new UpdateReviewVO();
        urvo.setPno(pno);
        urvo.setPRating(ratingAvg);
        reviewMapper.updateRating(urvo);

    }
    public void setReviewCnt(int pno){
        int cnt=reviewMapper.getReviewsCount(pno);
        UpdaterReviewCntVO urcvo = new UpdaterReviewCntVO();
        urcvo.setPno(pno);
        urcvo.setPReviewCnt(cnt);
        reviewMapper.updateReviewsCount(urcvo);
    }
}
