package com.green.team4.service.community;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.community.BoardMapper;
import com.green.team4.mapper.community.ReplyMapper;
import com.green.team4.vo.mypage.SearchVO;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper mapper;

    @Autowired
    ReplyMapper replyMapper;


    @Override
    public void insert(BoardVO boardVO) {
        mapper.insert(boardVO);
    }

    @Override
    public BoardVO getOne(Long bno) {

        return mapper.readOne(bno);
    }

    @Override
    public List<BoardVO> getList() {

        return mapper.readList();
    }

    @Override
    public void modify(BoardVO boardVO) {
        mapper.modify(boardVO);
    }

    @Override
    public void delete(Long bno) {
        mapper.delete(bno);
    }

    @Override
    public List<BoardVO> getPageList(Criteria criteria) {
        criteria.setPage((criteria.getPage() - 1)* criteria.getPageNum());
        return mapper.getPageList(criteria);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return mapper.getTotalCount(criteria);
    }

    @Override
    public BoardVO userInfo(Long mno) {
        return mapper.userInfo(mno);
    }

    @Override
    public List<BoardVO> readListForMain() {
        return mapper.readListForMain();
    }

    @Override
    public List<BoardVO> readAllByMno(int mno) { // SW 추가
        return mapper.getAllByMno(mno);
    }

    @Override
    public List<BoardVO> readAllByMnoSearch(int mno, int pageNum, SearchVO searchVO) { // SW 추가
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 mno: "+mno);
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 pageNum: "+pageNum);
        System.out.println("BoardService => readAllByMnoSearch 실행 => 받은 searchVO: "+searchVO);
        searchVO.setMno(mno); // 검색 vo에 mno set
        PageHelper.startPage(pageNum,8); // 가져올 데이터 페이지 번호, 페이지 당 데이터 개수

        // 게시글 가져오기
        List<BoardVO> boardlist = mapper.getAllByMnoSearch(searchVO);

        // 게시글에 해당하는 댓글 개수 가져오기
        boardlist.forEach(board->{
            board.setReplyCnt(replyMapper.getCntByBno(board.getBno()));
        });

        System.out.println("BoardService => readAllByMnoSearch 실행 후 가져온 list: "+boardlist);

        return boardlist;
    }
}
