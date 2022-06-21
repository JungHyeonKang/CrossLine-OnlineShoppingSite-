package com.green.team4;

import com.green.team4.mapper.mypage.MemberInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class pagingTests {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

//    @Test
//    public void getMemberListByPaging(){
//        PageHelper.startPage(1,5);
//        Page<MemberInfoVO> memPage = memberInfoMapper.selectAll();
//        memPage.forEach(i-> System.out.println(i));
//        System.out.println("memPage: "+memPage);
//    }
}
