package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int getAllCnt(int mno);
    List<OrderVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    OrderVO getOne(String ono); // 데이터 하나 가져오기(ono 단위로)
    //관리자
    List<OrderVO> getAllAdmin();
    int update(OrderVO orderVO); // 주문서 수정
    int insert(OrderVO orderVO); // 취소/반품/교환 새 주문서 발행 내역 등록
    int delete(String ono); // 주문서 삭제
    List<OrderVO> getAllByThisMonth(int mno); // 현재월 데이터 가져오기
}
