package com.green.team4.security;

import com.green.team4.vo.mypage.MemberInfoVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Log4j2
public class PrincipalDetails implements UserDetails {
    private MemberInfoVO member;

    public PrincipalDetails(MemberInfoVO member){
        this.member = member;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                log.info("PrincipalDetails getAuth: "+member.getAuth());
                return member.getAuth();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
