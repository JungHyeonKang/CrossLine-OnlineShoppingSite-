package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.ShipmentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShipmentMapper {
    int insert(ShipmentVO shipmentVO); // 데이터 입력
    List<ShipmentVO> getAll(int mno); // 데이터 전체 가져오기(mno 단위로)
    ShipmentVO getOne(int sno); // 데이터 하나 가져오기
    int update(ShipmentVO shipmentVO); // 데이터 수정
    int delete(int sno); // 데이터 삭제
}
