package com.green.team4.vo.community;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private Long bno;
    private Long mno;
    private String title;
    private String content;
    private Date regDate;
    private Date modDate;
    private Long totalCount; // count(*)
    private String bImg;
    private String nickName;
    private String nonMemberId;
    private String nonMemberPassword;
    private String community;

    // SW 추가
    private int replyCnt; // 해당 게시글 댓글 개수

}
