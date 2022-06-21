package com.green.team4.mapper.shop;

import com.green.team4.vo.shop.CouponVO;
import com.green.team4.vo.shop.MemberCouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponMapper {

    public void insert(CouponVO couponVO);

    public void insertCouponToMember(MemberCouponVO memberCouponVO);

    public List<MemberCouponVO> getCouponAvailable(MemberCouponVO memberCouponVO);

    public void updateStatus(MemberCouponVO memberCouponVO);
}
