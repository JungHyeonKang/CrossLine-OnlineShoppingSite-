package com.green.team4.serviceTests.sw;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class methodTests {

    // 메서드 동작 확인용 Test
    @Test
    public void getNewOrderNumTest(){
        // 날짜 문자열 생성
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        System.out.println("생성된 날짜 str: "+dateStr);

        // 난수 생성 (10자리)
        String randNum = "";
        for (int i = 0; i < 10; i++) {
            randNum += String.valueOf((int)Math.floor(Math.random()*8));
        }
        System.out.println("생성된 randNumFin: "+randNum);

        // 주문번호 조합 생성
        String orderNum = dateStr+randNum;
        System.out.println("새로 생성된 주문번호: "+orderNum);
    }


}
