package com.green.team4.service.shop;

import com.green.team4.mapper.shop.CouponMapper;
import com.green.team4.vo.shop.CouponVO;
import com.green.team4.vo.shop.MemberCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Override
    public void insert(CouponVO couponVO) {
        couponMapper.insert(couponVO);
    }

    @Override
    public void insertCouponToMember(MemberCouponVO memberCouponVO) {
        couponMapper.insertCouponToMember(memberCouponVO);
    }

    @Override
    public List<MemberCouponVO> getCouponAvailable(MemberCouponVO memberCouponVO) {
        return couponMapper.getCouponAvailable(memberCouponVO);
    }

    @Override
    public void updateStatus(MemberCouponVO memberCouponVO) {
        couponMapper.updateStatus(memberCouponVO);
    }
}
