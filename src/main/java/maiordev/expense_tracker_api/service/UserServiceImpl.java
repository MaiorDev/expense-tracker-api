package maiordev.expense_tracker_api.service;

import maiordev.expense_tracker_api.model.AuthRequest;
import maiordev.expense_tracker_api.repository.UserRepository;
import maiordev.expense_tracker_api.util.GenericResponse;
import maiordev.expense_tracker_api.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Override
    public GenericResponse registerUser(AuthRequest authRequest) {
        if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return new GenericResponse( 1, "Username already exists", null);
        }
        maiordev.expense_tracker_api.entity.UserEntity newUser = new maiordev.expense_tracker_api.entity.UserEntity();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userRepository.save(newUser);
        return new GenericResponse( 0, "User registered successfully", null);
    }

    @Override
    public GenericResponse loginUser(AuthRequest authRequest) {
       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           authRequest.getUsername(),
                           authRequest.getPassword()
                   )
           );
           String token =  jwtUtils.generateAccessToken(authentication.getName());
           return new GenericResponse(0, "Login success", token);
       }catch (Exception e){
           return new GenericResponse(1, "Invalid username or password", null);
       }
    }
}