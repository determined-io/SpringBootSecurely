package io.determind.persistence.springsecurity;

import io.determind.persistence.model.Role;
import io.determind.persistence.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SBSUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public SBSUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), createAuthorities(user));
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> createAuthorities(User user) {
        List<String> roleStrs = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        for (Role role: roles) {
            roleStrs.add(role.getName());
        }
        String[] roleArr = new String[roleStrs.size()];
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleStrs.toArray(roleArr));
        return authorities;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "SBSUser{ User: " + super.toString() + " }";
    }
}
