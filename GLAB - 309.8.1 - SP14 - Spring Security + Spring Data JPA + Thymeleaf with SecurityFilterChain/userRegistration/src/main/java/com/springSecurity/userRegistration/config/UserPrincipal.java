package com.springSecurity.userRegistration.config;

/*In this step we add a special class called UserPrincipal (you can give any name).
 This class implements the UserDetails interface which is a part of Spring Security Core (org.springframework.security.core.userdetails.UserDetails).
 This implementation gives you more flexibility and control over user authorization and authentication processes.
  For instance, set a user account disabled/enabled and other.*/

import com.springSecurity.userRegistration.model.Role;
import com.springSecurity.userRegistration.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private User user;
    private Collection<Role> roles;

    public UserPrincipal(User user, Collection<Role> roles) {
        super();
        this.user = user;
        this.roles = roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
