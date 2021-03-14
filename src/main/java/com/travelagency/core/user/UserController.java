package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.models.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

   @Autowired
   UserServices userServices;
    @PostMapping("/auth/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userServices.createUser(user);

    }
    @PostMapping("/auth/signIn")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest){
        return userServices.signin(signInRequest);
    }

    @GetMapping("/user")
    public List<User> getUsers(){

        return userServices.getUsers();
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable int id){
       return userServices.getOneUser(id);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id ){
        return userServices.deleteUser(id);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody User updateUser) {
        return userServices.updateUser(id,updateUser);
    }

   @GetMapping("/users")
    public ResponseEntity<?> getAges(){
        return userServices.getAges();
   }



}
