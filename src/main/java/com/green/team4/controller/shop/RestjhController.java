package com.green.team4.controller.shop;

import com.green.team4.mapper.admin.ProductInfoImgMapper;
import com.green.team4.service.shop.CategoryService;
import com.green.team4.service.shop.CouponService;
import com.green.team4.service.shop.ReviewService;
import com.green.team4.service.shop.ShopService;

import com.green.team4.service.mypage.PersonalQService;
import com.green.team4.service.mypage.ReviewMpService;
import com.green.team4.vo.shop.*;
import com.green.team4.vo.admin.ProductInfoImgVO;
import com.green.team4.vo.admin.ProductVO;
import com.green.team4.vo.mypage.PersonalQVO;
import com.green.team4.vo.mypage.ReviewMpVO;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Log4j2
@RequestMapping("/rest")
@RestController
public class RestjhController {

   @Autowired
   private  ReviewService reviewService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductInfoImgMapper infoImgMapper;
    @Autowired
    private ReviewMpService reviewMpService;
    @Autowired
    private PersonalQService personalQService;
    @Autowired
    private CouponService couponService;
    //리뷰 컨트롤러
    @GetMapping(value = "/getreviews",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewPageVO> getreviews(ItemPageCriteria cri){
//        log.info("getreviews 입장" );
//        log.info(" p_no " + cri);
        ResponseEntity<ReviewPageVO> responseEntity = null;
//        List<ReviewMpVO>list =reviewMpService.readAllByPno(cri.getPno());
//        System.out.println("list   "+list);
        try {
            responseEntity = new ResponseEntity<>( reviewMpService.readAllByPno(cri),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }
    //리뷰 상세정보 컨트롤러
    @GetMapping(value = "/getOneReview/{rno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewMpVO> getOneReview(@PathVariable ("rno") int rno){
        log.info("getOneReview 입장" );

        ResponseEntity<ReviewMpVO> responseEntity = null;

        try {
            responseEntity = new ResponseEntity<>( reviewMpService.readOneByRno(rno),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }

    // 상품 상세 정보 불러오기 컨트롤러
    @GetMapping("/getinfo/{pno}")
    public ResponseEntity<List<ProductInfoImgVO>> getinfo(@PathVariable ("pno") int pno){
        log.info("getinfo 입장" );
        log.info(" p_no " + pno);
        ResponseEntity<List<ProductInfoImgVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(infoImgMapper.infoImgList(pno) ,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }
    // 상품 배송 정보 불러오기 컨트롤러
    @GetMapping("/getDeliInfo/")
    public ResponseEntity<List<ProductInfoImgVO>> getDeliInfo(){
        log.info("getDeliInfo 입장" );
        ResponseEntity<List<ProductInfoImgVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>( HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }
    //검색 컨트롤러
    @GetMapping(value = "/getListBySearch/{keyword}")
    public ResponseEntity<List<ProductVO>> getListBySearch(@PathVariable("keyword") String keyword){
        log.info("getListBySearch 입장" );
        ResponseEntity<List<ProductVO>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(shopService.getListBySearch(keyword),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    //옵션 컨트롤러
    @GetMapping(value = "/getOptions")
    public ResponseEntity<List<Set<String>>> getOptions( Product_optVO povo){

//        log.info("getOptions 입장");
//        log.info("povo" +povo);
//        System.out.println("optList: "+shopService.getOptList(povo));
        List<Product_optVO> options = shopService.getOptList(povo);
//        System.out.println("options"+options);
        Set<String> uniqueColor = new HashSet<>();
        Set<String> uniqueOpt = new HashSet<>();
        Set<String> uniqueOpt2 = new HashSet<>();
        List<Set<String>> list = new ArrayList<>();
        for(Product_optVO option : options){
            uniqueColor.add(option.getPColor());
            uniqueOpt.add(option.getPOption());
            uniqueOpt2.add(option.getPOption2());
        }
        list.add(uniqueColor);
        list.add(uniqueOpt);
        list.add(uniqueOpt2);
//        System.out.println(uniqueColor);
//        System.out.println(uniqueOpt);
//        System.out.println(uniqueOpt2);
//        System.out.println(list);
        ResponseEntity<List<Set<String>>> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
    // 옵션 가격 컨트롤러
    @GetMapping(value = "/getPrice")
    public ResponseEntity<List<Integer>> getprice( Product_optVO povo){
        System.out.println("getPrice 입장");
        System.out.println(povo);
        Product_optVO product_optVO =shopService.getOptionPrice(povo);
        int totalprice =product_optVO.getPPrice() + product_optVO.getPOptionPrice();
        ResponseEntity<List<Integer>> responseEntity = null;
        List<Integer> priceList = new ArrayList<>();
        priceList.add(totalprice);
        priceList.add(product_optVO.getPPrice());
        priceList.add((product_optVO.getPOptionPrice()));

        log.info("product_optVO : "+product_optVO);
        log.info("priceList : "+priceList);
        try{
            responseEntity = new ResponseEntity<>(priceList,HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    // 리뷰 좋아요 컨트롤러
    @PostMapping(value = "/commentLike")
    public int ReviewLike(int pno,int rno,int mno,int rmno){
        System.out.println("commentLike         ");
        ReviewLikeVO reviewLikeVO = new ReviewLikeVO();
        reviewLikeVO.setPno(pno);
        reviewLikeVO.setMno(mno);
        reviewLikeVO.setRno(rno);
        reviewLikeVO.setRmno(rmno);
        System.out.println("reviewLikeVO              "+reviewLikeVO);
            int checkLike  = reviewService.checkLike(reviewLikeVO);
            System.out.println("checkLike              "+checkLike);
            if(checkLike ==0 ){
                reviewService.insertLike(reviewLikeVO);
                reviewService.updateLike(reviewLikeVO.getRno());
            }
            else if(checkLike == 1){
                reviewService.deleteLike(reviewLikeVO);
                reviewService.updateLikeCancel(reviewLikeVO.getRno());
            }

        return 0;
    }
    @PostMapping(value = "/commentLikeCheck")
    public int commentLikeCheck(@RequestBody ReviewLikeVO reviewLikeVO){
        System.out.println("commentLikeCheck 입장");
        System.out.println(reviewLikeVO);
        int checkLike  = reviewService.checkLike(reviewLikeVO);
        if(checkLike ==0 ){
            reviewService.insertLike(reviewLikeVO);
            reviewService.updateLike(reviewLikeVO.getRno());
        }
        else if(checkLike == 1){
            reviewService.deleteLike(reviewLikeVO);
            reviewService.updateLikeCancel(reviewLikeVO.getRno());
        }

        return 0;
    }
    @GetMapping("/getQList/{pno}")
    public ResponseEntity<List<PersonalQVO>> getQList(@PathVariable("pno") int pno){
        ResponseEntity<List<PersonalQVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(personalQService.readAllByPno(pno),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("/getCategoryList/")
    public ResponseEntity<List<CategoryVO>> getCateTier2(){
        ResponseEntity<List<CategoryVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(categoryService.cateList(),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/getCoupons")
    public ResponseEntity<List<MemberCouponVO>> getCoupons( MemberCouponVO memberCouponVO){
        System.out.println("getCoupons 입장" + memberCouponVO);
        ResponseEntity<List<MemberCouponVO>> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(couponService.getCouponAvailable(memberCouponVO),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/updateTimeDeal")
    public ResponseEntity<Integer> updateTimeDeal(String pCatecode){
        System.out.println("updateTimeDeal 입장" + pCatecode);
        String code =pCatecode.substring(0,1);
        ResponseEntity<Integer> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(shopService.updateDiscount(code),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

}
