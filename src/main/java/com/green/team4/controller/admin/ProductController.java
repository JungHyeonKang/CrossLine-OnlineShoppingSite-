package com.green.team4.controller.admin;

import com.github.pagehelper.PageInfo;
import com.green.team4.mapper.admin.ProductMapper;
import com.green.team4.mapper.shop.OrderPageMapper;
import com.green.team4.mapper.shop.ShopMapper;
import com.green.team4.mapper.admin.ProductInfoImgMapper;
import com.green.team4.mapper.admin.ProductImgMapper;
import com.green.team4.mapper.admin.ProductOptMapper;
import com.green.team4.paging.PagingEntity;
import com.green.team4.service.mypage.CartService;
import com.green.team4.service.mypage.InterestService;
import com.green.team4.service.shop.CategoryService;
import com.green.team4.vo.admin.SearchVO;
import com.green.team4.service.admin.PagingService;
import com.green.team4.service.admin.ProductService;
import com.green.team4.vo.shop.Product_optVO;
import com.green.team4.vo.admin.ProductImgVO;
import com.green.team4.vo.admin.ProductInfoImgVO;
import com.green.team4.vo.admin.ProductVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/admin/product/*")
@Controller
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class ProductController {
    @Value("${com.green.upload.path}") //application.properties 변수
    private String uploadPath;

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductOptMapper productOptMapper;
    private final ProductImgMapper productImgMapper;
    private final ProductInfoImgMapper productImgInfoMapper;
    private final PagingService pagingService;
    private final ShopMapper shopMapper;
    private final OrderPageMapper orderPageMapper;
    private final CartService cartService;
    private final InterestService interestService;
    private final ProductMapper productMapper;

    private String makeFolder(){ // 파일 저장 폴더 만들기(탐색기)
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("\\", File.separator);
        // 폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if(uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }

    private String saveImg(MultipartFile img) throws IOException {
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String originalImg = img.getOriginalFilename();
        String imgFileName = originalImg.substring(originalImg.lastIndexOf("\\") + 1);
        String saveImgName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        //String saveImgUrl = File.separator + folderPath + File.separator + uuid + "_" + imgFileName;
        String saveImgUrl = "/" + folderPath + "/" + uuid + "_" + imgFileName;
        Path saveImgPath = Paths.get(saveImgName);
        img.transferTo(saveImgPath);

        return saveImgUrl;
    }

    @GetMapping("/upload")
    public void uploadGet(Model model){
        log.info("uploadGet.......");
        model.addAttribute("categoryList", categoryService.cateList());
    }
    @PostMapping("/upload")
    public String uploadPost(ProductVO vo, Model model,
                             @RequestParam("pImg") MultipartFile img,
                             @RequestParam("pInfo") MultipartFile info) throws IOException {
        vo.setPImage(saveImg(img));
        vo.setPInformation(saveImg(info));
        vo.setPStatus("판매중");
        productService.insert(vo);
        ProductVO eve = productService.getEvePno();
        return "redirect:/admin/product/uploadOpt?pno="+eve.getPno();
    }

    @GetMapping("/uploadOpt")
    public void uploadOptGet(int pno, Model model){
        log.info("uploadOpt pno: "+pno);
        log.info("uploadOpt getOne: "+productService.getOne(pno));
        model.addAttribute("product", productService.getOne(pno));
    }

    @PostMapping("/uploadOpt")
    public String uploadPost(Integer pno, ProductImgVO imgVO, Product_optVO optVO, ProductInfoImgVO infoVO,
                             @RequestParam("opt1") String[] opt1,
                             @RequestParam("opt2") String[] opt2,
                             @RequestParam("colorOpt") String[] colors,
                             @RequestParam("uploadFilesImg") MultipartFile[] imgFiles,
                             @RequestParam("uploadFilesInfo") MultipartFile[] infoFiles) throws IOException {
        log.info("받아온 pno: " + pno);
        log.info("받아온 pOptionName: " + optVO.getPOptionName());
        log.info("받아온 pOptionName2: " + optVO.getPOptionName2());

        for(String option1 : opt1){
            System.out.println("option1: " + option1);
        }

        //옵션 저장
        for (String o1 : opt1){
            optVO.setPOption(o1);
            for (String o2 : opt2){
                optVO.setPOption2(o2);
                for (String c : colors){
                    optVO.setPColor(c);
                    productOptMapper.insert(optVO);
                }
            }
        }
        productOptMapper.updateOption1();
        productOptMapper.updateOption2();
        productOptMapper.updateColor();

        //이미지 저장
        for (MultipartFile img : imgFiles) {
            imgVO.setPImage(img.getOriginalFilename());
            imgVO.setPImagePath(saveImg(img));
            productImgMapper.insert(imgVO);
        }
        for (MultipartFile info : infoFiles){
            infoVO.setPInfoPath(saveImg(info));
            infoVO.setPInformation(info.getOriginalFilename());
            productImgInfoMapper.insert(infoVO);
        }

        return "redirect:/admin/product/list?pno="+pno;
    }
    @GetMapping("/list")
    public void list(int pno, @ModelAttribute SearchVO search,
                     @RequestParam(required = false, defaultValue = "1") int pageNum, Model model)throws Exception{
        List<ProductVO> list = productService.getAll();
        model.addAttribute("list", list);
        model.addAttribute("getOne", productService.getOne(pno));
        model.addAttribute("product", productService.getOne(pno));
        //페이징
        PageInfo<PagingEntity> products = new PageInfo<>(pagingService.getProductList(pageNum, search), 10);
        model.addAttribute("products", products);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("search", search);
    }

    @GetMapping("/modify")
    public void modifyGet(Model model, @RequestParam("pno") int pno){

        model.addAttribute("product", productService.getOne(pno));

        List<Product_optVO> optList = productOptMapper.getOpt(pno);

        List<String> opt1NameList = new ArrayList<>();
        List<String> opt1List = new ArrayList<>();
        List<String> opt2NameList = new ArrayList<>();
        List<String> opt2List = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<Integer> amountList = new ArrayList<>();

        optList.forEach(opt -> { // 옵션, 옵션1, item1
            if (!opt1NameList.contains(opt.getPOptionName()))  opt1NameList.add(opt.getPOptionName());
            if (!opt1List.contains(opt.getPOption()))          opt1List.add(opt.getPOption());
            if (!opt2NameList.contains(opt.getPOptionName2())) opt2NameList.add(opt.getPOptionName2());
            if (!opt2List.contains(opt.getPOption2()))         opt2List.add(opt.getPOption2());
            if (!colorList.contains(opt.getPColor()))          colorList.add(opt.getPColor());
            if (!amountList.contains(opt.getPAmount()))        amountList.add(opt.getPAmount());
        });

        model.addAttribute("opt1Name", opt1NameList);
        model.addAttribute("opt1List", opt1List);
        model.addAttribute("opt2Name", opt2NameList);
        model.addAttribute("opt2List", opt2List);
        model.addAttribute("colorList", colorList);
        model.addAttribute("amount", amountList);

    }

    @PostMapping("/modify")
    public String modifyPost(Product_optVO optVO){
        System.out.println("productModify optVO: " + optVO);

        Product_optVO product_optVO = new Product_optVO();
        product_optVO.setPno(optVO.getPno());
        product_optVO.setPColor(optVO.getPColor());
        product_optVO.setPOption(optVO.getPOption());
        product_optVO.setPOption2(optVO.getPOption2());
        System.out.println("product_optVO: " + product_optVO);

        Product_optVO optVO2 = shopMapper.getProductWithOpt(product_optVO);
        System.out.println("optVO2: " + optVO2);
        product_optVO.setPAmount(optVO.getPAmount());

        orderPageMapper.deductStockWithOpt(product_optVO);

        return "redirect:/admin/product/modify?pno="+optVO.getPno();
    }

    @PostMapping("/getAmount")
    public ResponseEntity<Product_optVO> amountAjax(@RequestBody Product_optVO optVO){
        log.info("amoutAjax 실행 => 받은 optVO: "+optVO);
        Product_optVO result = shopMapper.getOptionPrice(optVO);
        log.info("amoutAjax 실행 => result: "+result);
        log.info("--------------------------------------");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/modifyAmount")
    public ResponseEntity<Product_optVO> amountModifyAjax(@RequestBody Product_optVO optVO){

        Product_optVO product_optVO = new Product_optVO();
        product_optVO.setPno(optVO.getPno());
        product_optVO.setPColor(optVO.getPColor());
        product_optVO.setPOption(optVO.getPOption());
        product_optVO.setPOption2(optVO.getPOption2());
        System.out.println("product_optVO: " + product_optVO);

        Product_optVO optVO2 = shopMapper.getProductWithOpt(product_optVO);
        System.out.println("optVO2: " + optVO2);
        product_optVO.setPAmount(optVO.getPAmount());

        orderPageMapper.deductStockWithOpt(product_optVO);
        Product_optVO result = shopMapper.getProductWithOpt(product_optVO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/stop")
    public String ProductRemove(ProductVO productVO){
        log.info("받아온 productVO: " + productVO);

        if(productVO.getPStatus().equals("판매중지")){
            productVO.setPStatus("판매중");
        }
        else productVO.setPStatus("판매중지");

        productMapper.update(productVO);

        log.info(productVO.getPno()+"번 상품 판매대기");
        return "redirect:/admin/product/list?pno=1";
    }

}
