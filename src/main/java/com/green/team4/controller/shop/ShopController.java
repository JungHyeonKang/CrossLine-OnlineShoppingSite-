package com.green.team4.controller.shop;

import com.green.team4.mapper.admin.ProductImgMapper;
import com.green.team4.mapper.admin.ProductInfoImgMapper;
import com.green.team4.service.shop.CategoryService;
import com.green.team4.service.shop.ReviewService;
import com.green.team4.service.shop.ShopService;

import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.shop.ItemPageCriteria;
import com.green.team4.vo.shop.PagingVO;
import com.green.team4.vo.admin.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductInfoImgMapper productInfoImgMapper;
    @GetMapping("/list")
    public void listGet( @ModelAttribute("cri") ItemPageCriteria cri, Model model){
        log.info("listget");

        PagingVO pagingVO = new PagingVO();
        System.out.println(cri);
        String code = "";
        if(cri.getTier()==1){
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            code = cri.getPCateCode().substring(0,1);
        }
        else if(cri.getTier()==2){
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            code=cri.getPCateCode().substring(0,3);
            model.addAttribute("cateTier3ListWithCode",categoryService.getCateTier3WithCode(code));
        }
        else if (cri.getTier()==3) {
            model.addAttribute("pCateName",categoryService.getCateName(  cri.getPCateCode()));
            model.addAttribute("cateTier3ListWithCode",categoryService.getCateTier3WithCode(cri.getPCateCode().substring(0,3)));
            code=cri.getPCateCode();
        }
        cri.setPCateCode(code);
        System.out.println("코드 변환후 cri : "+cri);

        // 판매중인 상품만 sort
        List<ProductVO> productList = shopService.getListByFind(cri);
        List<ProductVO> finalList = productList.stream()
                            .filter(i->i.getPStatus()
                            .equals("판매중"))
                            .collect(Collectors.toList());
        log.info("sort 완료한 list: "+finalList);
        pagingVO.setCri(cri);
        pagingVO.setTotalProductData(shopService.getTotaldatabyFind(cri));
        model.addAttribute("list",finalList);
        model.addAttribute("pagingVO",pagingVO);
    }



    // 상품 상세페이지
    @GetMapping("/read")
    public void read(int pno,Model model){
        log.info("read");
        log.info("pno : "+pno);

        ProductVO pvo = shopService.getOne(pno);
        //model.addAttribute("member",memberInfoService.readOne(mno));
        model.addAttribute("relatedList",shopService.getListByRand( pvo.getPCateCode().substring(0,3)));
        System.out.println(shopService.getListByRand( pvo.getPName()));
        model.addAttribute("pvo",pvo);
        model.addAttribute("pImgs", productImgMapper.imgList(pno));
        model.addAttribute("pInfoImgs", productInfoImgMapper.infoImgList(pno));


    }


    @GetMapping("/orderCompleted")
    public void orderCompleted(int p_no , Model model){

    }
}
