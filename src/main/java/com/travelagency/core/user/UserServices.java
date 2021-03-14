package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.models.SignInRequest;
import com.ditraacademy.travelagency.core.user.models.SignInResponse;
import com.ditraacademy.travelagency.utils.EmailUtils;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import com.ditraacademy.travelagency.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Service

public class UserServices implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EmailUtils emailUtils;

    public ResponseEntity<?> createUser(User user) {
        if (user.getName() == null) {
            return new ResponseEntity<>(new ErrorResponseModel("User name required"), HttpStatus.BAD_REQUEST);
        }
        if (user.getName().length() < 3) {
            return new ResponseEntity<>(new ErrorResponseModel("Wrong user name"), HttpStatus.BAD_REQUEST);
        }
        if (user.getAge() == null) {
            return new ResponseEntity<>(new ErrorResponseModel("User age required"), HttpStatus.BAD_REQUEST);
        }
        if (user.getAge() < 0 || user.getAge() > 200) {
            return new ResponseEntity<>(new ErrorResponseModel("User name required"), HttpStatus.BAD_REQUEST);
        }

        String password = passwordEncoder().encode(user.getPassword());
        user.setPassword(password);

        String destination = user.getEmail();
        String subject="spring mailer test";
        String text="hello";
        emailUtils.sendEmail(destination,subject,text);


        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public ResponseEntity<?> getOneUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(("user not found"));
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(("user not found"));
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    public ResponseEntity<?> updateUser(int id, User updateUser) {
        Optional<User> userOptional = userRepository.findById(id);


        if (!userOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(("user not found"));
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }


        User dataBaseUser = userOptional.get();
        if (updateUser.getName() != null) {
            if (updateUser.getName().length() < 3) {
                return new ResponseEntity<>(new ErrorResponseModel("Wrong user name"), HttpStatus.BAD_REQUEST);
            }
            dataBaseUser.setName(updateUser.getName());
        }
        if (updateUser.getAge() != null) {
            if (updateUser.getAge() < 0 || updateUser.getAge() > 200)
                return new ResponseEntity<>(new ErrorResponseModel("User name required"), HttpStatus.BAD_REQUEST);
            dataBaseUser.setAge(updateUser.getAge());
        }

        userRepository.save(dataBaseUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getAges() {
        List<User> userList = userRepository.findAllByAgeIsLessThan(20);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent())
            return null;
        User user = userOptional.get();

        String password = user.getPassword();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //fonction Hash

    }

    public ResponseEntity<?> signin(SignInRequest signInRequest) {



        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        String token = jwtUtils.generateToken((signInRequest.getUsername()));

        return new ResponseEntity<>(new SignInResponse(token),HttpStatus.OK);
    }
}
