package com.green.team4.mapper.community;

import com.green.team4.vo.community.ReplyVO;
import com.green.team4.vo.community.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    public void insert(ReplyVO vo);
    public void modify(ReplyVO vo);
    public ReplyVO readOne(Long bno);
    public List<ReplyVO> replyList();
    public void deleteOne(Long rno);
    public void deleteAll(Long bno);
    public List<ReplyVO> getPageList(@Param("criteria") Criteria criteria,
                                     @Param("bno") Long bno);
    public int getTotalCount(Criteria criteria);
    // 전체 게시글 수를 구하기 위한 메서드

    // SW 추가
    public int getCntByBno(Long bno); // 해당 게시글의 댓글 모두 가져오기
    public List<ReplyVO> getAllByMno(int mno);
}
