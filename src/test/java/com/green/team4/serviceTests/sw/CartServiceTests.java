package com.green.team4.serviceTests.sw;

import com.green.team4.service.mypage.CartService;
import com.green.team4.vo.mypage.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    @Test
    public void testReadAll(){
        int mno = 8;
        List<CartVO> cartList = cartService.readAll(mno);
        cartList.forEach(i-> System.out.println(i));
    }

    @Test
    public void testDelete(){
        int cno = 2;
        int result = cartService.remove(cno);
        System.out.println("삭제된 장바구니 물건 개수: "+result);
    }
}
