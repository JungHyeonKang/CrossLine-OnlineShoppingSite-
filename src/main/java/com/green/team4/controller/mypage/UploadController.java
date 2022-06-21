package com.green.team4.controller.mypage;


import com.green.team4.vo.mypage.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    // 파일 업로드 경로
    @Value("${com.green.upload.path}") // application.properties 에서 경로 가져옴
    private String uploadPath;

    // 클래스 내 별도 사용 메서드
    private String makeFolder(){ // 파일 저장 폴더 만들기(탐색기)
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        // 폴더 생성
        File uploadFolder = new File(uploadPath, folderPath);
        if(uploadFolder.exists()==false) uploadFolder.mkdirs();

        return folderPath;
    }

    // 파일 업로드
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles){
        log.info("UploadController => uploadFile 실행 => 받은 uploadFiles: "+uploadFiles);
        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            if(uploadFile.getContentType().startsWith("image")==false){
                log.warn("이 파일은 이미지 형태가 아닙니다.");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename(); // 원본 파일 이름 가져오기
//            log.info("originalName: "+originalName);
            String fileName = originalName.substring(originalName.lastIndexOf("\\")+1); // 파일 이름 수정
//            log.info("fileName: "+fileName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_" 를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" +fileName;
            Path savePath = Paths.get(saveName);
            try{
                // 파일을 경로에 저장
                uploadFile.transferTo(savePath);

                // 썸네일 생성
                String thumbnailSaveName = uploadPath+File.separator+folderPath+File.separator+"s_"+uuid+"_"+fileName; // s_를 추가로 붙여줌
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(),thumbnailFile,300,300);

                // html에 넘겨줄 List에 저장
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    // 파일 Display
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
//        log.info("UploadController => getFile 실행 => 받은 fileName: "+fileName);
        ResponseEntity<byte[]> result = null;
        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
//            log.info("fileName: "+fileName);
            File file = new File(uploadPath+File.separator+srcFileName);

//            log.info("File: "+file);
            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
        } catch (Exception e){
//            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;

    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
        log.info("UploadController => removeFile 실행 => 받은 fileName: "+fileName);
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("srcFileName: "+srcFileName);
            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }
}
