package com.green.team4.service.mypage;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.mypage.OrderItemMapper;
import com.green.team4.mapper.mypage.OrderMapper;
import com.green.team4.vo.mypage.OrderItemVO;
import com.green.team4.vo.mypage.OrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // 의존성 주입
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;


    @Override
    public int readAllCnt(int mno) {
        log.info("OrderService => readAllCnt 실행 => 받은 mno: "+mno);
        int result = orderMapper.getAllCnt(mno);
        return result;
    }

    @Override
    public List<OrderVO> readAll(int mno, int pageNum) { // 주문목록 모두 가져오기(mno 단위)

        // 주문서 리스트 가져오기
        log.info("OrderService => readAll 실행 => 받은 mno: "+mno);
        log.info("OrderService => readAll 실행 => 받은 pageNum: "+pageNum);
        PageHelper.startPage(pageNum,5); // 가져올 데이터 페이지 번호, 페이지 당 데이터 개수
        List<OrderVO> orderList = orderMapper.getAll(mno);
        log.info("OrderService => readAll 실행 후 받은 orderList: "+orderList);

        // 주문서별 주문 상품 List 가져와서 저장
        orderList.forEach(i->{
            List<OrderItemVO> orderItemList = orderItemMapper.getAll(i.getOno());
            i.setOrderItemList(orderItemList);
        });

        return orderList;
    }

    @Override
    public OrderVO readOne(String ono) { // 주문내역 하나 가져오기

        // 주문서 하나 가져오기
        log.info("OrderService => readOne 실행 => 받은 ono: "+ono);
        OrderVO orderVO = orderMapper.getOne(ono);
        log.info("OrderService => readOne 실행 후 받은 orderVO: "+orderVO);

        // 주문서에 해당하는 주문 상품 List 가져와서 저장
        List<OrderItemVO> orderItemList = orderItemMapper.getAll(orderVO.getOno());
        orderVO.setOrderItemList(orderItemList);

        return orderVO;
    }

    //관리자용
    @Override
    public List<OrderVO> readAllAdmin() { // 관리자
        // 주문서 모두 가져오기(모든 회원)
        log.info("OrderService => readAllAdmin 실행");
        List<OrderVO> orderList = orderMapper.getAllAdmin();
        log.info("OrderService => readAllAdmin 실행 후 받은 orderList: "+orderList);

        // 주문서에 해당하는 주문 상품 모두 가져와서 저장
        orderList.forEach(i->{
            List<OrderItemVO> orderItemList = orderItemMapper.getAll(i.getOno());
            i.setOrderItemList(orderItemList);
        });

        return orderList;
    }

    @Override
    public int modifyItem(OrderItemVO orderItemVO) { // 관리자 배송,취소/반품/교환 상태 수정
        log.info("OrderService => modifyItem 실행 => 받은 orderItemVO: "+orderItemVO);
        int result = orderItemMapper.update(orderItemVO);
        log.info("OrderService => modifyItem 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int modifyStatus(OrderVO orderVO) { // 관리자 결제상태 업데이트(취소/반품/교환때 활용)
        log.info("OrderService => modifyStatus 실행 => 받은 orderVO: "+orderVO);
        int result = orderMapper.update(orderVO);
        log.info("OrderService => modifyStatus 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int register(OrderVO orderVO) { // 취소/반품/교환 새주문서 등록
        log.info("OrderService => register 실행 => 받은 orderVO: "+orderVO);
        int result = orderMapper.insert(orderVO); // 주문서 등록
        orderVO.getOrderItemList().forEach(i->{
            orderItemMapper.insert(i); // 주문상품 List에서 하나씩 꺼내서 등록
        });
        log.info("OrderService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override
    public List<OrderVO> readAllByThisMonth(int mno) {
        log.info("OrderService => readAllByThisMonth 실행 => 받은 mno: "+mno);
        List<OrderVO> orderList = orderMapper.getAllByThisMonth(mno);
        return orderList;
    }

    @Override
    public List<OrderVO> readAllByMno(int mno) {
        log.info("OrderService => readAllByMno 실행 => 받은 mno: "+mno);
        List<OrderVO> orderList = orderMapper.getAll(mno);
        return orderList;
    }
}
