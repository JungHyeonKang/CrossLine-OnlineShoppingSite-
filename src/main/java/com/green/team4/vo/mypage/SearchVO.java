package com.green.team4.vo.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchVO {

    // 검색 기능
    private Integer mno;
    private String keyword;
    private String search;

}
