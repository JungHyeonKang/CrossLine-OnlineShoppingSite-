package com.green.team4.service.shop;

import com.green.team4.mapper.shop.ShopMapper;
import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.Product_optVO;
import com.green.team4.vo.admin.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<ProductVO> getListAll() {
        return shopMapper.getListAll();
    }

    @Override
    public List<ProductVO> getListWithCategory(String p_category) {
        return shopMapper.getListWithCategory(p_category);
    }

    @Override
    public int register(ProductVO pvo) {
        return 0;
    }

    @Override
    public ProductVO getOne(int p_no) {
        return shopMapper.getOne(p_no);
    }

    @Override
    public ProductVO getOneWithPno(int p_no) {
        return shopMapper.getOneWithPno(p_no);
    }


    @Override
    public Product_optVO getProductWithOpt(Product_optVO povo) {
        return shopMapper.getProductWithOpt(povo);
    }

    @Override
    public List<ProductVO> getListByCategoryAndPage(Map<String, Object> map) {
        return shopMapper.getListByCategoryAndPage(map);
    }

    @Override
    public int getTotalProductCountbyCategory(String p_category) {
        return shopMapper.getTotalProductCountbyCategory(p_category);
    }


    @Override
    public List<ProductVO> getCategory(String p_category) {
        return shopMapper.getCategory(p_category);
    }

    @Override
    public List<ProductVO> getListByFind(ItemPageCriteria cri) {
        return shopMapper.getListByFind(cri);
    }

    @Override
    public List<ProductVO> getListByFind4(ItemPageCriteria cri) {
        return shopMapper.getListByFind4(cri);
    }

    @Override
    public int getTotaldatabyFind(ItemPageCriteria cri) {
        return shopMapper.getTotaldatabyFind(cri);
    }

    @Override
    public List<ProductVO> getListBySearch(String keyword) {
        return shopMapper.getListBySearch(keyword);
    }

    @Override
    public List<ProductVO> getListByRand(String keyword) {
        return shopMapper.getListByRand(keyword);
    }

    @Override
    public List<ProductVO> getListWithBest(ItemPageCriteria cri) {
        return shopMapper.getListWithBest(cri);
    }

    @Override
    public List<ProductVO> getListWithTimeDeal(ItemPageCriteria cri) {
        System.out.println("타임딜 가져오기 서비스 받은 cri: "+cri);
        System.out.println("타임딜List"+shopMapper.getListWithTimeDeal(cri));
        return shopMapper.getListWithTimeDeal(cri);
    }

    @Override
    public List<Product_optVO> getColors(int pno) {
        return shopMapper.getColors(pno);
    }

    @Override
    public List<Product_optVO> getOptions(int pno) {
        return shopMapper.getOptions(pno);
    }
    public List<Product_optVO> getOptions2(int pno) {
        return shopMapper.getOptions2(pno);
    }
    @Override
    public List<Product_optVO> getOptList(Product_optVO povo) {
        return shopMapper.getOptList(povo);
    }

    @Override
    public List<ProductVO> getOneWithOpt(int pno) {
        return shopMapper.getOneWithOpt(pno);
    }

    @Override
    public Product_optVO getOptionPrice(Product_optVO povo) {
        return shopMapper.getOptionPrice(povo);
    }

    @Override
    public int updateDiscount(String pCatecode) {
        return shopMapper.updateDiscount(pCatecode);
    }
}
