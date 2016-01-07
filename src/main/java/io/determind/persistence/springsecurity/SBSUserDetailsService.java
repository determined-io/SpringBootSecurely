package io.determind.persistence.springsecurity;

import io.determind.persistence.model.User;
import io.determind.persistence.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SBSUserDetailsService implements UserDetailsService {

    private final UserServiceInterface userService;

    @Autowired
    public SBSUserDetailsService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));

        SBSUser sbsUser = new SBSUser(user);
        return sbsUser;
    }

}
