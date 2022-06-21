package com.green.team4.vo.mypage;

import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ExchangeFilesVO {

    private int eno; // 취소/반품/교환 신청번호
    private int ino; // 파일 번호
    private String img_name; // 파일 이름
    private String path; // 파일 경로
    private String uuid; // 파일 uuid

    public String getImageURL() {
        try {
            return URLEncoder.encode(path + "/" + uuid+"_" + img_name, "UTF-8");

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(path + "/" + "s_" + uuid+"_" + img_name, "UTF-8");

        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
