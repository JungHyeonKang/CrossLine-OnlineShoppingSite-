package com.green.team4.mapperTests;

import com.green.team4.mapper.shop.OrderPageMapper;
import com.green.team4.mapper.shop.ShopMapper;
import com.green.team4.mapper.admin.ProductMapper;
import com.green.team4.mapper.admin.ProductOptMapper;
import com.green.team4.mapper.admin.ProductImgMapper;
import com.green.team4.mapper.mypage.MemberInfoMapper;
import com.green.team4.service.admin.ProductOptService;
import com.green.team4.vo.shop.Product_optVO;
import com.green.team4.vo.admin.ProductImgVO;
import com.green.team4.vo.admin.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductOptMapper productOptMapper;
    @Autowired
    ProductImgMapper productImgMapper;
    @Autowired
    ProductOptService optService;
    @Autowired
    private OrderPageMapper orderPageMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void insert(){
        ProductVO productVO = new ProductVO();
        productVO.setPName("상품이름");
//        productVO.setPColor("보라");
//        productVO.setPSize("L");
        productVO.setPAmount(5);
        productVO.setPPrice(700000);
        productVO.setPInformation("여성용 스포츠웨어");
        productMapper.insert(productVO);
    }

    @Test
    public void getAll(){
        List<ProductVO> result = productMapper.getAll();
        result.forEach(System.out::println);
    }

    @Test
    public void update(){
        ProductVO productVO = new ProductVO();
        productVO.setPno(3);
        productVO.setPInformation("바뀌나");
        productMapper.update(productVO);
    }

    @Test
    public void getOne(){
        System.out.println(productMapper.getOne(9));
    }

    @Test
    public void getEvePno(){
        System.out.println(productMapper.getEvePno());
    }

    //---------------옵션테스트---------------

    @Test
    public void optInset(){
        String [] sizes = {"90", "95", "100", "105", "110", "115"};
        String [] colors = {"빨강", "주황", "노랑", "초록", "파랑", "남색", "보라"};
        List<String> colorList = new ArrayList<String>();
        for (String i: colors) colorList.add(i);


        for (String c : colors){
            Product_optVO vo = new Product_optVO();
            vo.setPno(10);
            vo.setPAmount(10);
            vo.setPColor(c);
            for (String s : sizes){
                vo.setPOption2(s);
                productOptMapper.insert(vo);
            }
        }
    }

    @Test
    public void getOpt(){
        System.out.println(productOptMapper.getOpt(1));
    }

    @Test
    public void deleteOpt(){
        productOptMapper.delete(2);
    }

    @Test
    public void getOneImg(){
        List<ProductImgVO> result = productImgMapper.getOneImgList(16);
        result.forEach(System.out::println);
    }
    @Test
    public void getAll2(){
        List<ProductImgVO> result = productImgMapper.getAll();
        result.forEach(System.out::println);
    }

    @Test
    public void findAmount(){
        Product_optVO product_optVO = new Product_optVO();
        product_optVO.setPno(9);
        product_optVO.setPColor("WHITE");
        product_optVO.setPOption("S");
        product_optVO.setPOption2(null);
        System.out.println("product_optVO: " + product_optVO);
        Product_optVO product_optVO2 = shopMapper.getProductWithOpt(product_optVO);
        System.out.println("product_optVO2: " + product_optVO2);
        product_optVO.setPAmount(product_optVO2.getPAmount() + 17);
        System.out.println(product_optVO);
        orderPageMapper.deductStockWithOpt(product_optVO);
    }
    @Test
    public void findAmountMapper(){
        Product_optVO vo = new Product_optVO();
        vo.setPno(24);
        vo.setPColor("GRAY");
        vo.setPOptionName("사이즈");
        vo.setPOption("S");
        vo.setPOptionName2("없음");
        Product_optVO result = productOptMapper.findAmount(vo);
        System.out.println(result.getPAmount());
    }


}
