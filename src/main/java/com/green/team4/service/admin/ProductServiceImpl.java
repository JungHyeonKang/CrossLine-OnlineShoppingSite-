package com.green.team4.service.admin;

import com.green.team4.mapper.admin.ProductMapper;
import com.green.team4.vo.admin.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;

    @Override
    public int pno(ProductVO vo) { return vo.getPno(); }

    @Override
    public int insert(ProductVO vo) {
        log.info("상품등록Service");
        log.info("받아온 할인률: " + vo.getPDiscount());
        log.info("실수로 변환 후: " + vo.getPDiscount()/100f);
        vo.setPDiscount(vo.getPDiscount()/100f);
        return productMapper.insert(vo);
    }

    @Override
    public List<ProductVO> getAll() {
        return productMapper.getAll();
    }

    @Override
    public ProductVO getOne(int pno) {
        return productMapper.getOne(pno);
    }

    @Override
    public ProductVO getEvePno() {
        return productMapper.getEvePno();
    }

    @Override
    public int update(ProductVO vo) {
        log.info("상품정보 수정 service");
        return productMapper.update(vo);
    }

    @Override
    public int delete(int pno) {
        log.info("상품 삭제");
        return productMapper.delete(pno);
    }
}
