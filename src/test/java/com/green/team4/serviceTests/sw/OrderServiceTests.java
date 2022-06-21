//package com.green.team4.serviceTests.sw;
//
//import com.green.team4.service.sw.OrderService;
//import com.green.team4.vo.sw.OrderVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class OrderServiceTests {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    public void testReadAll(){
//        int mno = 8;
//        List<OrderVO> orderList = orderService.readAll(mno);
//        orderList.forEach(i-> System.out.println(i));
//    }
//
//    //@Test
////    public void testReadOne(){
////        int ono = 1;
////        OrderVO orderVO = orderService.readOne(ono);
////        System.out.println(orderVO);
////    }
////
////    @Test
////    public void testUpdate(){
////        OrderVO orderVO = orderService.readOne(2); // 기존 order 가져오기
////        orderVO.setPayStatus("결재 완료");
////        orderVO.setDeliveryStatus("배송 시작");
////        int result = orderService.modify(orderVO);
////        System.out.println("수정된 개수: "+result);
////    }
//}
