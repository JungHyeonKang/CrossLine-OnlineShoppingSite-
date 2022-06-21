package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.DeliveryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    int insert(DeliveryVO deliveryVO); // 데이터 신규 추가
    DeliveryVO getOneByDno(int dno); // 배송 데이터 하나 가져오기(dno 단위)
    DeliveryVO getOneByOINoDCat(int oINo); // 배송 데이터 하나 가져오기(oINo/dCategory 단위)
    List<DeliveryVO> getAll(); // 데이터 전체 가져오기
    List<DeliveryVO> getAllByMno(int mno); // 데이터 전체 가져오기(mno 단위로)
    int update(DeliveryVO deliveryVO); // 데이터 수정
    int delete(int dno); // 데이터 삭제
}
