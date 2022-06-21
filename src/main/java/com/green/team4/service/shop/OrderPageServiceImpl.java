package com.green.team4.service.shop;

import com.green.team4.mapper.shop.CouponMapper;
import com.green.team4.mapper.shop.OrderPageMapper;
import com.green.team4.mapper.shop.ShopMapper;
import com.green.team4.mapper.mypage.MemberInfoMapper;
import com.green.team4.vo.shop.*;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderPageServiceImpl implements OrderPageService {

    @Autowired
    private OrderPageMapper orderPageMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public List<OrderPageItemVO> getProductListInfo(List<OrderPageItemVO> orders) {
        List<OrderPageItemVO> list = new ArrayList<>();
        for(OrderPageItemVO order  :orders){
            System.out.println("페이지 orders : " + order);
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            OrderPageItemVO productsInfo = orderPageMapper.getProductsInfoWithOpt(product_optVO);
            log.info("productsInfo: "+productsInfo);
            productsInfo.setPOptionPrice(order.getPOptionPrice());
            productsInfo.setItemCount(order.getItemCount());
            log.info("productsInfo2 : "+productsInfo);
            productsInfo.initSaleTotal();
            log.info("productsInfo3 : "+productsInfo);
            list.add(productsInfo);

        }
        return list;
    }

    @Override
    @Transactional
    public void order(DBOrderVO vo) {
        System.out.println("order서비스 입장");
        System.out.println("vo : " + vo);
        //회원정보
        MemberInfoVO member =memberInfoMapper.getMemberInfo(vo.getMno());
        System.out.println("member: " + member);
        //주문정보(order테이블에 넣을 데이터 만들기)
        List<DBOrderItemVO> ords = new ArrayList<>();
        for(DBOrderItemVO order:vo.getOrders()){
            System.out.println("order :"+order);
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            DBOrderItemVO orderItem = orderPageMapper.getOrderInfoWithOpt(product_optVO);
            System.out.println("orderItem : "+orderItem);
            orderItem.setICount(order.getICount());
            orderItem.setOno(vo.getOno());
            orderItem.setPOptionPrice(order.getPOptionPrice());
            orderItem.initSaleTotal();
            System.out.println("orderItem : "+orderItem);
            vo.setPno(order.getPno());
            ords.add(orderItem);


        }
        vo.setPhoneNum(member.getPhoneNum());
        vo.setOrders(ords);
        vo.getOrderPriceInfo();

        //db에 넣기
        orderPageMapper.enrollOrder(vo);
        for(DBOrderItemVO order : vo.getOrders()){
            System.out.println("db 넣기 order : " + order);
            orderPageMapper.enrollOrderItem(order);
        }
        //포인트 차감
        int calPoint = member.getPoint();
        calPoint = calPoint - vo.getTUsePoint() + vo.getTSavePoint();
        member.setPoint(calPoint);
        orderPageMapper.deductMoney(member);

        //재고 차감
        for(DBOrderItemVO order: vo.getOrders()){
            System.out.println("재고입장");
            System.out.println("order : " + order);
            Product_optVO product_optVO = new Product_optVO();
            product_optVO.setPno(order.getPno());
            product_optVO.setPColor(order.getPColor());
            product_optVO.setPOption(order.getPOption());
            product_optVO.setPOption2(order.getPOption2());
            System.out.println(product_optVO);
            Product_optVO product_optVO2 = shopMapper.getProductWithOpt(product_optVO);
            System.out.println("하이");
            product_optVO.setPAmount(product_optVO2.getPAmount() - order.getICount());
            System.out.println(product_optVO);
            orderPageMapper.deductStockWithOpt(product_optVO);


        }
        //쿠폰 상태 수정
        MemberCouponVO memberCouponVO = new MemberCouponVO();
        memberCouponVO.setCpNo(vo.getTCpno());
        memberCouponVO.setMno(member.getMno());
        couponMapper.updateStatus(memberCouponVO);
    }
}
