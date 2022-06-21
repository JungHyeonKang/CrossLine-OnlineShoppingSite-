package com.green.team4.mapper.shop;

import com.green.team4.vo.shop.DBOrderItemVO;
import com.green.team4.vo.shop.DBOrderVO;
import com.green.team4.vo.shop.OrderPageItemVO;
import com.green.team4.vo.shop.Product_optVO;

import com.green.team4.vo.admin.ProductVO;
import com.green.team4.vo.mypage.MemberInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderPageMapper {
    
    //주문 상품 정보
    public OrderPageItemVO getProductsInfo(Product_optVO pno);

    public OrderPageItemVO getProductsInfoWithOpt(Product_optVO povo);
    // 주문 상품 정보 (주문처리용)

    public DBOrderItemVO getOrderInfo(Product_optVO povo);
    public DBOrderItemVO getOrderInfoWithOpt(Product_optVO povo);

    //주문 테이블에 주문 등록하기
    public int enrollOrder(DBOrderVO vo);

    // 주문 상품 등록
    public int enrollOrderItem(DBOrderItemVO vo);

    //회원 돈,포인트 차감
    public int deductMoney(MemberInfoVO memberInfoVO);

    //주문 재고 차감
    public int deductStock(ProductVO pvo);
    public int deductStockWithOpt(Product_optVO povo);
//    /* 장바구니 제거(주문한거만) */
//    public int deleteOrderCart(장바구니 VO);
}
