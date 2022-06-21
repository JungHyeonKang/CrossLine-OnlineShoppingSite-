//package com.green.team4.mapperTests;
//
//import com.green.team4.mapper.sw.DeleteMemMapper;
//import com.green.team4.mapper.sw.MemberInfoMapper;
//import com.green.team4.vo.sw.DeleteMemVO;
//import com.green.team4.vo.sw.MemberInfoVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//public class DeleteMemMapperTests {
//
//    @Autowired
//    private DeleteMemMapper deleteMemMapper;
//
//    @Autowired
//    private MemberInfoMapper memberInfoMapper;
//
//    @Test
//    public void testInsert(){
//        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(20);
//        DeleteMemVO deleteMemVO = new DeleteMemVO();
//        deleteMemVO.setId(memberInfoVO.getId());
//        deleteMemVO.setPassword(memberInfoVO.getPassword());
//        deleteMemVO.setName(memberInfoVO.getName());
//        deleteMemVO.setNickName(memberInfoVO.getNickName());
//        deleteMemVO.setEmail(memberInfoVO.getEmail());
//        deleteMemVO.setPhoneNum(memberInfoVO.getPhoneNum());
//        deleteMemVO.setGender(memberInfoVO.getGender());
//        deleteMemVO.setSSNum(memberInfoVO.getSSNum());
//        deleteMemVO.setPostcode(memberInfoVO.getPostcode());
//        deleteMemVO.setAddress(memberInfoVO.getAddress());
//        deleteMemVO.setDetailAddress(memberInfoVO.getDetailAddress());
//        deleteMemVO.setAuth(memberInfoVO.getAuth());
//        deleteMemVO.setGrade(memberInfoVO.getGrade());
//
//        deleteMemVO.setDelDate(LocalDateTime.now());
//        deleteMemVO.setDelCategory("사이트 불만");
//        deleteMemVO.setDelContent("사이트 사용이 불만스러워서 탈퇴합니다.");
//
//        deleteMemMapper.insert(deleteMemVO);
//    }
//
//    @Test
//    public void testReadAll(){
//        List<DeleteMemVO> delList = deleteMemMapper.getAll();
//        delList.forEach(i-> System.out.println(i));
//    }
//
//    @Test
//    public void testReadOne(){
//        DeleteMemVO deleteMemVO = deleteMemMapper.getOne(2);
//        System.out.println(deleteMemVO);
//    }
//
//    @Test
//    public void testDelete(){
//        int result = deleteMemMapper.delete(2);
//    }
//}
