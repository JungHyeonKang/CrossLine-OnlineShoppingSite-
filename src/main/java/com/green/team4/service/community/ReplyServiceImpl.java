package com.green.team4.service.community;

import com.green.team4.mapper.community.ReplyMapper;
import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.community.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyMapper replyMapper;

    @Override
    public void insert(ReplyVO replyVO) {
        replyMapper.insert(replyVO);
    }

    @Override
    public void modify(ReplyVO replyVO) {
        replyMapper.modify(replyVO);
    }

    @Override
    public ReplyVO getOne(Long rno) {
        return replyMapper.readOne(rno);
    }

    @Override
    public List<ReplyVO> getPageList(Criteria criteria,Long bno) {
        return replyMapper.getPageList(criteria,bno);
    }

    @Override
    public void deleteOne(Long rno) {
        replyMapper.deleteOne(rno);
    }

    @Override
    public void deleteAll(Long bno) {
        replyMapper.deleteAll(bno);
    }

    @Override
    public int getTotal(Criteria criteria) {
        return replyMapper.getTotalCount(criteria);
    }
}
