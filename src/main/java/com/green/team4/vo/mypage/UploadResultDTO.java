package com.green.team4.vo.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
// Serializable: 직렬화 (Stream으로 바꾸는 것과 거의 같은 개념)

    private String fileName;
    private String uuid;
    private String folderPath;


    // 이미지 URL 만들기
    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8"); // 인코딩 방법
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    // 썸네일 URL 만들기
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+"s_"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

}
