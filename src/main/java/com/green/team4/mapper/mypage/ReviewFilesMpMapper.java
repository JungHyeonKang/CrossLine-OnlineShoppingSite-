package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.ReviewFilesMpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewFilesMpMapper {

    int insert(ReviewFilesMpVO reviewFilesMpVO); // 리뷰 첨부파일 등록
    List<ReviewFilesMpVO> getAll(int rno); // 데이터 전체 가져오기(rno 단위로)
    int update(ReviewFilesMpVO reviewFilesMpVO); // 데이터 수정
    int delete(int rno); // 데이터 삭제(rno 단위로)
}
