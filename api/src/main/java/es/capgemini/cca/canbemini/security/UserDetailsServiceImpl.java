package es.capgemini.cca.canbemini.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> userRes = usersRepository.findByEmail(email);

        if (userRes.isEmpty())
            throw new UsernameNotFoundException("Could not find a user with email: " + email);
        Users user = userRes.get();

        return new User(user.getEmail(), "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
