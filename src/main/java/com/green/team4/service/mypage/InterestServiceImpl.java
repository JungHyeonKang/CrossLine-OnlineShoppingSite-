package com.green.team4.service.mypage;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.mypage.InterestMapper;
import com.green.team4.vo.mypage.InterestVO;
import com.green.team4.vo.mypage.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService{

    // 의존성 주입
    private final InterestMapper interestMapper;

    @Override
    public int readAllCnt(int mno) {
        log.info("InterestService => readAllCnt 실행 => 받은 mno: "+mno);
        int result = interestMapper.getAllCnt(mno);
        return result;
    }

    @Override
    public int register(InterestVO interestVO) { // 찜목록 신규 등록
        log.info("InterestService => register 실행 => 받은 interestVO: "+interestVO);
        int result = interestMapper.insert(interestVO);
        log.info("InterestService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override
    public List<InterestVO> readAll(int mno, int pageNum) { // 찜목록 전체 가져오기(mno 단위)
        log.info("InterestService => readAll 실행 => 받은 mno: "+mno);
        log.info("InterestService => readAll 실행 => 받은 pageNum: "+pageNum);
        List<InterestVO> list = interestMapper.getAll(mno);
        log.info("InterestService => readAll 실행 후 받은 list: "+list);
        return list;
    }

    @Override
    public List<InterestVO> readAllWithSearch(int mno, int pageNum,SearchVO searchVO) { // 찜목록 전체 가져오기(mno 단위)
        log.info("InterestService => readAllWithSearch 실행 => 받은 mno: "+mno);
        log.info("InterestService => readAllWithSearch 실행 => 받은 pageNum: "+pageNum);
        log.info("InterestService => readAllWithSearch 실행 => 받은 searchVO: "+searchVO);
        searchVO.setMno(mno); // 검색 vo에 mno set
        PageHelper.startPage(pageNum,5); // 가져올 데이터 페이지 번호, 페이지 당 데이터 개수
        List<InterestVO> list = interestMapper.getAllWithSearch(searchVO);
        log.info("InterestService => readAll 실행 후 받은 list: "+list);
        return list;
    }

    @Override
    public int remove(int mno, int pno) { // 찜목록 하나 삭제
        log.info("InterestService => remove 실행 => 받은 mno: "+mno);
        log.info("InterestService => remove 실행 => 받은 pno: "+pno);
        int result = interestMapper.delete(mno,pno);
        log.info("InterestService => remove 실행 => 삭제된 데이터 개수: "+result);
        return result;
    }

    @Override
    public int getOne(InterestVO interestVO) {
        return interestMapper.getOne(interestVO);
    }

    @Override
    public int removeByPno(int pno) {
        log.info("InterestService => removeByPno 실행 => 받은 pno: "+pno);
        int result = interestMapper.deleteByPno(pno);
        log.info("InterestService => removeByPno 실행 후 삭제된 개수: "+result);
        return result;
    }
}

