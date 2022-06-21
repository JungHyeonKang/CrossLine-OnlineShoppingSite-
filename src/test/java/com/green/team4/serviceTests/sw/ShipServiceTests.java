package com.green.team4.serviceTests.sw;

import com.green.team4.service.mypage.ShipmentService;
import com.green.team4.vo.mypage.ShipmentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ShipServiceTests {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,50).forEach(i->{
            ShipmentVO shipmentVO = new ShipmentVO();
            shipmentVO.setMno(i);
            shipmentVO.setPostcode(""+(int)(Math.random()*(10000-9999)+9999));
            shipmentVO.setAddress("주소 테스트"+i);
            shipmentVO.setDetailAddress("세부 주소 테스트"+i);
            shipmentService.register(shipmentVO);
        });
    }

    @Test
    public void testReadAllByMno(){
        List<ShipmentVO> result = shipmentService.readAll(14);
        result.forEach(i-> System.out.println(i));
    }

    @Test
    public void testReadOneBySno(){
        ShipmentVO result = shipmentService.readOne(21);
        System.out.println(result);
    }
    
//    @Test
//    public void testModify(){
//        ShipmentVO shipmentVO = new ShipmentVO();
//        shipmentVO.setSno(5);
//        shipmentVO.setShipAddress("shipAddr수정테스트");
//        shipmentService.modify(shipmentVO);
//    }

    @Test
    public void testRemove(){
        int result = shipmentService.remove(5);
        System.out.println(result);
    }
}
