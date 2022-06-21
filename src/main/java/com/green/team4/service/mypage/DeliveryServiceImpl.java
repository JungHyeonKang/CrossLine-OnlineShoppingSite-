package com.green.team4.service.mypage;

import com.green.team4.mapper.mypage.DeliveryMapper;
import com.green.team4.vo.mypage.DeliveryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryMapper deliveryMapper;


    @Override
    public int register(DeliveryVO deliveryVO) {
        log.info("DeliveryService => register 실행 => 받은 deliveryVO: "+deliveryVO);
        int result = deliveryMapper.insert(deliveryVO);
        log.info("DeliveryService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override
    public DeliveryVO readOneByDno(int dno) {
        log.info("DeliveryService => readOneByDno 실행 => 받은 dno: "+dno);
        DeliveryVO deliveryVO = deliveryMapper.getOneByDno(dno);
        log.info("DeliveryService => readOneByDno 실행 후 받은 deliveryVO: "+deliveryVO);
        return deliveryVO;
    }

    @Override
    public DeliveryVO readOneByOINoDCat(int oINo) {
        log.info("DeliveryService => readOneByOINoDCat 실행 => 받은 oINo: "+oINo);
//        log.info("DeliveryService => readOneByOINoDCat 실행 => 받은 oINo: "+dCategory);
        DeliveryVO deliveryVO = deliveryMapper.getOneByOINoDCat(oINo);
        log.info("DeliveryService => readOneByOINoDCat 실행 후 받은 deliveryVO: "+deliveryVO);
        return deliveryVO;
    }

    @Override
    public List<DeliveryVO> readAll() {
        log.info("DeliveryService => readAll 실행");
        List<DeliveryVO> delList = deliveryMapper.getAll();
        log.info("DeliveryService => readAll 실행 후 받은 delList: "+delList);
        return delList;
    }

    @Override
    public List<DeliveryVO> readAllByMno(int mno) {
        log.info("DeliveryService => readAllByMno 실행 => 받은 mno: "+mno);
        List<DeliveryVO> delList = deliveryMapper.getAllByMno(mno);
        log.info("DeliveryService => readAllByMno 실행 후 받은 delList: "+delList);
        return delList;
    }

    @Override
    public int modify(DeliveryVO deliveryVO) {
        log.info("DeliveryService => modify 실행 => 받은 deliveryVO: "+deliveryVO);
        int result = deliveryMapper.update(deliveryVO);
        log.info("DeliveryService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int remove(int dno) {
        log.info("DeliveryService => remove 실행 => 받은 dno: "+dno);
        int result = deliveryMapper.delete(dno);
        log.info("DeliveryService => remove 실행 후 삭제된 데이터 개수: "+result);
        return result;
    }
}
