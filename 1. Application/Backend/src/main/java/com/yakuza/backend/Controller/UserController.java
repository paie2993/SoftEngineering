package com.yakuza.backend.Controller;


import com.yakuza.backend.Controller.DTO.LoginRequestDto;
import com.yakuza.backend.Controller.DTO.LoginResponseDto;
import com.yakuza.backend.Controller.DTO.RegisterRequestDto;
import com.yakuza.backend.Controller.DTO.UpdateUserRequestDto;
import com.yakuza.backend.JWTUtils.JWTUserDetailsService;
import com.yakuza.backend.JWTUtils.TokenManager;
import com.yakuza.backend.Model.UserModel.*;
import com.yakuza.backend.Repository.*;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final ChairRepository chairRepository;
    private final ReviewerRepository reviewerRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public UserController(UserRepository userRepository, AuthorRepository authorRepository, ChairRepository chairRepository, ReviewerRepository reviewerRepository, UserTypeRepository userTypeRepository, PasswordEncoder passwordEncoder, JWTUserDetailsService userDetailsService, AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.chairRepository = chairRepository;
        this.reviewerRepository = reviewerRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto request) throws Exception {
        try { // try to authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (DisabledException e){ // if the user is disabled
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) { // if the credentials provided are incorrect
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        // get the user details and the id of the user
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        Integer id = userRepository.getCMSUserByUsername(request.getUsername()).getId();
        String role = userDetails.getAuthorities().toArray()[0].toString();

        // return the user details and the token as a response to the login request
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(jwtToken, request.getUsername(), role, id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequestDto request){
        // if another user with the same email already exists in the database
        if(userRepository.existsByEmail(request.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        // if another user with the same username already exists in the database
        if(userRepository.existsByUsername(request.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Password must be between 4 and 8 digits long and include at least one numeric digit.
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d).{4,8}$");
        Pattern emailPattern = Pattern.compile("^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}\\.){1,125}[A-Z]{2,63}$");

        // check that the password matches the requirements
        if(!passwordPattern.matcher(request.getPassword()).matches()){
            return new ResponseEntity<>("Password must be between 4 and 8 digits long and include at least one numeric digit.", HttpStatus.BAD_REQUEST);
        }

//        if(!emailPattern.matcher(request.getEmail()).matches()) {
//            return new ResponseEntity<>("Invalid email", HttpStatus.BAD_REQUEST);
//        }

        if(request.getUsername().length() < 3) {
            return new ResponseEntity<>("Username must be longer than 3 characters", HttpStatus.BAD_REQUEST);
        }

        // check that the user type exists
        if(!userTypeRepository.existsById(request.getUserType())){
            return new ResponseEntity<>("Invalid user type", HttpStatus.BAD_REQUEST);
        }

        // create a new user object and add it to the repository
        UserBuilder userBuilder = new UserBuilder(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()))
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setDateOfBirth(request.getDateOfBirth())
                .setPhoneNumber(request.getPhoneNumber())
                .setCountry(request.getCountry())
                .setStreet(request.getStreet())
                .setCity(request.getCity())
                .setStreetNumber(request.getStreetNumber())
        // set the user type according to the provided id
                .setUserType(userTypeRepository.getById(request.getUserType()));

        switch(request.getUserType()) {
            case 1:{
                authorRepository.save(userBuilder.buildAuthor());
                break;
            }
            case 2:{
                reviewerRepository.save(userBuilder.buildReviewer());
                break;
            }
            case 3:{
                chairRepository.save(userBuilder.buildChair());
                break;
            }
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> getUserDetails(@ApiIgnore Principal principal, @RequestParam Integer id) {
        Optional<?> user_opt = userRepository.findById(id);

        // check that the user with the provided id indeed exists
        if(user_opt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        // get the user if it exists
        CMSUser user = (CMSUser) user_opt.get();

        // if the requesting user doesn't correspond with the user whose information is requested, respond with error
        if(!user.getUsername().equals(principal.getName())) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        // return the user details
        return ResponseEntity.ok(user);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUserDetails(@ApiIgnore Principal principal, @RequestBody UpdateUserRequestDto request) {
        // get the current user by username
        CMSUser user = userRepository.getCMSUserByUsername(principal.getName());

        // if they provided a username, update it
        if(request.getUsername() != null) {
            if(userRepository.existsByUsername(request.getUsername())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }

            user.setUsername(request.getUsername());
        }


        // if they provided an email, update it
        if(request.getEmail() != null) {
            if(userRepository.existsByEmail(request.getEmail())) {
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            }

            user.setEmail(request.getEmail());
        }


        // if they provided a password, you get the idea
        if(request.getPassword() != null) {
            Pattern passwordPattern = Pattern.compile("^(?=.*\\d).{4,8}$");

            // check that the password matches the requirements
            if(!passwordPattern.matcher(request.getPassword()).matches()){
                return new ResponseEntity<>("Password must be between 4 and 8 digits long and include at least one numeric digit.", HttpStatus.BAD_REQUEST);
            }

            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }


        if(request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }


        if(request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }


        if(request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }


        if(request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }


        if(request.getCountry() != null) {
            user.setCountry(request.getCountry());
        }


        if(request.getStreet() != null) {
            user.setStreet(request.getStreet());
        }


        if(request.getCity() != null) {
            user.setCity(request.getCity());
        }


        if(request.getStreetNumber() != null) {
            user.setStreetNumber(request.getStreetNumber());
        }

        userRepository.save(user);

        Optional<?> user_opt = userRepository.findById(user.getId());

        if(user_opt.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        CMSUser user_result = (CMSUser) user_opt.get();
        return ResponseEntity.ok(user_result);
    }
}
