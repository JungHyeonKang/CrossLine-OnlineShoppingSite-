package com.green.team4.controller.admin;

import com.github.pagehelper.PageInfo;
import com.green.team4.paging.PagingEntity;
import com.green.team4.vo.admin.SearchVO;
import com.green.team4.service.admin.PagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PagingController {
    private final PagingService service;

    @GetMapping("/page")
    public String page(@ModelAttribute SearchVO search,
                       @RequestParam(required = false, defaultValue = "1") int pageNum, Model model) throws Exception {
        PageInfo<PagingEntity> p = new PageInfo<>(service.getProductList(pageNum, search), 10);
        model.addAttribute("products", p);
        model.addAttribute("search", search);
        return "/page";
    }
}
