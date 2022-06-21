package com.green.team4.service.mypage;

import com.green.team4.mapper.mypage.ShipmentMapper;
import com.green.team4.vo.mypage.ShipmentVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService{

    // 의존성 주입
    private final ShipmentMapper shipmentMapper;


    @Override // 배송지 정보 신규 등록
    public int register(ShipmentVO shipmentVO) {
        log.info("ShipmentService => register 실행 => 받은 shipmentVO: "+shipmentVO);
        int result = shipmentMapper.insert(shipmentVO);
        log.info("ShipmentService => register 후 데이터 등록 개수: "+result);
        return result;
    }

    @Override // 배송지 정보 전체 가져오기(mno 단위)
    public List<ShipmentVO> readAll(int mno) {
        log.info("ShipmentService => readAll 실행 => 받은 mno: "+mno);
        List<ShipmentVO> result = shipmentMapper.getAll(mno);
        log.info("ShipmentService => readAll 후 받은 List: "+result);
        return result;
    }

    @Override // 배송지 정보 하나 가져오기(sno 단위)
    public ShipmentVO readOne(int sno) {
        log.info("ShipmentService => readOne 실행 => 받은 sno: "+sno);
        ShipmentVO result = shipmentMapper.getOne(sno);
        log.info("ShipmentService => readOne 후 받은 ShipmentVO: "+result);
        return result;
    }

    @Override // 배송지 정보 수정
    public int modify(ShipmentVO shipmentVO) {
        log.info("ShipmentService => modify 실행 => 받은 shipmentVO: "+shipmentVO);
        int result = shipmentMapper.update(shipmentVO);
        log.info("ShipmentService => modify 후 수정된 개수: "+result);
        return result;
    }

    @Override // 배송지 정보 삭제
    public int remove(int sno) {
        log.info("ShipmentService => remove 실행 => 받은 sno: "+sno);
        int result = shipmentMapper.delete(sno);
        log.info("ShipmentService => remove 후 삭제된 개수: "+result);
        return result;
    }
}
