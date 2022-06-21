package com.green.team4.vo.account;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class LoginVO {
    private String id;
    private String password;
}
