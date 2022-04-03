package com.yakuza.backend.JWTUtils;

import com.yakuza.backend.Model.CMSUser;
import com.yakuza.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CMSUser user = userRepository.findByUsername(username);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority(user.getUserType().getType()));

            return new User(user.getUsername(), user.getPassword(), authorities);
        } catch (EntityNotFoundException e){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
