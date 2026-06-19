package com.example.demo.security;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {

        this.email = user.getEmail();
        this.password = user.getPassword();

        List<GrantedAuthority> authList = new ArrayList<>();

        // Add role
        authList.add(new SimpleGrantedAuthority(user.getRole().getName()));

        // Add permissions
        authList.addAll(
                user.getRole().getPermissions().stream()
                        .map(p -> new SimpleGrantedAuthority(p.getName()))
                        .collect(Collectors.toSet())
        );

        // Finally assign
        this.authorities = authList;

        System.out.println("Authorities : " + authorities);
        System.out.println("User : " + user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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