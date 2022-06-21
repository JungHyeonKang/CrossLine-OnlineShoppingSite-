package com.green.team4.vo.admin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductInfoImgVO {
    private int infoNo;
    private int pno;
    private String pName;
    private String pInformation;
    private String pInfoPath;
}
