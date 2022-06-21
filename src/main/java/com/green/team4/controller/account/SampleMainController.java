package com.green.team4.controller.account;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/account")
public class SampleMainController {

    @GetMapping("/samplemain")
    public void SampleMain(){

    }
}
