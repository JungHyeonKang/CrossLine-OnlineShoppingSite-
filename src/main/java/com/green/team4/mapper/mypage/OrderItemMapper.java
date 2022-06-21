package com.green.team4.mapper.mypage;

import com.green.team4.vo.mypage.OrderItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    List<OrderItemVO> getAll(String ono); // 데이터 전체 가져오기(ono 단위로)
    List<OrderItemVO> getAllAdmin(); // 데이터 전체 가져오기

    List<OrderItemVO> getNormalDelivery(); // 데이터 전체 가져오기(배송전/미신청)
    OrderItemVO getOne(int oINo); // 데이터 하나 가져오기(orderItemNo 단위로)
    int update(OrderItemVO orderItemVO); // 주문 상품 중 하나 수정(취소/환불/교환,배송 상태만 업데이트 가능)
    int insert(OrderItemVO orderItemVO); // 주문 상품 신규 등록 (취소/반품/교환 새주문서 등록시 함께 등록)
    int delete(String ono); // 주문 상품 삭제
}
