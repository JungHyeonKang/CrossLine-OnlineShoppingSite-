package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.ExchangeFilesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExchangeFilesMapper {

    int insert(ExchangeFilesVO exchangeFilesVO); // 취소/반품/교환 첨부파일 등록
    List<ExchangeFilesVO> getAll(int eno); // 데이터 전체 가져오기(eno 단위로)
    ExchangeFilesVO getOne(int ino); // 데이터 하나 가져오기(ino로 검색)
    int update(ExchangeFilesVO exchangeFilesVO); // 데이터 수정
    int delete(int eno); // 데이터 삭제(eno 단위로)
}
