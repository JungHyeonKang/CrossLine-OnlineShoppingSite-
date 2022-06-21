package com.green.team4.mapperTests;

import com.green.team4.mapper.shop.ShopMapper;
import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.PagingVO;

import com.green.team4.vo.admin.ProductVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ShopMapperTest {

    @Autowired
    private ShopMapper mapper;

    private static Logger logger = LoggerFactory.getLogger(ShopMapperTest.class);
    @Test
    public void testGetList(){

        System.out.println(mapper.getListAll());
    }

    @Test
    public void testGetList2(){


    }


    @Test
    public void testInsert(){
        String[] maincategories = {"의류","식품","운동용품"};
        String[] clothes = {"티셔츠","셔츠","기능성티","반팔티","런닝복"};
        String[] food = {"비타민C","비타민D","종합 비타민","오메가3","아르기닌"};
        String[] equipment = {"벤치프레스","로잉머신","철봉","런닝머신","덤벨"};
        String[] cjpgs = {"clothes0.jpg","clothes1.jpg","clothes2.jpg","clothes3.jpg"};
        String[] fjpgs = {"food0.jpg","food1.jpg","food2.jpg","food3.jpg"};
        String[] ejpgs = {"equipment0.jpg","equipment1.jpg","equipment2.jpg","equipment3.jpg"};
        String[] sizes = {"XXL","XL","L","M","S"};
        String[] colors = {"red","green","blue","white","black"};
        String [] cate = {"101001","101002","101003","102001","102002","103001","103002","103003","103004","104001"
        ,"104002","104003","105001","105002","105003","106001","106002","106003","107001","107002","107003"
        ,"201001","201002","201003","202001","202002","203001","203002","203003","204001","204002","204003"};
        for (int i = 0; i < 20; i++) {
            ProductVO pvo = new ProductVO();
            int randidx = (int)(Math.random()*cate.length);
            pvo.setPCateCode(cate[randidx]);
            pvo.setPCateCode("50000");
            pvo.setPImage("하이");
            pvo.setPDiscount(0.0);
            pvo.setPPrice(25000);
            pvo.setPAmount((int)(Math.random()*100)+1);

            pvo.setPName("테스트상품"+i);

            pvo.setPInformation("형 믿고 사");


            mapper.insert(pvo);
        }
    }
    @Test
    public void testInsert2(){
        String[] maincategories = {"의류","식품","운동용품"};
        String[] clothes = {"티셔츠","셔츠","기능성티","반팔티","런닝복"};
        String[] food = {"비타민C","비타민D","종합 비타민","오메가3","아르기닌"};
        String[] equipment = {"벤치프레스","로잉머신","철봉","런닝머신","덤벨"};
        String[] cjpgs = {"clothes0.jpg","clothes1.jpg","clothes2.jpg","clothes3.jpg"};
        String[] fjpgs = {"food0.jpg","food1.jpg","food2.jpg","food3.jpg"};
        String[] ejpgs = {"equipment0.jpg","equipment1.jpg","equipment2.jpg","equipment3.jpg"};
        String[] sizes = {"XXL","XL","L","M","S"};
        String[] colors = {"red","green","blue","white","black"};
        String [] cate = {"101001","101002","101003","102001","102002","103001","103002","103003","103004","104001"
                ,"104002","104003","105001","105002","105003","106001","106002","106003","107001","107002","107003"
                ,"201001","201002","201003","202001","202002","203001","203002","203003","204001","204002","204003"};

            ProductVO pvo = new ProductVO();
            int randidx = (int)(Math.random()*cate.length);
            pvo.setPCateCode(cate[randidx]);

            pvo.setPImage("하이");
            pvo.setPDiscount(0.0);
            pvo.setPPrice(5000);
            pvo.setPAmount((int)(Math.random()*100)+1);

            pvo.setPName("가격테스트1");

            pvo.setPInformation("형 믿고 사");


            mapper.insert(pvo);

    }
    @Test
    public void testGetone(){
        System.out.println(mapper.getOne(20));


    }
    @Test void testPage(){
        ItemPageCriteria cri = new ItemPageCriteria();

        PagingVO pagingVO = new PagingVO();
        pagingVO.setCri(cri);
        Map<String ,Object> map = new HashMap<>();
        map.put("p_cateCodeRef","201");
        map.put("pagingVO",pagingVO);
        System.out.println(mapper.getListByCategoryAndPage(map));
    }

    @Test
    public void testtotalcnt(){
        System.out.println(mapper.getTotalProductCountbyCategory("프로틴"));
    }
    @Test
    public void testGetCategory(){
        List<ProductVO> list =mapper.getCategory("프로틴");
        System.out.println(list);
        System.out.println("갯수 : "+list.size());
    }

    @Test
    public void  testFindList(){

        ItemPageCriteria cri = new ItemPageCriteria();


        //cri.setSub_category("잠옷");
        mapper.getTotaldatabyFind(cri);
        System.out.println(mapper.getListByFind(cri));






    }
    @Test
    public void  testFindcount(){

        ItemPageCriteria cri = new ItemPageCriteria();
        // fCri.setMain_category("식품");

        //fCri.setSub_category2("프로틴바");
        System.out.println(mapper.getTotaldatabyFind(cri));

    }
}
