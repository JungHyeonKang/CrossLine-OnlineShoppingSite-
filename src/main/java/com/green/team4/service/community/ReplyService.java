package com.green.team4.service.community;

import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.community.ReplyVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
    public void insert(ReplyVO replyVO);
    public void modify(ReplyVO replyVO);
    public ReplyVO getOne(Long rno);
    public List<ReplyVO> getPageList(Criteria criteria, Long uNo);
    public void deleteOne(Long rno);
    public void deleteAll(Long bno);
    public int getTotal(Criteria criteria);
}
