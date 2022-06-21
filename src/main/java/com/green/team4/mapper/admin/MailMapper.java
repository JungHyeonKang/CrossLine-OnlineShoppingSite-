package com.green.team4.mapper.admin;

import com.green.team4.vo.admin.MailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {
    int insert(MailVO vo);
    List<MailVO> getAll();
    List<MailVO> getPage(int pageNum);
    MailVO getOne(int mno);
    int update(MailVO vo);
    int delete(int mno);
}
