package es.capgemini.cca.canbemini.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.mapppers.UsersMapper;
import es.capgemini.cca.canbemini.users.Users;
import es.capgemini.cca.canbemini.users.UsersRepository;
import es.capgemini.cca.canbemini.users.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UsersService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsersMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public LoginReply authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken(loginRequest.getEmail());

        LoginReply reply = new LoginReply();
        reply.setToken(token);

        // UserDetails userDetails = (UserDetails)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = userService.findByEmail(loginRequest.getEmail());

        reply.setUser(userMapper.UsersToUsersDto(user));

        return reply;
    }

}
