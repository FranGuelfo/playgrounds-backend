package com.playground.playground.auth;

import com.playground.playground.domain.enums.Role;
import com.playground.playground.exception.BadRequestException;
import com.playground.playground.repository.UserRepository;
import com.playground.playground.security.jwt.JwtService;
import com.playground.playground.security.user.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUsername(registerDto.getUsername());
        userSecurity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userSecurity.setRole(Role.USER);

        userRepository.save(userSecurity);
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails);
    }
}
