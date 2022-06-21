package com.green.team4.service.mypage;

import com.green.team4.vo.mypage.MemberInfoVO;

import java.util.List;

public interface MemberInfoService {

    MemberInfoVO readOne(int mno);
    public List<MemberInfoVO> readAll(int pageNum);
    int modifyByMember(MemberInfoVO memberInfoVO);
    int modifyByAdmin(MemberInfoVO memberInfoVO);
    int remove(int mno,String delCategory, String delContent);
    public MemberInfoVO getMemberInfo(int mno); //주문페이지에 사용할 멤버 서비스
    MemberInfoVO findById(String id);
    //비밀번호찾기
    MemberInfoVO findPw(MemberInfoVO vo);


}
