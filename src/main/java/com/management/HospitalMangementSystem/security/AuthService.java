package com.management.HospitalMangementSystem.security;

import com.management.HospitalMangementSystem.Entity.User;
import com.management.HospitalMangementSystem.dto.LoginRequestDto;
import com.management.HospitalMangementSystem.dto.LoginResponseDto;
import com.management.HospitalMangementSystem.dto.SignUpResponseDto;
import com.management.HospitalMangementSystem.repository.UserRepository;
import com.management.HospitalMangementSystem.Entity.type.AuthProviderType;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername() , loginRequestDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token , user.getId());
    }

    public User signUpInternal(LoginRequestDto signUpRequestDto , AuthProviderType providerType
            , String providerId){
        User user = userRepository.findByUsername(signUpRequestDto.getUsername())
                .orElse(null);
        if(user != null){
            throw new IllegalArgumentException("User already exist");
        }

        user =  User.builder()
                .username(signUpRequestDto.getUsername())
                .providerId(providerId)
                .providerType(providerType)
                .build();

        if(providerType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }

    public SignUpResponseDto signup(LoginRequestDto signUpRequestDto) {
        User user = signUpInternal(signUpRequestDto , AuthProviderType.EMAIL , null);

        return new SignUpResponseDto(user.getId() , user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        // fetch provider type and provider id
        // save the provider type and provider id with oAuth2User
        // if the oAuth2User have an account : directly login
        // otherwise first signup then login
        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        System.out.println(providerType);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);
        User user = userRepository.findByProviderIdAndProviderType(providerId , providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){
            // now implement signup flow
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId , providerId);
            user = signUpInternal( new LoginRequestDto(
                    username , null
            ) , providerType , providerId);

        }
        else if(user != null){
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }
        else{
            throw new BadCredentialsException("This email is already registered with Provider " + emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user) , user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostConstruct
    public void checkEnv() {
        System.out.println("CLIENT_ID=" + System.getenv("GOOGLE_CLIENT_ID"));
        System.out.println("CLIENT_SECRET=" + System.getenv("GOOGLE_CLIENT_SECRET"));
    }

}
