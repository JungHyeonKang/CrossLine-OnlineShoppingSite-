package com.green.team4.service.mypage;

import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.mypage.PersonalQFilesMapper;
import com.green.team4.mapper.mypage.PersonalQMapper;
import com.green.team4.vo.mypage.PersonalQVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonalQServiceImpl implements PersonalQService{

    private final PersonalQMapper personalQMapper;
    private final PersonalQFilesMapper personalQFilesMapper;


    // 문의글 첨부파일 가져오는 메서드
    private List<PersonalQVO> getFiles(List<PersonalQVO> list){

        // 문의글 첨부파일 가져오기
        list.forEach(i->{
            i.setPersonalQFilesList(personalQFilesMapper.getAllByPqNo(i.getPqNo()));
        });

        return list;
    }


    @Override // 회원별 1:1문의글 개수 가져오기
    public int readAllCnt(int mno) {
        log.info("PersonalQService => readAllCnt 실행 => 받은 mno: "+mno);
        int result = personalQMapper.getAllCnt(mno);
        log.info("PersonalQService => readAllCnt 실행 후 받은 데이터 개수: "+result);
        return result;
    }

    @Override // 1:1문의글 신규 등록
    public int register(PersonalQVO personalQVO) {
        log.info("PersonalQService => register 실행 => 받은 personalQVO: "+personalQVO);

        // 등록일 및 상태 업데이트
        personalQVO.setPqRegDate(LocalDateTime.now()); // 등록일 저장
        personalQVO.setPqStatus("접수완료");

        // 문의글 등록
        int result = personalQMapper.insert(personalQVO);
        int key = personalQVO.getPqNo();
        log.info("key: "+key);

        // 문의글 첨부파일 등록
        if(personalQVO.getPersonalQFilesList() != null){
            personalQVO.getPersonalQFilesList().forEach(file->{
                file.setPqNo(key); // 문의글 DB 등록된 PK 바로 가져와서 이미지 데이터에 저장
                log.info("등록 파일: "+file);
                personalQFilesMapper.insert(file);
            });
        }

        log.info("PersonalQService => register 실행 후 등록된 데이터 개수: "+result);
        return result;
    }

    @Override // 1:1문의글 가져오기(mno 단위)
    public List<PersonalQVO> readAllByMno(int mno, int pageNum) {
        log.info("PersonalQService => readAllByMno 실행 => 받은 mno: "+mno);
        PageHelper.startPage(pageNum,5);
        List<PersonalQVO> personalQList = personalQMapper.getAllByMno(mno); // 문의글 가져오기
        List<PersonalQVO> finalList = getFiles(personalQList); // 문의글 첨부파일 가져오기
        log.info("PersonalQService => readAllByMno 실행 후 받은 finalList: "+finalList);
        return finalList;
    }

    @Override // 1:1문의글 가져오기(pno 단위)
    public List<PersonalQVO> readAllByPno(int pno) {
        log.info("PersonalQService => readAllByPno 실행 => 받은 pno: "+pno);
        List<PersonalQVO> personalQList = personalQMapper.getAllByPno(pno); // 문의글 가져오기
        List<PersonalQVO> finalList = getFiles(personalQList); // 문의글 첨부파일 가져오기
        log.info("PersonalQService => readAllByPno 실행 후 받은 finalList: "+finalList);
        return finalList;
    }

    @Override // 1:1문의글 가져오기(ono 단위)
    public List<PersonalQVO> readAllByOno(String ono) {
        log.info("PersonalQService => readAllByOno 실행 => 받은 ono: "+ono);
        List<PersonalQVO> personalQList = personalQMapper.getAllByOno(ono); // 문의글 가져오기
        List<PersonalQVO> finalList = getFiles(personalQList); // 문의글 첨부파일 가져오기
        log.info("PersonalQService => readAllByOno 실행 후 받은 finalList: "+finalList);
        return personalQList;
    }

    @Override // 1:1문의글 가져오기(전체)
    public List<PersonalQVO> readAllAdmin(int pageNum) {
        log.info("PersonalQService => readAllAdmin 실행");
        PageHelper.startPage(pageNum,5);
        List<PersonalQVO> personalQList = personalQMapper.getAllAdmin(); // 문의글 가져오기
        List<PersonalQVO> finalList = getFiles(personalQList); // 문의글 첨부파일 가져오기
        log.info("PersonalQService => readAllAdmin 실행 후 받은 finalList: "+finalList);
        return personalQList;
    }

    @Override // 1:1문의글 하나 가져오기
    public PersonalQVO readOne(int pqNo) {
        log.info("PersonalQService => readOne 실행 => 받은 pqNo: "+pqNo);
        PersonalQVO personalQVO = personalQMapper.getOne(pqNo); // 문의글 가져오기
        personalQVO.setPersonalQFilesList(personalQFilesMapper.getAllByPqNo(personalQVO.getPqNo())); // 문의글 첨부파일 가져오기
        log.info("PersonalQService => readOne 실행 후 받은 personalQVO: "+personalQVO);
        return personalQVO;
    }

    @Override // 1:1문의글 수정
    public int modify(PersonalQVO personalQVO) {
        log.info("PersonalQService => modify 실행 => 받은 personalQVO: "+personalQVO);
        int result = personalQMapper.update(personalQVO);
        log.info("PersonalQService => modify 실행 후 수정된 데이터 개수: "+result);
        return result;
    }

    @Override // 1:1문의글 삭제
    public int remove(int pqNo) {
        log.info("PersonalQService => remove 실행 => 받은 pqNo: "+pqNo);
        personalQFilesMapper.delete(pqNo); // 첨부파일 먼저 삭제
        int result = personalQMapper.delete(pqNo); // 글 삭제
        log.info("PersonalQService => remove 실행 후 삭제된 데이터 개수: "+result);
        return result;
    }
}
