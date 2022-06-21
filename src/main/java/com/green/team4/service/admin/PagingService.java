package com.green.team4.service.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.green.team4.mapper.admin.PagingMapper;
import com.green.team4.paging.PagingEntity;
import com.green.team4.vo.admin.SearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagingService {
    private final PagingMapper repository;

    public Page<PagingEntity> getProductList(int pageNo, SearchVO search) throws Exception {
        PageHelper.startPage(pageNo, 10);
        return repository.findProduct(search);
    }
}
