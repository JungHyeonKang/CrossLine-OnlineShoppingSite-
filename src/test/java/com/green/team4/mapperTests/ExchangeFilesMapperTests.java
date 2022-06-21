package com.green.team4.mapperTests;

import com.green.team4.mapper.mypage.ExchangeFilesMapper;
import com.green.team4.vo.mypage.ExchangeFilesVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExchangeFilesMapperTests {

    @Autowired
    private ExchangeFilesMapper mapper;

    @Test
    public void testInsert(){
        ExchangeFilesVO exchangeFilesVO = new ExchangeFilesVO();
        exchangeFilesVO.setEno(1);
        exchangeFilesVO.setImg_name("테스트 이미지제목3");
        exchangeFilesVO.setPath("/c:upload");
        exchangeFilesVO.setUuid("dpadawdpijawda24123");
        mapper.insert(exchangeFilesVO);
    }

    @Test
    public void testReadAll(){
        List<ExchangeFilesVO> result = mapper.getAll(1);
        result.forEach(i-> System.out.println(i));
    }

    @Test
    public void testReadOne(){
        ExchangeFilesVO result = mapper.getOne(1);
        System.out.println(result);
    }

    @Test
    public void testUpdate(){
        ExchangeFilesVO exchangeFilesVO = mapper.getOne(1);
        exchangeFilesVO.setImg_name("파일이름 수정 테스트");
        mapper.update(exchangeFilesVO);
    }

    @Test
    public void testDelete(){
        mapper.delete(1);
    }

}
