package com.yakuza.backend.Controller.UserController;


import com.yakuza.backend.Controller.UserController.Model.LoginRequestModel;
import com.yakuza.backend.Controller.UserController.Model.LoginResponseModel;
import com.yakuza.backend.Controller.UserController.Model.RegisterRequestModel;
import com.yakuza.backend.JWTUtils.JWTUserDetailsService;
import com.yakuza.backend.JWTUtils.TokenManager;
import com.yakuza.backend.Model.CMSUser;
import com.yakuza.backend.Repository.UserRepository;
import com.yakuza.backend.Repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> createToken(@RequestBody LoginRequestModel request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String role = userDetails.getAuthorities().toArray()[0].toString();

        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new LoginResponseModel(jwtToken, request.getUsername(), role));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequestModel request){
        if(userRepository.existsByEmail(request.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(request.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Password must be between 4 and 8 digits long and include at least one numeric digit.
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d).{4,8}$");

        if(!passwordPattern.matcher(request.getPassword()).matches()){
            return new ResponseEntity<>("Password must be between 4 and 8 digits long and include at least one numeric digit.", HttpStatus.BAD_REQUEST);
        }

        if(!userTypeRepository.existsById(request.getUserType())){
            return new ResponseEntity<>("Invalid user type", HttpStatus.BAD_REQUEST);
        }

        CMSUser user = new CMSUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCountry(request.getCountry());
        user.setStreet(request.getStreet());
        user.setCity(request.getCity());
        user.setStreetNumber(request.getStreetNumber());
        user.setUserType(userTypeRepository.getById(request.getUserType()));

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
