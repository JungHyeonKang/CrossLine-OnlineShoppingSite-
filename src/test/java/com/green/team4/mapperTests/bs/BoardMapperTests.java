package com.green.team4.mapperTests.bs;

import com.green.team4.mapper.community.BoardMapper;
import com.green.team4.vo.community.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BoardMapperTests {
    @Autowired
    public BoardMapper mapper;

//    @Test
//    public void readOneTest(){
//        System.out.println(mapper.readOne(1L));
//    }

    @Test
    public void insertTest(){
        List<String> titleArr = new ArrayList<>();
        titleArr.add("운동이 뇌건강에 미치는 영향");
        titleArr.add("더 강한 가드를 위해 해야할 것");
        titleArr.add("더 빠른 근성장을 위한 휴식!");
        titleArr.add("마인드셋과 운동의 상관관계");
        titleArr.add("에너지드링크 및 보충제 5000종 비교!! 다 먹어봤습니다");
        titleArr.add( "카페인은 정말 운동에 도움이 될까?");
        titleArr.add("어깨가 넓어지지 않는다구요? 이것만 따라하세요");
        for (int i = 0; i < 500; i++) {
            int rand = (int)(Math.random()*7);
            BoardVO vo = new BoardVO();
            vo.setNickName("guest"+i);
            vo.setContent("제목이 곧 내용입니다." + i);
            vo.setTitle(titleArr.get(rand)+i);

            mapper.insert(vo);
        }

    }

//    @Test
//    public void modifyTest(){
//        BoardVO vo = new BoardVO();
//        vo.setUserName("수정된 이름 수정이");
//        vo.setTitle("수정된 제목 수정제");
//        vo.setContent("수정된 내용 수정내");
//        vo.setUNo(4L);
//        mapper.modify(vo);
//    }

    @Test
    public void readOneTest(){
        BoardVO vo = mapper.readOne(4L);
        System.out.println("======================");
        System.out.println("@@ 결과 " + vo);
        System.out.println("======================");
    }

    @Test
    public void readListTest(){

        List<BoardVO> list = mapper.readList();

        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

    }

}
