package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.DeleteMemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeleteMemMapper {

    int insert(DeleteMemVO deleteMemVO); // 데이터 입력 (마이페이지에서 입력)
    List<DeleteMemVO> getAll(); // 회원탈퇴 사유 정보 전체 가져오기 (관리자페이지에서 활용 예정)
    DeleteMemVO getOne(int dMNo); // 데이터 하나 가져오기(관리자페이지에서 활용 예정)
    int delete(int dMNo); // 데이터 삭제(관리자페이지에서 활용 예정)

}
