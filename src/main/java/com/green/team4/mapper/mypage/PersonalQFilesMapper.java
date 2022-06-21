package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.PersonalQFilesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalQFilesMapper {

    int insert(PersonalQFilesVO personalQFilesVO); // 1:1문의글 첨부파일 등록
    List<PersonalQFilesVO> getAllByPqNo(int pqNo); // 1:1문의글 전체 가져오기(pqNo 단위로)
    int update(PersonalQFilesVO personalQFilesVO); // 데이터 수정
    int delete(int pqNo); // 데이터 삭제(pqNo 단위로)
}
