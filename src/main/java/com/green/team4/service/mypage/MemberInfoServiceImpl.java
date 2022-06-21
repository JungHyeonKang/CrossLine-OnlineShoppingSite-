package com.green.team4.service.mypage;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.admin.MailMapper;
import com.green.team4.mapper.community.BoardMapper;
import com.green.team4.mapper.community.ReplyMapper;
import com.green.team4.mapper.mypage.*;
import com.green.team4.vo.community.BoardVO;
import com.green.team4.vo.community.ReplyVO;
import com.green.team4.vo.mypage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService{

    private final InterestMapper interestMapper;
    private final CartMapper cartMapper;
    private final MailMapper mailMapper;
    private final ReviewMpMapper reviewMpMapper;
    private final ReviewFilesMpMapper reviewFilesMpMapper;
    private final DeliveryMapper deliveryMapper;
    private final ExchangeMapper exchangeMapper;
    private final ExchangeFilesMapper exchangeFilesMapper;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShipmentMapper shipmentMapper;
    private final MemberInfoMapper memberInfoMapper;
    private final DeleteMemMapper deleteMemMapper;
    private final PersonalQMapper personalQMapper;
    private final PersonalQFilesMapper personalQFilesMapper;
    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;


    // 회원정보 전체 가져오기
    @Override
    public List<MemberInfoVO> readAll(int pageNum) {
        log.info("MemberInfoService => readAll 실행");
        PageHelper.startPage(pageNum,10);
        List<MemberInfoVO> memList = memberInfoMapper.getAll();
        memList.forEach(System.out::println);
        return memList;
    }

    // 회원정보 하나 가져오기
    @Override
    public MemberInfoVO readOne(int mno) {
        log.info("MemberInfoService => read 실행 => 받은 mno: "+mno);
        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(mno);
        log.info("memberInfoMapper 호출 => getOne 후 받은 memberInfoVO: "+memberInfoVO);
        return memberInfoVO;
    }

    // 회원정보 수정 (회원 전용)
    @Override
    public int modifyByMember(MemberInfoVO memberInfoVO) {
        log.info("MemberInfoService => modifyByMember(회원전용) 실행 => 받은 MemberInfoVO: "+memberInfoVO);
        log.info("수정 대상 mno: "+memberInfoVO.getMno());

        // 비밀번호 조정
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = memberInfoVO.getPassword(); // 사용자 입력 비밀번호
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        log.info("encodePassword: "+encPassword);

        // 조정 비밀번호 반영 및 회원정보 수정
        memberInfoVO.setPassword(encPassword);
        int modResult = memberInfoMapper.updateByMember(memberInfoVO);
        log.info("memberInfoMapper 호출 => update 후 수정된 개수: "+modResult);

        return modResult;
    }

    // 회원정보 수정 (관리자 전용)
    @Override
    public int modifyByAdmin(MemberInfoVO memberInfoVO) {
        log.info("MemberInfoService => modifyByAdmin(관리자전용) 실행 => 받은 MemberInfoVO: "+memberInfoVO);
        int modResult = memberInfoMapper.updateByAdmin(memberInfoVO);
        log.info("memberInfoMapper 호출 => update 후 수정된 개수: "+modResult);
        return modResult;
    }

    // 회원정보 삭제(회원탈퇴) - 회원 관련 모든 정보 삭제
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(int mno,String delCategory, String delContent) {
        log.info("MemberInfoService => remove 실행 => 받은 mno: "+mno);
        log.info("MemberInfoService => remove 실행 => 받은 delCategory: "+delCategory);
        log.info("MemberInfoService => remove 실행 => 받은 delContent: "+delContent);

        // (1) 탈퇴회원 데이터 입력(tbl_deleteMem) --------------------------------------------------------
        MemberInfoVO memberInfoVO = memberInfoMapper.getOne(mno);

        log.info("기존 회원데이터 => 탈퇴 회원 데이터로 덮어쓰기 시작");

        // 기존 회원데이터 덮어쓰기
        DeleteMemVO deleteMemVO = new DeleteMemVO();
        deleteMemVO.setId(memberInfoVO.getId());
        deleteMemVO.setPassword(memberInfoVO.getPassword());
        deleteMemVO.setName(memberInfoVO.getName());
        deleteMemVO.setNickName(memberInfoVO.getNickName());
        deleteMemVO.setGender(memberInfoVO.getGender());
        deleteMemVO.setSSNum(memberInfoVO.getSSNum());
        deleteMemVO.setEmail(memberInfoVO.getEmail());
        deleteMemVO.setPhoneNum(memberInfoVO.getPhoneNum());
        deleteMemVO.setMobileNum(memberInfoVO.getMobileNum());
        deleteMemVO.setPostcode(memberInfoVO.getPostcode());
        deleteMemVO.setAddress(memberInfoVO.getAddress());
        deleteMemVO.setDetailAddress(memberInfoVO.getDetailAddress());
        deleteMemVO.setInterestSports(memberInfoVO.getInterestSports());
        deleteMemVO.setAuth(memberInfoVO.getAuth());
        deleteMemVO.setGrade(memberInfoVO.getGrade());
        deleteMemVO.setPoint(memberInfoVO.getPoint());

        // 회원탈퇴 사유 가져와서 set
        deleteMemVO.setDMCategory(delCategory);
        deleteMemVO.setDMContent(delContent);
        deleteMemVO.setDMDate(LocalDateTime.now());

        log.info("기존 회원데이터 => 탈퇴 회원 데이터로 덮어쓰기 끝");

        // DB 저장
        int delSaveCnt = deleteMemMapper.insert(deleteMemVO);
        log.info("MemberInfoService => remove 실행 => deleteMemMapper 실행 후 입력된 데이터 개수: "+delSaveCnt);

        // (2) 회원 데이터 모두 삭제 ------------------------------------------------------------------------

        // (2-1) 커뮤니티 데이터 삭제 -----------------------------------------------------------------------
        // 댓글 삭제
        List<ReplyVO> replyList = replyMapper.getAllByMno(mno);
        replyList.forEach(reply->{
            replyMapper.deleteOne(reply.getRno());
        });
        log.info("댓글 삭제 완료");

        // 게시글 삭제
        List<BoardVO> boardList = boardMapper.getAllByMno(mno);
        boardList.forEach(board->{
            boardMapper.delete(board.getBno());
        });
        log.info("게시글 삭제 완료");

        // (2-2) 쇼핑 및 마이페이지 데이터 삭제 -----------------------------------------------------------------------

        // 찜목록 삭제
        List<InterestVO> interestList = interestMapper.getAll(mno);
        interestList.forEach(i->interestMapper.delete(mno,i.getPno()));
        log.info("찜목록 삭제 완료");

        // 장바구니 삭제
        List<CartVO> cartList = cartMapper.getAll(mno);
        cartList.forEach(i->cartMapper.delete(i.getCno()));
        log.info("장바구니 삭제 완료");

        // 메일 삭제
        mailMapper.delete(mno);
        log.info("메일 삭제 완료");

        // 리뷰글 삭제
        List<ReviewMpVO> reviewList = reviewMpMapper.getAllByMno(mno);
        reviewList.forEach(i->{
            reviewFilesMpMapper.delete(i.getRno()); // 첨부파일 삭제
            reviewMpMapper.deleteByRno(i.getRno()); // 글 삭제
        });
        log.info("리뷰글 삭제 완료");

        // 문의글 삭제
        List<PersonalQVO> pqList = personalQMapper.getAllByMno(mno);
        pqList.forEach(i->{
            personalQFilesMapper.delete(i.getPqNo());
            personalQMapper.delete(i.getPqNo());
        });
        log.info("문의글 삭제 완료");

        // 배송 데이터 삭제
        List<DeliveryVO> deliveryList = deliveryMapper.getAllByMno(mno);
        deliveryList.forEach(i->deliveryMapper.delete(i.getDno()));
        log.info("배송데이터 삭제 완료");

        // 취소/반품/교환 데이터 삭제
        List<ExchangeVO> exList = exchangeMapper.getAll(mno);
        exList.forEach(e->{
            exchangeFilesMapper.delete(e.getEno()); // 신청서 첨부파일 삭제
            exchangeMapper.delete(e.getEno()); // 신청서 삭제
        });
        log.info("취소반품교환 삭제 완료");

        // 주문 내역 삭제
        List<OrderVO> orderList = orderMapper.getAll(mno);
        orderList.forEach(o->{
            orderItemMapper.delete(o.getOno()); // 주문 상품 삭제
            orderMapper.delete(o.getOno()); // 주문서 삭제
        });
        log.info("주문내역 삭제 완료");

        // 개인 배송지 정보 삭제
        List<ShipmentVO> shipList = shipmentMapper.getAll(mno);
        shipList.forEach(ship->shipmentMapper.delete(ship.getSno())); // 배송지 삭제
        log.info("개인배송지 삭제 완료");

        // 회원정보 삭제
        int delResult = memberInfoMapper.delete(mno);
        log.info("memberInfoMapper 호출 => remove 후 삭제된 개수: "+delResult);
        log.info("회원 최종 삭제 완료");

        return delResult;
    }

    //주문 페이지 서비스
    @Override
    public MemberInfoVO getMemberInfo(int mno) {
        return memberInfoMapper.getMemberInfo(mno);
    }

    @Override
    public MemberInfoVO findById(String id) {
        log.info("security id 조회 service: " + id);
        return memberInfoMapper.findByUsername(id);
    }

    @Override
    public MemberInfoVO findPw(MemberInfoVO vo) {
        log.info("비밀번호 찾기 조회 결과: "+vo);
        return memberInfoMapper.findPw(vo);
    }


}

