package com.green.team4.service.mypage;

import com.green.team4.mapper.shop.ReviewMapper;
import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.mapper.mypage.ReviewFilesMpMapper;
import com.green.team4.mapper.mypage.ReviewMpMapper;
import com.green.team4.vo.shop.*;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.ReviewFilesMpVO;
import com.green.team4.vo.mypage.ReviewMpVO;
import com.green.team4.vo.mypage.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewMpServiceImpl implements ReviewMpService{

    private final ReviewMpMapper reviewMpMapper;
    private final ReviewFilesMpMapper reviewFilesMpMapper;
    private final OrderItemMapper orderItemMapper;

    private final ReviewMapper reviewMapper;

    @Override
    public int register(ReviewMpVO reviewMpVO) { // 리뷰 신규 등록
        log.info("ReviewMpService => register 실행 => 받은 reviewMpVO: "+reviewMpVO);

        // 리뷰 글 등록
        int result = reviewMpMapper.insert(reviewMpVO);
        int key = reviewMpVO.getRno();
        log.info("key: "+key);

        // 리뷰 첨부파일 등록
        if(reviewMpVO.getReviewFilesList() != null){
            reviewMpVO.getReviewFilesList().forEach(file->{
                file.setRno(key);
                log.info("등록 파일: "+file);
                reviewFilesMpMapper.insert(file);
            });
        }

        // 해당 주문상품 리뷰 등록 여부 상태 업데이트
        OrderItemVO orderItemVO = orderItemMapper.getOne(reviewMpVO.getOINo());
        orderItemVO.setIReviewStatus("등록완료");
        orderItemMapper.update(orderItemVO);

        setReviewCnt(reviewMpVO.getPno());//(JH추가)
        setRating(reviewMpVO.getPno());//(JH추가)

        return result;
    }

    @Override
    public ReviewPageVO readAllByPno(ItemPageCriteria cri) { // 리뷰 가져오기 (pno 단위)
        log.info("ReviewMpService => readAllByPno 실행 => 받은 pno: "+cri.getPno());

        // 리뷰 글 모두 가져오기
        ReviewPageVO reviewPageVO = new ReviewPageVO();
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByPno(cri);
        log.info("ReviewMpService => readAllByPno 실행 후 받은 reviewList: "+reviewList);


        // 리뷰 첨부파일 모두 가져오기
        List<ReviewMpVO> resultList = new ArrayList<>();

        reviewList.forEach(reviewMpVO -> {
            List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(reviewMpVO.getRno());
            System.out.println(fileList);
            reviewMpVO.setReviewFilesList(fileList);
            resultList.add(reviewMpVO);
            System.out.println(resultList);
        });
        reviewPageVO.setList(resultList);
        reviewPageVO.setPageInfo(new PagingVO(cri,reviewMapper.getReviewsCount(cri.getPno())));
        return reviewPageVO;
    }

    @Override
    public List<ReviewMpVO> readAllByMno(int mno) { // 리뷰 가져오기 (mno 단위)
        log.info("ReviewMpService => readAllByMno 실행 => 받은 mno: "+mno);

        // 리뷰 글 모두 가져오기
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByMno(mno);
        log.info("ReviewMpService => readAllByMno 실행 후 받은 reviewList: "+reviewList);

        // 리뷰 첨부파일 모두 가져오기
        List<ReviewMpVO> resultList = new ArrayList<>();

        reviewList.forEach(reviewMpVO -> {
            List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(reviewMpVO.getRno());
            reviewMpVO.setReviewFilesList(fileList);
            resultList.add(reviewMpVO);
        });
        return resultList;
    }

    @Override
    public List<ReviewMpVO> readAllByMnoSearch(SearchVO searchVO) { // 리뷰 가져오기 (mno 단위)
        log.info("ReviewMpService => readAllByMno 실행 => 받은 searchVO: "+searchVO);

        // 리뷰 글 모두 가져오기
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByMnoSearch(searchVO);
        log.info("ReviewMpService => readAllByMno 실행 후 받은 reviewList: "+reviewList);

        // 리뷰 첨부파일 모두 가져오기
        List<ReviewMpVO> resultList = new ArrayList<>();

        reviewList.forEach(reviewMpVO -> {
            List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(reviewMpVO.getRno());
            reviewMpVO.setReviewFilesList(fileList);
            resultList.add(reviewMpVO);
        });
        return resultList;
    }

    @Override
    public ReviewMpVO readOneByRno(int rno) { // 리뷰 하나 가져오기
        log.info("ReviewMpService => readOneByRno 실행 => 받은 rno: "+rno);

        // 리뷰 글 하나 가져오기
        ReviewMpVO reviewMpVO = reviewMpMapper.getOneByRno(rno);

        // 리뷰 첨부파일 가져오기
        List<ReviewFilesMpVO> fileList = reviewFilesMpMapper.getAll(rno);

        // 리뷰 글에 첨부파일 저장
        reviewMpVO.setReviewFilesList(fileList);

        return reviewMpVO;
    }

    @Override
    public int modify(ReviewMpVO reviewMpVO) { // 리뷰 수정
        log.info("ReviewMpService => modify 실행 => 받은 reviewMpVO: "+reviewMpVO);
        int result = reviewMpMapper.update(reviewMpVO);
        log.info("ReviewMpService => modify 실행 후 수정된 데이터 개수: "+result);

        setReviewCnt(reviewMpVO.getPno());//(JH추가)
        setRating(reviewMpVO.getPno());//(JH추가)

        return result;
    }

    @Override
    public int remove(ReviewMpVO reviewMpVO) { // 리뷰 삭제
        log.info("ReviewMpService => remove 실행 => 받은 rno: "+reviewMpVO.getRno());

        // 리뷰 등록 상태 원상복구
        OrderItemVO orderItemVO = orderItemMapper.getOne(reviewMpVO.getOINo());
        orderItemVO.setIReviewStatus("미등록");
        orderItemMapper.update(orderItemVO);

        // 리뷰 첨부파일 삭제
        reviewFilesMpMapper.delete(reviewMpVO.getRno());

        // 리뷰 삭제
        int result = reviewMpMapper.delete(reviewMpVO);
        log.info("ReviewMpService => remove 실행 후 삭제된 데이터 개수: "+result);

        setReviewCnt(reviewMpVO.getPno());//(JH추가)
        setRating(reviewMpVO.getPno());//(JH추가)

        return result;
    }
    //상품테이블에 평점 업데이트(JH추가)
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
//    상품테이블에 리뷰 갯수 업데이트(JH추가)
    public void setReviewCnt(int pno){
        int cnt=reviewMapper.getReviewsCount(pno);
        UpdaterReviewCntVO urcvo = new UpdaterReviewCntVO();
        urcvo.setPno(pno);
        urcvo.setPReviewCnt(cnt);
        reviewMapper.updateReviewsCount(urcvo);
    }

}
