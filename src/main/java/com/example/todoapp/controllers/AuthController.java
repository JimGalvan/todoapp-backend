package com.example.todoapp.controllers;

import com.example.todoapp.dtos.auth.TokenResponseDto;
import com.example.todoapp.dtos.user.UserRegistrationDto;
import com.example.todoapp.entities.User;
import com.example.todoapp.repositories.UserRepository;
import com.example.todoapp.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtEncoder encoder;

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserRegistrationDto userDto) {
        List<String> authorities = List.of("USER");
        return userService.registerUser(userDto.getUsername(), userDto.getPassword(), authorities);
    }

    @PostMapping("/token")
    public TokenResponseDto token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;

        Object principal = null;
        if (authentication != null && authentication.isAuthenticated()) {
            principal = authentication.getPrincipal();
        } else {
            throw new RuntimeException("Invalid authentication");
        }

        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByUsername(username);
        UUID user_id = user.getId();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .claim("user_id", user_id)
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenResponseDto(token);
    }
}
