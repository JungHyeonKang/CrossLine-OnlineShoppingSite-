package com.green.team4.vo.shop;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderPageVO {
    private List<OrderPageItemVO> orders;
}
