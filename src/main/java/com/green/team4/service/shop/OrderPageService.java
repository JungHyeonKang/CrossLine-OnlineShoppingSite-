package com.green.team4.service.shop;

import com.green.team4.vo.shop.DBOrderVO;
import com.green.team4.vo.shop.OrderPageItemVO;

import java.util.List;

public interface OrderPageService {
//    주문정보
    public List<OrderPageItemVO> getProductListInfo(List<OrderPageItemVO> orders);

//    주문하기
    public void order(DBOrderVO vo);
}
