package com.green.team4.mapper.community;

import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.mypage.ReviewMpVO;
import com.green.team4.vo.mypage.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public void insert(BoardVO boardVO);
    public void modify(BoardVO boardVO);
    public BoardVO readOne(Long bno);
    public List<BoardVO> readList();
    public void delete(Long bno);
    public List<BoardVO> getPageList(Criteria criteria);
    public int getTotalCount(Criteria criteria);
    // 전체 게시글 수를 구하기 위한 메서드
    public BoardVO userInfo(Long mno);
    //JH추가
    public List<BoardVO> readListForMain();
    // SW 추가
    public List<BoardVO> getAllByMno(int mno);
    List<BoardVO> getAllByMnoSearch(SearchVO searchVO);
}
