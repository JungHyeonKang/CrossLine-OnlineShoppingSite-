package com.green.team4.controller.mypage;

import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.service.mypage.ShipmentService;
import com.green.team4.vo.mypage.MemberInfoVO;
import com.green.team4.vo.mypage.ShipmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/mypage/memberInfo/*")
@RequiredArgsConstructor
public class MemberInfoController {

    // 의존성 주입
    private final MemberInfoService memberInfoService;
    private final ShipmentService shipmentService;


    // memberInfo -----------------------------------------------------------------------------------------

    @GetMapping("/read") // 개인정보, 배송지 정보 가져오기(개인정보 수정화면 포함됨)
    public void readOne(int mno, Model model){
        log.info("MemberInfoController => readOne(GET) 실행 => 받은 mno: "+mno);

        // MemberInfo 가져오기
        MemberInfoVO memberInfoVO = memberInfoService.readOne(mno);
        model.addAttribute("memberInfo",memberInfoVO);

        // ShipmentInfo 가져오기
        List<ShipmentVO> shipList = shipmentService.readAll(mno);
        model.addAttribute("shipmentList",shipList);
    }

    @PostMapping("/memModify") // 개인정보 수정 진행 (회원 전용)
    public String memberModify(MemberInfoVO memberInfoVO, Model model){
        log.info("MemberInfoController => memberModify(POST) 실행 => 받은 MemberInfoVO: "+memberInfoVO);
        memberInfoService.modifyByMember(memberInfoVO);
        return "redirect:/mypage/memberInfo/read?mno="+memberInfoVO.getMno();
    }

    @GetMapping("/memDelete") // 회원탈퇴 화면 가져오기
    public void memberDelete(int mno, Model model){ // 회원 탈퇴 화면 가져오기
        log.info("MemberInfoController => delete(GET) 실행 => 받은 mno: "+mno);
        model.addAttribute("mno",mno);
        log.info("회원탈퇴 화면 가져오기");
    }

    @PostMapping("/memDelete") // 회원 탈퇴 진행
    public String delete(int mno, String dMCategory, String dMContent){
        log.info("MemberInfoController => delete(POST) 실행 => 받은 mno: "+mno);
        log.info("MemberInfoController => delete(POST) 실행 => 받은 dMCategory: "+dMCategory);
        log.info("MemberInfoController => delete(POST) 실행 => 받은 dMContent: "+dMContent);
        memberInfoService.remove(mno,dMCategory,dMContent);
        return "redirect:/mypage/main?mno="+1; // ★ 추후 웹사이트 메인페이지로 돌아가는 것으로 수정 예정
    }

    // shipmentInfo -----------------------------------------------------------------------------------------

    @PostMapping("/shipAdd") // 배송지 주소 신규 추가 진행
    public ResponseEntity<Integer> shipAddAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipAdd(POST/AJAX) 실행 => 받은 shipmentVO: "+shipmentVO);
        int addCnt = shipmentService.register(shipmentVO);
        return new ResponseEntity<>(addCnt, HttpStatus.OK);
    }

    @PostMapping("/shipModify") // 배송지 수정 진행
    public ResponseEntity<Integer> shipModifyAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipModify(POST) 실행 => 받은 shipmentVO: "+shipmentVO);
        int modCnt = shipmentService.modify(shipmentVO);
        return new ResponseEntity<>(modCnt,HttpStatus.OK);
    }

    @PostMapping("/shipDelete") // 배송지 삭제 진행
    public ResponseEntity<Integer> shipDeleteAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipDelete(POST) 실행 => 받은 shipmentVO: "+shipmentVO);
        log.info("삭제 대상 sno: "+shipmentVO.getSno());
        int delCnt = shipmentService.remove(shipmentVO.getSno());
        return new ResponseEntity<>(delCnt,HttpStatus.OK);
    }

    @PostMapping("/shipOne")
    public ResponseEntity<List<ShipmentVO>> shipGetOneAjax(@RequestBody ShipmentVO shipmentVO){
        log.info("MemberInfoController => shipGetOneAjax(POST) 실행 => 받은 shipmentVO: "+shipmentVO);

        // 개인 배송지 모두 가져오기
        List<ShipmentVO> shipList = shipmentService.readAll(shipmentVO.getMno());
        log.info("shipList: "+shipList);

        return new ResponseEntity<>(shipList,HttpStatus.OK);
    }


}
