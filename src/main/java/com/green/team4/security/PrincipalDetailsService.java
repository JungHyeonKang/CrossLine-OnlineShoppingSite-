package com.green.team4.security;

import com.green.team4.service.mypage.MemberInfoService;
import com.green.team4.vo.mypage.MemberInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private MemberInfoService memberInfoService;


    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{
        MemberInfoVO member = memberInfoService.findById(id);
        if(member != null){
            System.out.println("UserDetails loadUserByUsername => "+"mno: "+member.getMno() + " name: "+member.getName() + " auth: "+member.getAuth());
            return new PrincipalDetails(member);
        }
        return null;
    }
}
