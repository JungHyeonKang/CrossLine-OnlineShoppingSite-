package com.green.team4.vo.admin;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductImgVO {
    private int ino;
    private int pno;
    private String pName;
    private String pThumbnail;
    private String pImage;
    private String pImagePath;
}
