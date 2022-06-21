package com.green.team4.vo.shop;

import com.green.team4.vo.mypage.ReviewMpVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewPageVO {

    List<ReviewMpVO> list;
    PagingVO pageInfo;
}
