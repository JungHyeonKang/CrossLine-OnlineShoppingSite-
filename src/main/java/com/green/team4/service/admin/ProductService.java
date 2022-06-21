package com.green.team4.service.admin;


import com.green.team4.vo.admin.ProductVO;

import java.util.List;

public interface ProductService {
    int pno(ProductVO vo);
    int insert(ProductVO vo);
    List<ProductVO> getAll();
    ProductVO getOne(int pno);
    ProductVO getEvePno();
    int update(ProductVO vo);
    int delete(int pno);
}
