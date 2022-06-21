//package com.green.team4.serviceTests.sw;
//
//import com.green.team4.service.sw.ExchangeService;
//import com.green.team4.vo.sw.ExchangeVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//public class ExchangeServiceTests {
//
//    @Autowired
//    private ExchangeService exchangeService;
//
//    @Test
//    public void testInsert(){
//        LocalDateTime time = LocalDateTime.now();
//
//        ExchangeVO exchangeVO = new ExchangeVO();
//        exchangeVO.setMno(8);
//
//        exchangeVO.setPno(2);
//        exchangeVO.setPno(8);
//        exchangeVO.setExCategory("취소");
//        exchangeVO.setExContent("구매에 착오가 있었습니다.");
//        exchangeVO.setExStatus("접수중");
//        exchangeVO.setExStartDate(time);
//
//        int key = exchangeService.register(exchangeVO);
//        System.out.println("가져온 key: "+key);
//    }
//
//    @Test
//    public void testReadAll(){
//        int mno = 8;
//        List<ExchangeVO> exList = exchangeService.readAll(mno);
//        exList.forEach(i-> System.out.println(i));
//    }
//
//    @Test
//    public void testReadOne(){
//        int eno = 15;
//        ExchangeVO exchangeVO = exchangeService.readOne(eno);
//        System.out.println(exchangeVO);
//    }
//
//    @Test
//    public void testUpdate(){
//        ExchangeVO exchangeVO = exchangeService.readOne(1);
//        exchangeVO.setExCategory("교환(수정테스트)");
//        exchangeVO.setExContent("신청 내용 수정 테스트");
//        exchangeVO.setExStartDate(LocalDateTime.now());
//        int result = exchangeService.modify(exchangeVO);
//        System.out.println("수정된 개수: "+result);
//    }
//
//    @Test
//    public void testDelete(){
//        int result = exchangeService.remove(3);
//        System.out.println("삭제된 개수: "+result);
//    }
//}
