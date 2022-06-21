package com.green.team4.service.admin;

import com.green.team4.mapper.admin.ProductOptMapper;
import com.green.team4.vo.shop.Product_optVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductOptServiceImpl implements ProductOptService{
    private final ProductOptMapper productOptMapper;

    @Override
    public List<Product_optVO> getOpt1Names(int pno) {
        List list = new ArrayList();
        list.add(getOpt1Names(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getOpt2Names(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getColors(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getColors(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getColors(pno));
        return list;
    }

    @Override
    public List<Product_optVO> getOpt(int pno) {
        List list = new ArrayList();
        list.add(productOptMapper.getProductOption(pno));
        return list;
    }

    @Override
    public Product_optVO findAmount(Product_optVO vo) {
        productOptMapper.findAmount(vo);
       log.info("수량조회: " + vo.getPAmount());
        return vo;
    }

}
