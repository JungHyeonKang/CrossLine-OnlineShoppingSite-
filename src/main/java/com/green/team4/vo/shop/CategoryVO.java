package com.green.team4.vo.shop;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CategoryVO {
    /* 카테고리 등급 */
    private int tier;

    /* 카테고리 이름 */
    private String pCateName;

    /* 카테고리 넘버 */
    private String pCateCode;

    /* 상위 카테고리 */
    private String cateParent;
}
