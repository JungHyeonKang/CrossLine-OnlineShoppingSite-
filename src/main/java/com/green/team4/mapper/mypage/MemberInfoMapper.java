package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.MemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberInfoMapper {

    int insert(MemberInfoVO memberInfoVO); // 회원가입 화면에서 사용
    List<MemberInfoVO> getAll(); // 회원정보 전체 가져오기
    MemberInfoVO getOne(int mno); // 회원정보 하나 가져오기
    int updateByMember(MemberInfoVO memberInfoVO); // 회원정보 수정 (일반회원용 - 일부만 수정 가능)

    int updateByAdmin(MemberInfoVO memberInfoVO); // 회원정보 수정 (관리자용 - 전부 수정가능)
    int delete(int mno); // 회원정보 삭제 (탈퇴 회원 정보 삭제(tbl_memberInfo에서 삭제))
    MemberInfoVO getMemberInfo(int mno); //주문 페이지에서 사용 할 id 통한 회원구하기
    MemberInfoVO findByUsername(String id);
    MemberInfoVO findPw(MemberInfoVO vo);
    void newPw(MemberInfoVO vo);
}
