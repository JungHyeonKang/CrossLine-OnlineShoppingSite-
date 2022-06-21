//package com.green.team4.mapperTests.bs;
//
//import com.green.team4.mapper.community.ReplyMapper;
//import com.green.team4.vo.community.Criteria;
//import com.green.team4.vo.community.ReplyVO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class ReplyMapperTests {
//    @Autowired
//    ReplyMapper replyMapper;
//
//    @Test
//    public void insertTest(){
//        ReplyVO vo = new ReplyVO();
//        for (int i = 0; i < 10; i++) {
//            vo.setUNo(490L+i);
//            for (int j = 0; j < 10; j++) {
//                vo.setReply("테스트 댓글 " + j);
//                vo.setReplyer("테글러 "+j);
//                replyMapper.insert(vo);
//            }
//
//        }
//
//    }
//    @Test
//    public void getOneTest(){
//        System.out.println(replyMapper.readOne(1L));
//
//    }
//    @Test
//    public void getListTest(){
//        Criteria criteria = new Criteria(1L,10L);
//        List<ReplyVO> list = replyMapper.replyList();
//
//        for (int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i));
//        }
//    }
//    @Test
//    public void modifyTest(){
//        ReplyVO vo = new ReplyVO();
//        vo.setReply("수정 댓글 ");
//        vo.setReplyer("수정 테글러");
//        vo.setRNo(1L);
//        replyMapper.modify(vo);
//    }
//    @Test
//    public void delete(){
//        replyMapper.delete(1L);
//    }
//
//}
