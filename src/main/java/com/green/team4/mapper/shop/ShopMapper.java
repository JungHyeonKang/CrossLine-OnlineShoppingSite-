package com.green.team4.mapper.shop;

import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.Product_optVO;
import com.green.team4.vo.admin.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopMapper {
    public List<ProductVO> getListAll();
    public List<ProductVO> getListWithCategory(String p_category);
    public int insert(ProductVO pvo);
    public int update(ProductVO pvo);
    public int delete(int pno);
    public ProductVO getOne(int p_no);
    public Product_optVO getProductWithOpt(Product_optVO povo);
    //페이징 처리

   public List<ProductVO> getListByCategoryAndPage(Map<String,Object> map);


   // public int getTotalProductCount();


    public int getTotalProductCountbyCategory(String p_category);

    //하위 카테고리

    public List<ProductVO> getCategory(String p_category);


    // 리스트들

    public List<ProductVO> getListByFind(ItemPageCriteria cri);
    public List<ProductVO> getListByFind4(ItemPageCriteria cri);
    public List<ProductVO> getListWithBest(ItemPageCriteria cri);
    public List<ProductVO> getListWithTimeDeal(ItemPageCriteria cri);

    public List<ProductVO> getListBySearch(String keyword);
    public List<ProductVO> getListByRand(String keyword);
    public int getTotaldatabyFind(ItemPageCriteria cri);

    //옵션처리


    public List<Product_optVO> getColors(int pno);
    public List<Product_optVO> getOptions(int pno);
    public List<Product_optVO> getOptions2(int pno);
//    public List<Product_optVO> getOptList(int pno);

    public List<ProductVO> getOneWithOpt(int pno);
    public ProductVO getOneWithPno(int pno);
    //에이젝스에서 쓸거
    public List<Product_optVO> getOptList(Product_optVO povo);
    public Product_optVO getOptionPrice(Product_optVO povo);


// 타임딜 업데이트
    public int updateDiscount(String pCatecode);


}
