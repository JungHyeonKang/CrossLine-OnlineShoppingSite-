package com.green.team4.vo.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
    private Long rno;
    private Long bno;
    private Long mno;
    private String replyPassword;
    private String reply;
    private String replyer;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm")
    private Date replyDate;
    private Date modDate;
}