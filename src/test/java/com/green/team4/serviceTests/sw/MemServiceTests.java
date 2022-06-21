//package com.green.team4.serviceTests.sw;
//
//import com.green.team4.mapper.sw.MemberInfoMapper;
//import com.green.team4.service.sw.MemberInfoService;
//import com.green.team4.vo.sw.MemberInfoVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class MemServiceTests {
//
//    @Autowired
//    private MemberInfoService service;
//
//    @Autowired
//    private MemberInfoMapper mapper;
//
//    @Test
//    public void testInsert(){ // Test데이터 입력
//
//        String[] gender = {"남성","여성"};
//        String[] auth = {"일반","에디터","관리자"};
//        String[] grade = {"일반","우수","최우수"};
//
//        IntStream.rangeClosed(2,10).forEach(i->{
//            MemberInfoVO memberInfoVO = new MemberInfoVO();
//            memberInfoVO.setId("Id"+i);
//            memberInfoVO.setPassword("pw"+i);
//            memberInfoVO.setName("name"+i);
//            memberInfoVO.setNickName("nickName"+i);
//            memberInfoVO.setEmail("email"+i+"@test.com");
//            memberInfoVO.setPhoneNum("1234-"+i);
//            memberInfoVO.setMobileNum("031-"+i);
//            memberInfoVO.setGender(gender[(int)(Math.random()*2)]);
//            memberInfoVO.setSSNum("99999-"+i);
//            memberInfoVO.setAddress("기본주소"+i);
//            memberInfoVO.setPostcode(""+(int)(Math.random()*(10000-9999)+9999));
//            memberInfoVO.setDetailAddress("세부주소"+i);
//            memberInfoVO.setAuth(auth[(int)(Math.random()*3)]);
//            memberInfoVO.setGrade(grade[(int)(Math.random()*3)]);
//            memberInfoVO.setPoint((int)(Math.random()*(1000000-9999)+9999));
//
//            mapper.insert(memberInfoVO);
//        });
//    }
//    @Test
//    public void insertOne(){
//        String[] gender = {"남성","여성"};
//        String[] auth = {"일반","에디터","관리자"};
//        String[] grade = {"일반","우수","최우수"};
//
//        MemberInfoVO memberInfoVO = new MemberInfoVO();
//        memberInfoVO.setId("Id");
//        memberInfoVO.setPassword("pw");
//        memberInfoVO.setName("최성빈");
//        memberInfoVO.setNickName("nickName");
//        memberInfoVO.setEmail("csb3694@naver.com");
//        memberInfoVO.setPhoneNum("432-543436");
//        memberInfoVO.setMobileNum("010-3105-5347");
//        memberInfoVO.setGender("남자");
//        memberInfoVO.setSSNum("123-43556");
//        memberInfoVO.setAddress("금학로 91");
//        memberInfoVO.setDetailAddress("107동 1103호");
//        memberInfoVO.setAuth("관리자");
//        memberInfoVO.setPostcode("310260");
//        memberInfoVO.setGrade(grade[(int)(Math.random()*3)]);
//        memberInfoVO.setPoint(999999);
//        memberInfoVO.setInterestSports("눕기");
//
//        mapper.insert(memberInfoVO);
//    }
//
//    @Test
//    public void testReadOne(){ // 데이터 하나 가져오기
//        int mno = 51;
//        MemberInfoVO memberInfoVO = service.readOne(mno);
//        System.out.println("가져온 MemberInfo: "+memberInfoVO);
//    }
//
//    @Test
//    public void testGetAll(){
//        List<MemberInfoVO> list = service.readAll();
//        list.forEach(System.out::println);
//    }
//
////    @Test
////    public void testUpdate(){
////        MemberInfoVO memberInfoVO = service.readOne(24);
////        memberInfoVO.setPassword("pw"+"수정");
////        memberInfoVO.setNickName("nickName"+"수정");
////        memberInfoVO.setEmail("email"+"수정"+"@test.com");
////        memberInfoVO.setPhoneNum("1234-"+"수정");
////        memberInfoVO.setAddress("경기도 성남시 분당구 운중동"+"수정");
////
////        int result = service.modify(memberInfoVO);
////        System.out.println("수정된 개수: "+result);
////    }
//
////    @Test .... 사용 X
////    public void testDelete(){
////        int mno = 25;
////        int result = service.remove(15);
////        System.out.println("삭제된 개수: "+result);
////    }
//}
