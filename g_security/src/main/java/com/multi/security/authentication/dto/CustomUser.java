package com.multi.security.authentication.dto;

import com.multi.security.member.dto.MemberDTO;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@ToString(exclude = "pw")
public class CustomUser implements UserDetails {


    private String id; // principal
    private String pw; // credential
    private String memberEmail; // principal
    private Collection<? extends GrantedAuthority> authorities; // authorities

    public CustomUser(MemberDTO memberDTO, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.id = memberDTO.getId();
        this.pw = memberDTO.getPw();
        this.memberEmail = memberDTO.getMemberEmail();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pw;

    }

    @Override
    public String getUsername() {
        return memberEmail;

    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
