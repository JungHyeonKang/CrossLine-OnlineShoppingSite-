package com.green.team4.controller.community;

import com.green.team4.service.community.ReplyService;
import com.green.team4.vo.community.Criteria;
import com.green.team4.vo.community.PageMaker;
import com.green.team4.vo.community.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/community/reply/*")
@Log4j2
public class ReplyController {
    @Autowired
    ReplyService replyService;


    @PostMapping(path="register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<Long> register(@RequestBody ReplyVO replyVO){
        log.info("@@@replyRegister.replyVO: "+replyVO);
        replyService.insert(replyVO);
        return new ResponseEntity<>(replyVO.getRno(), HttpStatus.OK);

    }

    @GetMapping(value = "getList/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> getCommentList(@PathVariable("bno") Long bno, @PathVariable("page") Long page){
        log.info("@@@@@@@@@@@@@@ getList 작동 bno :" + bno + "page :"+ page);
        Map<String,Object> map = new HashMap<>();
        Criteria criteria = new Criteria(page*10-9,1000L);
        PageMaker pageMaker = new PageMaker(criteria, replyService.getTotal(criteria));
        map.put("pageMaker",pageMaker);
        map.put("list",replyService.getPageList(criteria,bno));
        // 얘가 전것 까지만 뽑아옴; 쿼리로 확인하면 잘나옴; 뭐임;
        log.info("컨트롤러에서 list: "+replyService.getPageList(criteria,bno));
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


    @PutMapping(value = "update/{rno}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE
        }
    )
    public ResponseEntity<String> getList(
            @RequestBody ReplyVO replyVO,
            @PathVariable("rno") Long rno){
        replyVO.setRno(rno);
        replyService.modify(replyVO);

    return new ResponseEntity<>("update success"+ replyVO.getReply(),HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{rno}",
    produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> get(@PathVariable("rno") Long rno) {
        replyService.deleteOne(rno);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


}
