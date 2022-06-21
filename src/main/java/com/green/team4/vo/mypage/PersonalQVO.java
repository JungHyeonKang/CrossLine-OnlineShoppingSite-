package com.green.team4.vo.mypage;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonalQVO {

    private int pqNo; // 1:1문의글번호
    private int mno; // 회원번호

    private String pqCategory; // 문의글 대분류(상품문의/일반문의)
    private String ono; // 주문번호 (상품문의 시 기재 요청)
    private Integer pno; // 상품번호 (상품문의 시 기재 요청)

    private String pqTitle; // 문의글 제목
    private String pqContent; // 문의글 본문
    private LocalDateTime pqRegDate; // 문의글 등록일

    private String pqReplyTitle; // 답변글 제목 (답변 등록은 업데이트로)
    private String pqReplyContent; // 답변글 본문 (답변 등록은 업데이트로)
    private LocalDateTime pqReplyDate; // 문의글 답변일 (답변 등록할때 별도 업데이트 필요)

    private String pqStatus; // 접수완료 -> 답변완료

    // 첨부파일 리스트
    private List<PersonalQFilesVO> personalQFilesList;
}
