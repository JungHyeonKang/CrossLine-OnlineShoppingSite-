package com.green.team4.controller.community;

import com.green.team4.security.PrincipalDetails;
import com.green.team4.service.community.BoardService;
import com.green.team4.service.community.ReplyService;
import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.community.PageMaker;
import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequestMapping("/community/board/*")
@Log4j2
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    ReplyService replyService;
    @Autowired
    MemberInfoService memberInfoService;

    PrincipalDetails principalDetails = new PrincipalDetails(new MemberInfoVO());

    // Board List
    @GetMapping("/list")
    public void list(Model model, Criteria criteria) {
        PageMaker pageMaker = new PageMaker(criteria, boardService.getTotal(criteria));
        log.info("list로 이동....");
        model.addAttribute("items", boardService.getPageList(criteria));
        model.addAttribute("pageMaker",pageMaker);

    }

    @GetMapping("/register")
    public void register(Model model,String idSec) throws Exception {

        log.info("register로 이동....");

        try {
            log.info("idSec : " + idSec);
        } catch(Exception e) {
            e.printStackTrace();
        }
        MemberInfoVO memberInfoVO = memberInfoService.findById(idSec);
        model.addAttribute("memberInfoVO",memberInfoVO);

    }
    // Create
    @PostMapping("/register")
    public String register(BoardVO boardVO, @RequestParam("img") MultipartFile img) throws IOException {
        log.info("register.....................");
        log.info("imgPath : " + img);
        log.info("@boardVO : " + boardVO);
        // img가 null이면 경로&uuid파일 생성 안됨
        if(!img.isEmpty()){
            boardVO.setBImg(saveImg(img));
        }
        log.info("boardVO.getImgPath : " + boardVO.getBImg());
        boardService.insert(boardVO);
        return "redirect:list";
    }

    // Read One
    @GetMapping({"/read","/modify"})
    public void read(Model model, Long bno, Criteria criteria) {
        System.out.println("bno : " + bno);
        log.info("read로 이동");
        log.info(boardService.getOne(bno));
        model.addAttribute("item", boardService.getOne(bno));
        if(boardService.getOne(bno).getMno() != null){
            model.addAttribute("memberVO", memberInfoService.getMemberInfo(Math.toIntExact((boardService.getOne(bno).getMno()))));
        }
        model.addAttribute("cri",criteria);
    }

    // Update Board
    @PostMapping("/modify")
    // redirect시에 컨트롤러가 속성을 선택하는데 사용할 수 있는 모델 인터페이스의 분화. get 방식임.
    // 리다이렉트가 발생하면 원래 요청은 끊어지고, 새로운 HTTP get요청 시작으로 인해 리다이렉트 실행 이전의 모델 데이터는 소멸한다.
    // get방식을 통해 url에 데이터를 붙여 보낼 수 있지만, 보기 지저분하고 보안에 취약할 수 있기 때문에 RedirectAttributes를
    // 사용한다.
    public String modify(BoardVO vo, Criteria criteria, RedirectAttributes redirectAttributes) {
        boardService.modify(vo);
        log.info(criteria);
        log.info("@VO: " + vo);
        redirectAttributes.addAttribute("page",criteria.getPage());
        redirectAttributes.addAttribute("pageNum",criteria.getPageNum());
        log.info("수정 완료");
        return "redirect:/community/board/list";
    }

    // Delete Board
    @PostMapping("/delete")
    public String delete(@RequestParam("bno") Long bno){
        replyService.deleteAll(bno);
        boardService.delete(bno);

        log.info("삭제(수정) 완료");
        return "redirect:list";
    }

    // Test
    @GetMapping("id/{idSec}")
    public ResponseEntity<Integer> test(@PathVariable("idSec") String idSec){
        log.info("@@ idSec : " + idSec);
        MemberInfoVO memberInfoVO = memberInfoService.findById(idSec);
        if (idSec.isEmpty()){


        }
        return new ResponseEntity<>((memberInfoVO.getMno()), HttpStatus.OK);
    }


    @GetMapping("/insert")
    public void insert(){
        log.info("BoardController reply 댓글 등록");
    }


    // variable & method

    // File Upload
    @Value("${com.green.upload.path}") //application.properties 변수
    private String uploadPath;
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




}
