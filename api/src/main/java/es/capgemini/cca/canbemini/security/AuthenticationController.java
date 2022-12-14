package es.capgemini.cca.canbemini.security;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.capgemini.cca.canbemini.users.UsersRepository;

@RequestMapping(value = "/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentialsDto body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}
